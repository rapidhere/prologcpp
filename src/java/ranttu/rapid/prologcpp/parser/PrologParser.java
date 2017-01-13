/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser;

import ranttu.rapid.prologcpp.exp.QueryNotLast;
import ranttu.rapid.prologcpp.exp.UnexpectedToken;
import ranttu.rapid.prologcpp.parser.absyn.AtomNode;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.FunctorNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;
import ranttu.rapid.prologcpp.parser.absyn.QueryNode;
import ranttu.rapid.prologcpp.parser.absyn.StatementNode;
import ranttu.rapid.prologcpp.parser.token.BasePrologToken;
import ranttu.rapid.prologcpp.parser.token.Comma;
import ranttu.rapid.prologcpp.parser.token.Dot;
import ranttu.rapid.prologcpp.parser.token.LeftParenthesis;
import ranttu.rapid.prologcpp.parser.token.Number;
import ranttu.rapid.prologcpp.parser.token.QueryToken;
import ranttu.rapid.prologcpp.parser.token.RightParenthesis;
import ranttu.rapid.prologcpp.parser.token.Symbol;

/**
 * Parse prolog to syntax tree
 *
 * @author rapidhere@gmail.com
 * @version $id: PrologParser.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class PrologParser {
    private PrologLexer lexer;

    public PrologParser(String prologCode) {
        lexer = new PrologLexer(prologCode);
    }

    /**
     * parse the code and return the parsed abstract syntax tree
     * @return the root of abstract syntax tree
     */
    public ProgramNode parse() {
        return parseProgram();
    }

    protected ProgramNode parseProgram() {
        ProgramNode program = new ProgramNode();
        QueryNode query = null;

        while (lexer.hasNext()) {
            StatementNode stm = parseStatement();

            if(query != null) {
                throw new QueryNotLast(query);
            }

            if(stm.is(QueryNode.class)) {
                query = (QueryNode) stm;
            }

            program.add(parseStatement());
        }

        return program;
    }

    protected StatementNode parseStatement() {
        BasePrologToken token = lexer.peekToken();

        if(token.is(QueryToken.class)) {
            return parseQuery();
        } else {
            return parseFactOrRule();
        }
    }

    protected QueryNode parseQuery() {
        QueryToken queryToken = lexer.nextToken(QueryToken.class);

        QueryNode query = new QueryNode(queryToken);

        while(true) {
            query.addFunctorNode(parseFunctor());

            BasePrologToken token = lexer.nextToken();

            if(token.is(Dot.class)) {
                break;
            } else if(! token.is(Comma.class)) {
                throw new UnexpectedToken(token);
            }
        }

        return query;
    }

    protected FunctorNode parseFunctor() {
        Symbol functorId = lexer.nextToken(Symbol.class);
        FunctorNode functor = new FunctorNode(functorId);

        // TODO: merge with parse fact
        lexer.nextToken(LeftParenthesis.class);

        while (true)  {
            BasePrologToken token = lexer.nextToken();
            functor.addAtom(new AtomNode(token));

            token = lexer.nextToken();
            if (token.is(RightParenthesis.class)) {
                break;
            } else if(! token.is(Comma.class)){
                // or must be Comma
                throw new UnexpectedToken(token);
            }
        }

        // end with dot
        lexer.nextToken(Dot.class);

        return functor;
    }

    protected StatementNode parseFactOrRule() {
        Symbol factId = lexer.nextToken(Symbol.class);
        FactNode fact = new FactNode(factId);

        lexer.nextToken(LeftParenthesis.class);

        while (true) {
            Number arg = lexer.nextToken(Number.class);
            fact.addArgument(arg);

            BasePrologToken token = lexer.nextToken();
            if (token.is(RightParenthesis.class)) {
                break;
            } else if(! token.is(Comma.class)){
                // or must be Comma
                throw new UnexpectedToken(token);
            }
        }

        // end with dot
        lexer.nextToken(Dot.class);

        return fact;
    }
}

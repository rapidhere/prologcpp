/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser;

import ranttu.rapid.prologcpp.exp.UnexpectedToken;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;
import ranttu.rapid.prologcpp.parser.token.BasePrologToken;
import ranttu.rapid.prologcpp.parser.token.Comma;
import ranttu.rapid.prologcpp.parser.token.Dot;
import ranttu.rapid.prologcpp.parser.token.LeftParenthesis;
import ranttu.rapid.prologcpp.parser.token.Number;
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
        while (lexer.hasNext()) {
            program.add(parseFact());
        }

        return program;
    }

    protected FactNode parseFact() {
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

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile;

import ranttu.rapid.prologcpp.parser.absyn.AtomNode;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.FunctorNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;
import ranttu.rapid.prologcpp.parser.absyn.QueryNode;
import ranttu.rapid.prologcpp.parser.absyn.StatementNode;

/**
 * visit nodes in post-order on ast
 *
 * @author rapidhere@gmail.com
 * @version $id: PostOrderVisitPass.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
abstract public class PostOrderVisitPass implements Pass {
    protected CompilingContext context;

    @Override
    public void process(CompilingContext context) {
        this.context = context;

        init();
        visit(context.program);
    }

    // ~ visit methods
    private void visit(ProgramNode program) {
        program.getBody().forEach(this::visit);
        on(program);
    }

    private void visit(StatementNode statement) {
        if(statement.is(FactNode.class)) {
            visit((FactNode) statement);
        } else if(statement.is(QueryNode.class)) {
            visit((QueryNode) statement);
        }

        on(statement);
    }

    private void visit(FactNode fact) {
        on(fact);
    }

    private void visit(QueryNode query) {
        query.getFunctors().forEach(this::visit);
        on(query);
    }

    private void visit(FunctorNode functor) {
        functor.getAtoms().forEach(this::visit);
        on(functor);
    }

    private void visit(AtomNode atom) {
        on(atom);
    }

    // ~ init
    protected void init() {}

    // ~ visit entry
    protected void on(ProgramNode program) {}
    protected void on(StatementNode statement) {}
    protected void on(FactNode fact) {}
    protected void on(QueryNode query) {}
    protected void on(FunctorNode functor) {}
    protected void on(AtomNode atom) {}
}

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile;

import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;

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

    private void visit(FactNode fact) {
        on(fact);
    }

    // ~ init
    protected void init() {}

    // ~ visit entry
    protected void on(ProgramNode program) {}
    protected void on(FactNode fact) {}
}

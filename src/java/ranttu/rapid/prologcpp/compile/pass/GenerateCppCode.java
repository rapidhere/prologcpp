/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.$;
import ranttu.rapid.prologcpp.parser.absyn.FunctorNode;
import ranttu.rapid.prologcpp.parser.absyn.QueryNode;
import ranttu.rapid.prologcpp.template.T;
import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.compile.struct.Functor;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * the pass generating cpp code
 *
 * @author rapidhere@gmail.com
 * @version $id: GenerateCppCode.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class GenerateCppCode extends PostOrderVisitPass {
    private StringWriter writer;

    @Override
    protected void init() {
        // clear
        writer = new StringWriter();
        // set builder
        T.setWriter(writer);

        T.Essential.render();
        T.ConstantTable.with("context", context).render();
        for(Functor functor: context.functors.values()) {
            T.GenericFunctor.with("functor", functor).render();
        }
    }

    @Override
    protected void on(ProgramNode program) {
        // get result
        context.compiledCode = writer.toString();
    }

    @Override
    protected void on(QueryNode query) {
        // query will be the last statement in program
        // generate true functors
        for(Functor functor: context.functors.values()) {
            T.TopFunctor.with("functor", functor).render();
        }


        // TODO: refine
        // TODO: only support one query now
        $.should(query.getFunctors().size() == 1, "currently only support 1-atom query");

        FunctorNode functorNode = query.getFunctors().get(0);
        Functor functor = context.getFunctor(functorNode.getFunctorId().getValue(), functorNode.getSize());

        T.QueryMain
            .with("functorNode", functorNode)
            .with("functor", functor)
            .with("context", context)
            .render();
    }

    @Override
    protected void on(FactNode fact) {
        // get functor
        Functor functor = context.getFunctor(fact.getFactId().getValue(), fact.getArguments()
            .size());
        functor.incItemCount();

        for(int mask = 0;mask < (1 << functor.getSize());mask ++) {
            List<Boolean> q = new ArrayList<>();
            for(int i = 0;i < functor.getSize();i ++) {
                q.add(((mask >> i) & 1) == 1);
            }

            T.SubFunctor
                .with("functor", functor)
                .with("args", fact.getArguments())
                .with("context", context)
                .with("q", q)
                .render();
        }
    }
}

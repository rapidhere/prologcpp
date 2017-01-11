/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.template.T;
import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.compile.struct.Functor;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;

import java.util.ArrayList;
import java.util.List;

/**
 * the pass generating cpp code
 *
 * @author rapidhere@gmail.com
 * @version $id: GenerateCppCode.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class GenerateCppCode extends PostOrderVisitPass {
    private StringBuilder builder;

    @Override
    protected void init() {
        // clear
        builder = new StringBuilder();
        // set builder
        T.setBuilder(builder);

        T.Essential.render();
        T.ConstantTable.with("context", context).render();
        for(Functor functor: context.functors.values()) {
            T.GenericFunctor.with("functor", functor).render();
        }
    }

    @Override
    protected void on(ProgramNode program) {
        // generate true functors
        for(Functor functor: context.functors.values()) {
            T.TopFunctor.with("functor", functor).render();
        }

        // get result
        context.compiledCode = builder.toString();
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

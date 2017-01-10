/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.T;
import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.compile.struct.Functor;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;

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

        T.essential().render();
        T.constantTable().with("context", context).render();
        T.genericFactTemplates().with("functors", context.functors).render();
    }

    @Override
    protected void on(ProgramNode program) {
        // generate true functors
        T.topFunctors().with("context", context).render();

        // get result
        context.compiledCode = builder.toString();
    }

    @Override
    protected void on(FactNode fact) {
        // get functor
        Functor functor = context.getFunctor(fact.getFactId().getValue(), fact.getArguments()
            .size());
        functor.incItemCount();

        T.subFunctor().with("functor", functor).with("fact", fact).with("context", context)
            .render();
    }
}

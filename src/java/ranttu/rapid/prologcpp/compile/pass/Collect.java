/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.compile.struct.Functor;
import ranttu.rapid.prologcpp.exp.TooManyAtoms;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.token.Number;

/**
 * the pass that collecting facts, rules and constants
 *
 * @author rapidhere@gmail.com
 * @version $id: Collect.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class Collect extends PostOrderVisitPass {
    @Override
    protected void on(FactNode fact) {
        // check atom size
        if(fact.getArguments().size() > context.maxAtoms) {
            throw new TooManyAtoms(fact);
        }

        // collect functor
        Functor functor = new Functor(fact.getFactId().getValue(), fact.getArguments().size());
        context.addFunctor(functor);

        // collect constant
        for(Number number: fact.getArguments()) {
            context.addConstant(number.getInt());
        }
    }
}

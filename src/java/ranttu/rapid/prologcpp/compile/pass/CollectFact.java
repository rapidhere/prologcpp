/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.parser.absyn.Fact;

import java.util.HashMap;

/**
 * the pass that collecting facts and rules
 *
 * @author rapidhere@gmail.com
 * @version $id: CollectFact.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class CollectFact extends PostOrderVisitPass {
    @Override
    protected void init() {
        context.facts = new HashMap<>();
    }

    @Override
    protected void on(Fact fact) {
        context.facts.put(fact.getFactId().getValue(), fact);
    }
}

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile;

import com.google.common.collect.ImmutableList;
import ranttu.rapid.prologcpp.compile.pass.CollectFact;
import ranttu.rapid.prologcpp.compile.pass.GenerateCppCode;
import ranttu.rapid.prologcpp.parser.PrologParser;
import ranttu.rapid.prologcpp.parser.absyn.Program;

import java.util.List;

/**
 * compile prolog to cpp
 *
 * @author rapidhere@gmail.com
 * @version $id: Prolog2CppCompiler.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class Prolog2CppCompiler {
    /**
     * compile the prolog code to C++ code
     * @return compiled c++ code
     */
    public String compile(String prologCode) {
        PrologParser parser = new PrologParser(prologCode);
        Program prologProgram = parser.parse();

        // set up context
        CompilingContext context = new CompilingContext();
        context.program = prologProgram;

        // processing passes
        for(Pass pass: PASSES) {
            pass.process(context);
        }

        return context.compiledCode;
    }

    // compile passes
    private final static List<Pass> PASSES = ImmutableList.of(
        new CollectFact(),
        new GenerateCppCode());


    // ~ tmp test
    public static void main(String args[]) {
        System.out.println(new Prolog2CppCompiler().compile("f(1)."));
    }
}

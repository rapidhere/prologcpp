/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.parser.absyn.Fact;
import ranttu.rapid.prologcpp.parser.absyn.Program;

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
        builder = new StringBuilder();

        generateFactTemplates();
    }

    @Override
    protected void on(Program program) {
        context.compiledCode = builder.toString();
    }

    @Override
    protected void on(Fact fact) {
        l("template <>");
        l("struct %s <%d, %d> {", fact.getFactId().getValue(), fact.getArguments().get(0).getInt(), 1);
        l("    const static int v0 = %d;", fact.getArguments().get(0).getInt());
        l("};");
        l();
    }

    // builder helper
    private GenerateCppCode l(String text, Object...args) {
        builder.append(String.format(text, args)).append("\n");
        return this;
    }

    private GenerateCppCode l() {
        builder.append("\n");
        return this;
    }

    /**
     * generating fact templates
     */
    private void generateFactTemplates() {
        // l("// --- base fact templates");

        for(Fact fact: context.facts.values()) {
            l("template <int _v0, int _i>");
            l("struct %s {", fact.getFactId().getValue());
            l("};");
            l();
        }

        // l("// --- compiled prolog code goes here");
        l();
    }
}

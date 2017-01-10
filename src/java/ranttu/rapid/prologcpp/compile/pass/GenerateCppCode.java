/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile.pass;

import ranttu.rapid.prologcpp.compile.PostOrderVisitPass;
import ranttu.rapid.prologcpp.compile.struct.Functor;
import ranttu.rapid.prologcpp.parser.absyn.FactNode;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;
import ranttu.rapid.prologcpp.parser.token.Number;

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

        generateEssentialComponent();
        generateConstantTable();
        generateFactTemplates();
    }

    @Override
    protected void on(ProgramNode program) {
        // generate true functors
        generateTopFunctors();

        // get result
        context.compiledCode = builder.toString();
    }

    @Override
    protected void on(FactNode fact) {
        // get functor
        Functor functor = context.getFunctor(fact.getFactId().getValue(), fact.getArguments()
            .size());
        functor.incItemCount();

        l("template <>");

        String structHeader = String.format("struct _%s_%d <", functor.getId(), functor.getSize());

        for (Number number : fact.getArguments()) {
            int constantIdx = context.getConstantIndex(number.getInt());
            structHeader += String.format("Atom<%d>, ", constantIdx);
        }
        structHeader += String.format("%d> {", functor.getItemCount());
        l(structHeader);

        for (int i = 0; i < functor.getSize(); i++) {
            int constantIdx = context.getConstantIndex(fact.getArguments().get(i).getInt());

            l("    typedef pack<%d> v%d;", constantIdx, i);
        }
        l("};");
        l();
    }

    // ~~~ generate

    /**
     * generating fact templates
     */
    private void generateFactTemplates() {
        comment("base fact templates");

        for (Functor functor : context.functors.values()) {
            String templateHeader = "template <";
            for (int i = 0; i < functor.getSize(); i++) {
                templateHeader += "class T" + i + ", ";
            }
            templateHeader += "int _i>";
            l(templateHeader);

            l("struct _%s_%d {", functor.getId(), functor.getSize());
            for (int j = 0; j < functor.getSize(); j++) {
                l("    typedef pack<> v%d;", j);
            }
            l("};");
            l();
        }

        comment("compiled prolog code goes here");
        l();
    }

    /**
     * constant tables
     */
    private void generateConstantTable() {
        comment("constant table");

        for (int i = 0; i < context.constants.size(); i++) {
            // current can only be int
            int val = context.getConstant(i);
            l("template <> struct Atom<%d> { static const int val = %d; };", i, val);
        }
        l();
    }

    /**
     * top functor descriptors
     */
    private void generateTopFunctors() {
        comment("top functors");

        for (Functor functor : context.functors.values()) {
            String templateHeader = "<";
            for (int i = 0; i < functor.getSize(); i++) {
                templateHeader += "class T" + i + ", ";
            }
            l("template " + templateHeader + "int _i=" + functor.getItemCount() + ">");

            String topFunctorName = String.format("%s_%d", functor.getId(), functor.getSize());
            String subFunctorName = "_" + topFunctorName;

            l("struct %s {", topFunctorName);
            for (int j = 0; j < functor.getSize(); j++) {
                l("    typedef typename mergePack<typename %s%s_i>::v%d, typename %s%s_i-1>::v%d>::ret v%d;",
                    subFunctorName, templateHeader, j, topFunctorName, templateHeader, j, j);
            }
            l("};");
            l();

            l("template " + templateHeader + ">");
            l("struct %s %s0> {", topFunctorName, templateHeader);
            for (int j = 0;j < functor.getSize();j ++) {
                l("    typedef pack<> v%d;", j);
            }
            l("};");
        }
    }

    /**
     * essential c++ classes and components
     */
    private void generateEssentialComponent() {
        comment("essential components");

        // base atoms
        l("struct QueryAtom {};");
        l();
        l("template <int atom_idx>");
        l("struct Atom {};");
        l();
        // packs and pack utils
        l("template <int... src>");
        l("struct pack {");
        l("    static constexpr int data[] = { src... };");
        l("};");
        l();
        l("template <class T1, class T2>");
        l("struct mergePack {};");
        l();
        l("template <");
        l("    template<int...> class T1,");
        l("    template<int...> class T2,");
        l("    int... src1,");
        l("    int... src2");
        l(">");
        l("struct mergePack <T1<src1...>, T2<src2...>> {");
        l("    typedef pack<src1..., src2...> ret;");
        l("};");
        l();
    }

    // ~~~ builder helper
    private GenerateCppCode l(String text, Object... args) {
        builder.append(String.format(text, args)).append("\n");
        return this;
    }

    private GenerateCppCode l() {
        builder.append("\n");
        return this;
    }

    private GenerateCppCode comment(String text, Object... args) {
        if (context.commentOn) {
            builder.append("// --- ").append(String.format(text, args)).append("\n");
        }

        return this;
    }
}

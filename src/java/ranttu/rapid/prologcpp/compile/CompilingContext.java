/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile;

import ranttu.rapid.prologcpp.$;
import ranttu.rapid.prologcpp.compile.struct.Functor;
import ranttu.rapid.prologcpp.parser.absyn.ProgramNode;

import java.util.HashMap;
import java.util.Map;

/**
 * store some information in context
 *
 * @author rapidhere@gmail.com
 * @version $id: CompilingContext.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class CompilingContext {
    /** comment flag */
    public boolean              commentOn = false;

    /** max atoms of a functor */
    public int                  maxAtoms  = 10;

    /** the compiling program */
    public ProgramNode          program;

    /** collected functor */
    public Map<String, Functor> functors  = new HashMap<>();

    /** collected constants*/
    public Map<Object, Integer> constants = new HashMap<>();

    /** compiled code */
    public String               compiledCode;

    /**
     * add a functor to context
     * @param functor a functor
     */
    public void addFunctor(Functor functor) {
        functors.put(functor.getId() + "/" + functor.getSize(), functor);
    }

    /**
     * get a functor from context
     */
    public Functor getFunctor(String id, int size) {
        return $.notNull(functors.get(id + "/" + size));
    }

    /**
     * add a constant to context
     */
    public void addConstant(Object constantValue) {
        int size = constants.size();
        constants.put(constantValue, size);
    }

    /**
     * get a constant index from context
     */
    public int getConstantIndex(Object constantValue) {
        return $.notNull(constants.get(constantValue));
    }

    /**
     * get constant by index
     * TODO: refine performance
     */
    public <T> T getConstant(int index) {
        for (Object constant : constants.keySet()) {
            if (constants.get(constant) == index) {
                return $.required(constant);
            }
        }

        return $.shouldNotReach();
    }
}
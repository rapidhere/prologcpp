/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.compile;

import ranttu.rapid.prologcpp.parser.absyn.Fact;
import ranttu.rapid.prologcpp.parser.absyn.Program;

import java.util.Map;

/**
 * store some information in context
 *
 * @author rapidhere@gmail.com
 * @version $id: CompilingContext.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class CompilingContext {
    /** the compiling program */
    public Program program;

    /** collected facts and rules */
    public Map<String, Fact> facts;

    /** compiled code */
    public String compiledCode;
}

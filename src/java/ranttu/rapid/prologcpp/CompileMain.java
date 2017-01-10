/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp;

import ranttu.rapid.prologcpp.compile.Prolog2CppCompiler;

/**
 * the main compiler
 *
 * @author rapidhere@gmail.com
 * @version $id: CompileMain.java, v0.1 2017/1/9 dongwei.dq Exp $
 */
public class CompileMain {
    // ~ tmp test
    public static void main(String args[]) {
        System.out.println(new Prolog2CppCompiler().compile("f(1).\nf(2)."));
    }
}

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.exp;

/**
 * @author rapidhere@gmail.com
 * @version $id: PrologCompilingError.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class PrologCompilingError extends PrologCppException {
    public PrologCompilingError(int lineNo, int column, String message) {
        super(String.format("(%d, %d): %s\n", lineNo, column, message));
    }
}

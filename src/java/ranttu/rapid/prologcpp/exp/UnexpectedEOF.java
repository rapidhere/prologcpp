/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.exp;

/**
 * unexpected end of file
 *
 * @author rapidhere@gmail.com
 * @version $id: UnexpectedEOF.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class UnexpectedEOF extends PrologCompilingError{
    public UnexpectedEOF(int lineNo, int column) {
        super(lineNo, column, "unexpected end of file");
    }
}

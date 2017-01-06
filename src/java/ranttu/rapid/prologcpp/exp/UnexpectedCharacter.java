/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.exp;

/**
 * unexpected character compiling error
 *
 * @author rapidhere@gmail.com
 * @version $id: UnexpectedCharacter.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class UnexpectedCharacter extends PrologCompilingError {
    public UnexpectedCharacter(int lineNo, int column, char ch) {
        super(lineNo, column, "unexpected character: `" + ch + "`");
    }
}

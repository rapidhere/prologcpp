/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * a prolog string
 *
 * @author rapidhere@gmail.com
 * @version $id: PrologString.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class PrologString extends BasePrologToken<String> {
    public PrologString(int lineNo, int column, String s) {
        super(lineNo, column, s);
    }
}

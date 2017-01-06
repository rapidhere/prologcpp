/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * a prolog symol
 *
 * @author rapidhere@gmail.com
 * @version $id: Symbol.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
@TokenPattern("\\w*[a-zA-z]+[a-zA-z0-9]*\\w*")
public class Symbol extends BasePrologToken<String> {
    public Symbol(int lineNo, int column, String value) {
        super(lineNo, column, value);
    }
}

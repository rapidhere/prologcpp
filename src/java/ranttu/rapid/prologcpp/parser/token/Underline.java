/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the `_` underline
 *
 * @author rapidhere@gmail.com
 * @version $id: Underline.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
@TokenPattern("\\s*_\\s*")
public class Underline extends BasePrologToken<Character> {
    public Underline(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }
}

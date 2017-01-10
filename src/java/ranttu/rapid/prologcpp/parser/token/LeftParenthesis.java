/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the `(` left parenthesis
 *
 * @author rapidhere@gmail.com
 * @version $id: LeftParenthesis.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
@TokenPattern("\\(")
public class LeftParenthesis extends BasePrologToken<Character> {
    public LeftParenthesis(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }
}

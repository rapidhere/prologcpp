/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the `)` right parenthesis
 *
 * @author rapidhere@gmail.com
 * @version $id: LeftParenthesis.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
@TokenPattern("\\)")
public class RightParenthesis extends BasePrologToken<Character> {
    public RightParenthesis(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }
}

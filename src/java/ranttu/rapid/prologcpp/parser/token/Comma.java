/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the `,` comma
 *
 * @author rapidhere@gmail.com
 * @version $id: Comma.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
@TokenPattern("\\s*,\\s*")
public class Comma extends BasePrologToken<Character> {
    public Comma(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }
}

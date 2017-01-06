/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the `.` dot
 *
 * @author rapidhere@gmail.com
 * @version $id: Dot.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
@TokenPattern("\\w*.\\w*")
public class Dot extends BasePrologToken<Character> {
    public Dot(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }
}

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * a integer or float
 *
 * TODO: only 10-based integer is support now
 *
 * @author rapidhere@gmail.com
 * @version $id: Number.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
@TokenPattern("\\s*\\d+\\s*")
public class Number extends BasePrologToken<java.lang.Number> {
    public Number(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }

    @Override
    protected java.lang.Number castValue(String raw) {
        // TODO: support float
        return Integer.valueOf(raw);
    }

    public int getInt() {
        return (int) getValue();
    }

    public double getFloat() {
        return (double) getValue();
    }
}

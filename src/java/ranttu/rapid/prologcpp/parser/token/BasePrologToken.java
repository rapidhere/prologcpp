/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the basic prolog token
 *
 * @author rapidhere@gmail.com
 * @version $id: BasePrologToken.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
abstract public class BasePrologToken<T> {
    /** the line no of the token */
    protected int lineNo;

    /** the column of the token */
    protected int column;

    /** the value of the token*/
    protected T value;

    public BasePrologToken(int lineNo, int column, String raw) {
        this.lineNo = lineNo;
        this.column = column;
        this.value = castValue(raw);
    }

    final public boolean is(Class<? extends BasePrologToken> clazz) {
        return clazz.isAssignableFrom(getClass());
    }

    @SuppressWarnings("unchecked")
    protected T castValue(String raw) {
        return (T) raw;
    }

    public int getLineNo() {
        return lineNo;
    }

    public int getColumn() {
        return column;
    }

    public T getValue() {
        return value;
    }
}

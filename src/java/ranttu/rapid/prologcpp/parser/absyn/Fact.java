/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

import ranttu.rapid.prologcpp.parser.token.Number;
import ranttu.rapid.prologcpp.parser.token.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * a prolog fact statement
 *
 * @author rapidhere@gmail.com
 * @version $id: Fact.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class Fact extends AstNode{
    private Symbol factId;

    private List<Number> factArgument;

    public Fact(Symbol factId) {
        this.factId = factId;
        this.factArgument = new ArrayList<>();
    }

    public void addArgument(Number number) {
        factArgument.add(number);
    }

    public List<Number> getArguments() {
        return factArgument;
    }

    public Symbol getFactId() {
        return factId;
    }

    @Override
    public int getLineNo() {
        return factId.getLineNo();
    }

    @Override
    public int getColumn() {
        return factId.getColumn();
    }
}

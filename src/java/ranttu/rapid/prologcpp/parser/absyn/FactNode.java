/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

import com.google.common.collect.ImmutableList;
import ranttu.rapid.prologcpp.parser.token.Number;
import ranttu.rapid.prologcpp.parser.token.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * a prolog fact statement
 *
 * @author rapidhere@gmail.com
 * @version $id: FactNode.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class FactNode extends StatementNode {
    private Symbol       factId;

    private List<Number> factArgument;

    public FactNode(Symbol factId) {
        this.factId = factId;
        this.factArgument = new ArrayList<>();
    }

    public void addArgument(Number number) {
        factArgument.add(number);
    }

    public List<Number> getArguments() {
        return ImmutableList.copyOf(factArgument);
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

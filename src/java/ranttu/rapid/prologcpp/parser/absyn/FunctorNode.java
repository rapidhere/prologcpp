/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

import com.google.common.collect.ImmutableList;
import ranttu.rapid.prologcpp.parser.token.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * a functor in a query or rule
 *
 * @author rapidhere@gmail.com
 * @version $id: FunctorNode.java, v0.1 2017/1/13 dongwei.dq Exp $
 */
public class FunctorNode extends AstNode {
    private Symbol functorId;
    private List<AtomNode> atoms = new ArrayList<>();

    public FunctorNode(Symbol functorId) {
        this.functorId = functorId;
    }

    public Symbol getFunctorId() {
        return functorId;
    }

    public void addAtom(AtomNode atom) {
        atoms.add(atom);
    }

    public List<AtomNode> getAtoms() {
        return ImmutableList.copyOf(atoms);
    }

    @Override
    public int getLineNo() {
        return functorId.getLineNo();
    }

    @Override
    public int getColumn() {
        return functorId.getColumn();
    }
}

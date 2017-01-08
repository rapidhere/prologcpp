/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

/**
 * the base ast node
 *
 * @author rapidhere@gmail.com
 * @version $id: AstNode.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
abstract public class AstNode {
    public AstNode() {
    }

    /**
     * get the lineNo of current node
     */
    public abstract int getLineNo();

    /**
     * get the column of current node
     */
    public abstract int getColumn();
}

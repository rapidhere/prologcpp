/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.exp;

import ranttu.rapid.prologcpp.parser.absyn.QueryNode;

/**
 * @author rapidhere@gmail.com
 * @version $id: QueryNotLast.java, v0.1 2017/1/13 dongwei.dq Exp $
 */
public class QueryNotLast extends PrologCompilingError {
    public QueryNotLast(QueryNode queryNode) {
        super(queryNode.getLineNo(), queryNode.getColumn(), "query node must be the last statement");
    }
}

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

import com.google.common.collect.ImmutableList;
import ranttu.rapid.prologcpp.parser.token.QueryToken;

import java.util.ArrayList;
import java.util.List;

/**
 * a query node
 *
 * @author rapidhere@gmail.com
 * @version $id: QueryNode.java, v0.1 2017/1/13 dongwei.dq Exp $
 */
public class QueryNode extends StatementNode {
    private QueryToken queryToken;
    private List<FunctorNode> functors = new ArrayList<>();

    public QueryNode(QueryToken queryToken) {
        this.queryToken = queryToken;
    }

    public void addFunctorNode(FunctorNode functor) {
        functors.add(functor);
    }

    public List<FunctorNode> getFunctors() {
        return ImmutableList.copyOf(functors);
    }

    @Override
    public int getLineNo() {
        return queryToken.getLineNo();
    }

    @Override
    public int getColumn() {
        return queryToken.getColumn();
    }
}

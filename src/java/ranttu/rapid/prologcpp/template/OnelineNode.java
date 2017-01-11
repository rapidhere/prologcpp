/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.jtwig.model.expression.Expression;
import org.jtwig.model.position.Position;
import org.jtwig.model.tree.ContentNode;
import org.jtwig.model.tree.Node;

import java.util.Optional;

/**
 * a one line node
 *
 * @author rapidhere@gmail.com
 * @version $id: OnelineNode.java, v0.1 2017/1/11 dongwei.dq Exp $
 */
class OnelineNode extends ContentNode {
    private Expression tabExpression;

    protected OnelineNode(Position position, Node content, Expression tabExpression) {
        super(position, content);

        this.tabExpression = tabExpression;
    }

    public Optional<Expression> getTabExpression() {
        return Optional.ofNullable(tabExpression);
    }
}

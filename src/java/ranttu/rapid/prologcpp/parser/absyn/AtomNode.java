/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

import ranttu.rapid.prologcpp.exp.UnexpectedToken;
import ranttu.rapid.prologcpp.parser.token.BasePrologToken;
import ranttu.rapid.prologcpp.parser.token.Number;
import ranttu.rapid.prologcpp.parser.token.Symbol;

/**
 * a prolog atom
 *
 * @author rapidhere@gmail.com
 * @version $id: AtomNode.java, v0.1 2017/1/13 dongwei.dq Exp $
 */
public class AtomNode extends AstNode {
    private BasePrologToken token;

    public AtomNode(BasePrologToken token) {
        if(! token.is(Number.class, Symbol.class)) {
            throw new UnexpectedToken(token);
        }

        this.token = token;
    }

    @Override
    public int getLineNo() {
        return token.getLineNo();
    }

    @Override
    public int getColumn() {
        return token.getColumn();
    }
}

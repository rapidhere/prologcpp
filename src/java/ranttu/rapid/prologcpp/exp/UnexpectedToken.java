/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.exp;

import ranttu.rapid.prologcpp.parser.token.BasePrologToken;

/**
 * unexpected token error
 *
 * @author rapidhere@gmail.com
 * @version $id: UnexpectedToken.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class UnexpectedToken extends PrologCompilingError {
    public UnexpectedToken(BasePrologToken token) {
        super(token.getLineNo(), token.getColumn(), "unexpected token: "
                                                    + token.getClass().getSimpleName() + " `"
                                                    + token.getValue() + "`");
    }
}

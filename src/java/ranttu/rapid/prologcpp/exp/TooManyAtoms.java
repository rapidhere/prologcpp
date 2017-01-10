/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.exp;

import ranttu.rapid.prologcpp.parser.absyn.FactNode;

/**
 * too many atoms for functor
 *
 * @author rapidhere@gmail.com
 * @version $id: TooManyAtoms.java, v0.1 2017/1/10 dongwei.dq Exp $
 */
public class TooManyAtoms extends PrologCompilingError {
    public TooManyAtoms(FactNode factNode) {
        super(factNode.getLineNo(), factNode.getColumn(), "too many atoms for this functor `"
                                                          + factNode.getFactId().getValue() + "/"
                                                          + factNode.getArguments().size() + "`");
    }
}

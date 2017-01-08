/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.absyn;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * the base prolog program
 *
 * @author rapidhere@gmail.com
 * @version $id: Program.java, v0.1 2017/1/8 dongwei.dq Exp $
 */
public class Program extends AstNode {
    private List<Fact> body;

    public Program() {
        body = new ArrayList<>();
    }

    public void add(Fact fact) {
        body.add(fact);
    }

    public List<Fact> getBody() {
        return ImmutableList.copyOf(body);
    }

    // ~ programs always start at 0, 0
    @Override
    public int getLineNo() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }
}

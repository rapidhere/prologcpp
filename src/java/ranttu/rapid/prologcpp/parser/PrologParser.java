/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser;


/**
 * Parse prolog to syntax tree
 *
 * @author rapidhere@gmail.com
 * @version $id: PrologParser.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class PrologParser {
    private PrologLexer lexer;

    public PrologParser(String prologCode) {
        lexer = new PrologLexer(prologCode);
    }
}

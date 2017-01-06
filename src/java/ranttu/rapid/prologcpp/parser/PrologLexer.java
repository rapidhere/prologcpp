/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser;

import com.google.common.collect.ImmutableList;
import ranttu.rapid.prologcpp.exp.UnexpectedCharacter;
import ranttu.rapid.prologcpp.exp.UnexpectedEOF;
import ranttu.rapid.prologcpp.parser.token.BasePrologToken;
import ranttu.rapid.prologcpp.parser.token.Comma;
import ranttu.rapid.prologcpp.parser.token.Dot;
import ranttu.rapid.prologcpp.parser.token.Number;
import ranttu.rapid.prologcpp.parser.token.PrologString;
import ranttu.rapid.prologcpp.parser.token.Symbol;
import ranttu.rapid.prologcpp.parser.token.Underline;

import java.util.List;

/**
 * a simple lexer
 * TODO: a lot to support
 *
 * @author rapidhere@gmail.com
 * @version $id: PrologLexer.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
public class PrologLexer {
    private String prologCode;
    private int currentTokenIndex;

    private int currentColumn, currentLineNo;

    public PrologLexer(String prologCode) {
        this.prologCode = prologCode;

        // lexer
        this.currentTokenIndex = 0;

        this.currentColumn = 0;
        this.currentLineNo = 0;
    }

    protected BasePrologToken findToken() {
        while(currentTokenIndex < prologCode.length()) {
            char ch = prologCode.charAt(currentTokenIndex);

            if(ch == '\'' || ch == '"') {
                return new PrologString(currentLineNo, currentColumn, onStateString(ch));
            }
        }

        // TODO
        return null;
    }

    private String onStateString(char stringStartChar) {
        // ignore first start char
        incPos(stringStartChar);

        int startPos = currentTokenIndex, endPos = -1;
        boolean escaping = false;

        while(currentTokenIndex < prologCode.length()) {
            char ch = prologCode.charAt(currentTokenIndex);
            incPos(ch);

            if(ch == '\n') {
                throw new UnexpectedCharacter(currentLineNo, currentColumn, ch);
            } else if(ch == '\\') {
                escaping = !escaping;
            } else if(ch == stringStartChar && !escaping) {
                endPos = currentTokenIndex;
                break;
            }
        }

        if(endPos == -1) {
            throw new UnexpectedEOF(currentLineNo, currentColumn);
        }

        return prologCode.substring(startPos, endPos);
    }

    private void incPos(char ch) {
        if(ch == '\n') {
            currentLineNo ++;
            currentTokenIndex = 0;
        } else {
            currentTokenIndex ++;
        }

        currentTokenIndex ++;
    }

    // Lexer tokens
    public final static List<Class<? extends BasePrologToken>> TOKENS = ImmutableList.of(
        Symbol.class,
        Number.class,
        Comma.class,
        Dot.class,
        Underline.class);
}

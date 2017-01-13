/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser;

import com.google.common.collect.ImmutableList;
import ranttu.rapid.prologcpp.exp.UnexpectedCharacter;
import ranttu.rapid.prologcpp.exp.UnexpectedEOF;
import ranttu.rapid.prologcpp.exp.UnexpectedToken;
import ranttu.rapid.prologcpp.parser.token.BasePrologToken;
import ranttu.rapid.prologcpp.parser.token.Comma;
import ranttu.rapid.prologcpp.parser.token.Dot;
import ranttu.rapid.prologcpp.parser.token.LeftParenthesis;
import ranttu.rapid.prologcpp.parser.token.Number;
import ranttu.rapid.prologcpp.parser.token.PrologString;
import ranttu.rapid.prologcpp.parser.token.QueryToken;
import ranttu.rapid.prologcpp.parser.token.RightParenthesis;
import ranttu.rapid.prologcpp.parser.token.Symbol;
import ranttu.rapid.prologcpp.parser.token.TokenPattern;
import ranttu.rapid.prologcpp.parser.token.Underline;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private BasePrologToken lastToken;

    public PrologLexer(String prologCode) {
        this.prologCode = prologCode;

        this.currentTokenIndex = 0;
        this.currentColumn = 0;
        this.currentLineNo = 1;
    }

    /**
     * test whether have token to return
     */
    public boolean hasNext() {
        if(lastToken != null) {
            return true;
        }

        lastToken = findToken(true);

        return lastToken != null;
    }

    /**
     * get current token from the string, and let lexer ready to get next token
     *
     * @return current token
     */
    public BasePrologToken nextToken() {
        if(lastToken == null) {
            lastToken = findToken(false);
        }

        BasePrologToken ret = lastToken;
        lastToken = null;
        return ret;
    }

    /**
     * get current token of specified type, and let lexer ready to get next token
     *
     * @return current token
     */
    public <T extends BasePrologToken> T nextToken(Class<T> clazz) {
        BasePrologToken ret = nextToken();
        try {
            return clazz.cast(ret);
        } catch (ClassCastException e) {
            throw new UnexpectedToken(ret);
        }
    }

    /**
     * get current token from the string, without setting lexer to get next token
     * @return current token
     */
    public BasePrologToken peekToken() {
        if(lastToken == null) {
            lastToken = findToken(false);
        }

        return lastToken;
    }

    protected BasePrologToken findToken(boolean allowEOF) {
        while (currentTokenIndex < prologCode.length()) {
            char ch = prologCode.charAt(currentTokenIndex);

            if (ch == '\'' || ch == '"') {
                return new PrologString(currentLineNo, currentColumn, onStateString(ch));
            } else if (Pattern.matches("\\s+", "" + ch)) {
                incPos(ch);
            } else {
                for (Class<? extends BasePrologToken> tokenCls : TOKENS) {
                    TokenPattern tokenPattern = tokenCls.getAnnotation(TokenPattern.class);

                    Pattern regex = Pattern.compile(tokenPattern.value());
                    Matcher matcher = regex.matcher(prologCode);
                    matcher.region(currentTokenIndex, prologCode.length());

                    // TODO: refine
                    if (matcher.lookingAt()) {
                        int startIdx = matcher.start(),
                            endIdx = matcher.end();

                        currentTokenIndex = endIdx;
                        currentColumn += endIdx - startIdx;

                        return newToken(tokenCls, startIdx, endIdx);
                    }
                }

                // if reach here, no pattern is matched
                throw new UnexpectedCharacter(currentLineNo, currentColumn, ch);
            }
        }

        // no more token is allowed
        if (allowEOF) {
            return null;
        } else {
            throw new UnexpectedEOF(currentLineNo, currentColumn);
        }
    }

    private BasePrologToken newToken(Class<? extends BasePrologToken> clazz, int startIdx, int endIdx) {
        try {
            return clazz.getDeclaredConstructor(int.class, int.class, String.class).
                newInstance(currentLineNo, currentColumn, prologCode.substring(startIdx, endIdx));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            // never reach
            throw new RuntimeException(e);
        }
    }

    private String onStateString(char stringStartChar) {
        // ignore first start char
        incPos(stringStartChar);

        int startPos = currentTokenIndex, endPos = -1;
        boolean escaping = false;

        while (currentTokenIndex < prologCode.length()) {
            char ch = prologCode.charAt(currentTokenIndex);
            incPos(ch);

            if (ch == '\n') {
                throw new UnexpectedCharacter(currentLineNo, currentColumn, ch);
            } else if (ch == '\\') {
                escaping = !escaping;
            } else if (ch == stringStartChar && !escaping) {
                endPos = currentTokenIndex;
                break;
            }
        }

        if (endPos == -1) {
            throw new UnexpectedEOF(currentLineNo, currentColumn);
        }

        return prologCode.substring(startPos, endPos);
    }

    private void incPos(char ch) {
        if (ch == '\n') {
            currentLineNo++;
            currentColumn = 0;
        } else {
            currentColumn++;
        }

        currentTokenIndex++;
    }

    // Lexer tokens
    public final static List<Class<? extends BasePrologToken>> TOKENS = ImmutableList.of(
        Symbol.class,
        Number.class,
        QueryToken.class,
        Comma.class,
        LeftParenthesis.class,
        RightParenthesis.class,
        Dot.class,
        Underline.class);
}

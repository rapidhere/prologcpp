/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

/**
 * the `?-` query token
 *
 * @author rapidhere@gmail.com
 * @version $id: QueryToken.java, v0.1 2017/1/13 dongwei.dq Exp $
 */
@TokenPattern("\\?-")
public class QueryToken extends BasePrologToken<String> {
    public QueryToken(int lineNo, int column, String raw) {
        super(lineNo, column, raw);
    }
}

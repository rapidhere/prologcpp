/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.parser.token;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * the token pattern annotation
 *
 * @author rapidhere@gmail.com
 * @version $id: TokenPattern.java, v0.1 2017/1/6 dongwei.dq Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenPattern {
    String value();
}

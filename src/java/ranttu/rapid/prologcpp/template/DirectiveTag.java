/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author rapidhere@gmail.com
 * @version $id: DirectiveTag.java, v0.1 2017/1/12 dongwei.dq Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectiveTag {
    String value();
}

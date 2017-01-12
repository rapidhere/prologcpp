/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.apache.velocity.runtime.directive.Directive;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author rapidhere@gmail.com
 * @version $id: DirectiveType.java, v0.1 2017/1/12 dongwei.dq Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectiveType {
    int value();

    int BLOCK = Directive.BLOCK;
    int LINE = Directive.LINE;
}

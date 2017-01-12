/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.apache.velocity.runtime.directive.Directive;

/**
 * abstract velocity directive
 *
 * @author rapidhere@gmail.com
 * @version $id: RVelocityDirective.java, v0.1 2017/1/12 dongwei.dq Exp $
 */
abstract public class RVelocityDirective extends Directive {
    @Override
    public String getName() {
        return getClass().getAnnotation(DirectiveTag.class).value();
    }

    @Override
    public int getType() {
        return getClass().getAnnotation(DirectiveType.class).value();
    }
}

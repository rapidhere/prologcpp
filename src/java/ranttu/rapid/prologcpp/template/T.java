/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import ranttu.rapid.prologcpp.$;
import ranttu.rapid.prologcpp.exp.PrologCppException;

import java.io.StringWriter;

/**
 * templates collections
 *
 * @author rapidhere@gmail.com
 * @version $id: T.java, v0.1 2017/1/10 dongwei.dq Exp $
 */
final public class T {
    // forbidden constructor
    private T() {
    }

    // ~ builder in context
    // TODO: support multi-thread
    private static StringWriter writer;

    public static void setWriter(StringWriter _writer) {
        writer = _writer;
    }

    // ~~~ templates
    public static final RTemplate
        Essential = define("essential"),
        ConstantTable = define("constant-table"),
        GenericFunctor = define("generic-functor"),
        SubFunctor = define("sub-functor"),
        TopFunctor = define("top-functor");

    /**
     * shortcut for define a template
     */
    private static RTemplate define(String templateName) {
        if(! hasInit) {
            Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            Velocity.setProperty("userdirective", OnelineDirective.class.getName());

            Velocity.init();
            hasInit = true;
        }

        return new RTemplate(templateName);
    }

    /** whether velocity inited */
    private static boolean hasInit = false;

    // ~~~ chain class
    public static class RTemplate {
        private VelocityContext context;
        private Template template;

        public RTemplate(String templateName) {
            template = Velocity.getTemplate("cpp-template/" + templateName + ".vm");
            context = new VelocityContext();
        }

        /**
         * add value to context
         */
        final public RTemplate with(String key, Object value) {
            context.put(key, value);
            return this;
        }

        /**
         * render and clear context
         */
        final public void render() {
            try {
                template.merge(context, writer);
            } catch (RuntimeException e) {
                throw getRealThrowable(e);
            }

            // clear context
            context = new VelocityContext();
        }

        /**
         * find PrologCppException in e's cause chain, if not found, return e
         */
        private RuntimeException getRealThrowable(RuntimeException e) {
            Throwable cur = e;
            while(cur != null) {
                if(cur instanceof PrologCppException) {
                    return $.required(cur);
                }

                cur = cur.getCause();
            }

            return e;
        }
    }
}

/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

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
    private static StringBuilder builder;

    public static void setBuilder(StringBuilder _builder) {
        builder = _builder;
    }

    // ~~~ templates
    public static final RTemplate
        Essential = new RTemplate("essential"),
        ConstantTable = new RTemplate("constant-table"),
        GenericFactTemplates = new RTemplate("generic-fact-templates"),
        SubFunctor = new RTemplate("sub-functor"),
        TopFunctors = new RTemplate("top-functors");

    // ~~~ chain class
    public static class RTemplate {
        private JtwigModel model;
        private JtwigTemplate template;

        public RTemplate(String templateName) {
            template = JtwigTemplate.classpathTemplate("cpp-template/" + templateName + ".twig");
            model = JtwigModel.newModel();
        }

        /**
         * add value to model
         */
        final public RTemplate with(String key, Object value) {
            model.with(key, value);
            return this;
        }

        /**
         * render and clear model
         */
        final public void render() {
            builder.append(template.render(model));
            model = JtwigModel.newModel();
        }
    }
}

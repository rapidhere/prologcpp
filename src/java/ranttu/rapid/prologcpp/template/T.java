/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;

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

    // ~ template environment
    private static EnvironmentConfiguration configuration;

    public static void setBuilder(StringBuilder _builder) {
        builder = _builder;
    }

    // ~~~ templates
    public static final RTemplate
        Essential = define("essential"),
        ConstantTable = define("constant-table"),
        GenericFunctor = define("generic-functor"),
        SubFunctor = define("sub-functor"),
        TopFunctor = define("top-functor");


    private static void initConfig() {
        configuration = EnvironmentConfigurationBuilder
            .configuration()
                .parser()
                    .addonParserProviders().add(new AddonParserProvider()).and()
                .and()
                .render()
                    .nodeRenders()
                        .add(OnelineNode.class, new OnelineNodeRender())
                    .and()
                .and()
            .build();
    }

    /**
     * shortcut for define a template
     */
    private static RTemplate define(String templateName) {
        if(configuration == null) {
            initConfig();
        }

        return new RTemplate(templateName);
    }

    // ~~~ chain class
    public static class RTemplate {
        private JtwigModel model;
        private JtwigTemplate template;

        public RTemplate(String templateName) {
            template = JtwigTemplate.classpathTemplate("cpp-template/" + templateName + ".twig",
                configuration);
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

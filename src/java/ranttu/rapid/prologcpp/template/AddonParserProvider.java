/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * parser provider
 *
 * @author rapidhere@gmail.com
 * @version $id: AddonParserProvider.java, v0.1 2017/1/11 dongwei.dq Exp $
 */
class AddonParserProvider implements org.jtwig.parser.addon.AddonParserProvider {
    @Override
    public Class<? extends org.jtwig.parser.parboiled.node.AddonParser> parser() {
        return AddonParser.class;
    }

    @Override
    public Collection<String> keywords() {
        return asList(
            AddonParser.KEYWORD_ONELINE,
            AddonParser.KEYWORD_END_ONELINE,
            AddonParser.KEYWORD_TAB);
    }
}

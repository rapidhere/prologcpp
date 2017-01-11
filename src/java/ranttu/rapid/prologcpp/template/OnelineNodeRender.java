/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.jtwig.environment.Environment;
import org.jtwig.escape.NoneEscapeEngine;
import org.jtwig.render.RenderRequest;
import org.jtwig.render.node.renderer.NodeRender;
import org.jtwig.renderable.Renderable;
import org.jtwig.renderable.StringBuilderRenderResult;
import org.jtwig.renderable.impl.StringRenderable;

import java.math.BigDecimal;

/**
 * render for online node
 *
 * @author rapidhere@gmail.com
 * @version $id: OnelineNodeRender.java, v0.1 2017/1/11 dongwei.dq Exp $
 */
class OnelineNodeRender implements NodeRender<OnelineNode> {
    @Override
    public Renderable render(RenderRequest renderRequest, OnelineNode node) {
        Environment env = renderRequest.getEnvironment();

        // render sub content
        Renderable subRender = env.getRenderEnvironment().getRenderNodeService()
            .render(renderRequest, node.getContent());

        int tabSize;
        if(node.getTabExpression().isPresent()) {
            Object val = env.getRenderEnvironment().getCalculateExpressionService().calculate(
                renderRequest,node.getTabExpression().get());

            if(! (val instanceof BigDecimal)) {
                tabSize = 0;
            } else {
                tabSize = ((BigDecimal)val).toBigInteger().intValue();
            }
        } else {
            tabSize = 0;
        }

        String content = subRender.appendTo(new StringBuilderRenderResult())
            .content().trim();

        return new StringRenderable(trimNewline(content, tabSize), NoneEscapeEngine.instance());
    }

    private String trimNewline(String raw, int tabSize) {
        String tab = "";
        for(int i = 0;i < tabSize;i ++) {
            tab += " ";
        }

        return tab + raw.replaceAll("\r*\n\\s*", "") + "\n";
    }
}

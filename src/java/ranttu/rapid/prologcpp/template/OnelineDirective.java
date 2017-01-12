/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.parser.node.ASTBlock;
import org.apache.velocity.runtime.parser.node.Node;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * oneline directive
 *
 * @author rapidhere@gmail.com
 * @version $id: OnelineDirective.java, v0.1 2017/1/12 dongwei.dq Exp $
 */
@DirectiveTag("ol")
@DirectiveType(DirectiveType.BLOCK)
public class OnelineDirective extends RVelocityDirective {
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
                                                                                   throws IOException,
                                                                                   ResourceNotFoundException,
                                                                                   ParseErrorException,
                                                                                   MethodInvocationException {
        String raw = "";

        for(int i = 0;i < node.jjtGetNumChildren();i ++) {
            Node child = node.jjtGetChild(i);
            if(child != null && child instanceof ASTBlock) {
                StringWriter sw = new StringWriter();
                child.render(context, sw);

                raw = sw.toString();
            }
        }

        writer.write(trim(raw));

        return true;
    }

    private String trim(String raw) {
        String ret = raw.replaceAll("\r*\n\\s*", "");
        return ret.replaceAll("\\s+", " ").trim();
    }
}

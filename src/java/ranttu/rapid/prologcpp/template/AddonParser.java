/**
 * rapidhere@gmail.com
 * Copyright (c) 1995-2017 All Rights Reserved.
 * ===> GLORY TO THE FIRST BORN! <===
 */
package ranttu.rapid.prologcpp.template;

import org.jtwig.parser.parboiled.ParserContext;
import org.jtwig.parser.parboiled.base.LexicParser;
import org.jtwig.parser.parboiled.base.LimitsParser;
import org.jtwig.parser.parboiled.base.PositionTrackerParser;
import org.jtwig.parser.parboiled.base.SpacingParser;
import org.jtwig.parser.parboiled.expression.AnyExpressionParser;
import org.jtwig.parser.parboiled.node.CompositeNodeParser;
import org.parboiled.Rule;

/**
 * addon parser for jtwig
 *
 * @author rapidhere@gmail.com
 * @version $id: AddonParser.java, v0.1 2017/1/11 dongwei.dq Exp $
 */
class AddonParser extends org.jtwig.parser.parboiled.node.AddonParser {
    static final String KEYWORD_ONELINE = "ol";
    static final String KEYWORD_END_ONELINE = "endol";
    static final String KEYWORD_TAB = "tab";

    public AddonParser(ParserContext context) {
        super(AddonParser.class, context);
    }

    @Override
    public Rule NodeRule() {
        PositionTrackerParser positionTrackerParser = parserContext().parser(PositionTrackerParser.class);
        LimitsParser limitsParser = parserContext().parser(LimitsParser.class);
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        LexicParser lexicParser = parserContext().parser(LexicParser.class);
        AnyExpressionParser anyExpressionParser = parserContext().parser(AnyExpressionParser.class);
        CompositeNodeParser compositeNodeParser = parserContext().parser(CompositeNodeParser.class);

        return Sequence(
            positionTrackerParser.PushPosition(),

            Sequence(
                limitsParser.startCode(),
                spacingParser.Spacing(),
                lexicParser.Keyword(KEYWORD_ONELINE),

                FirstOf(
                    Sequence(
                        spacingParser.Spacing(),
                        lexicParser.Keyword(KEYWORD_TAB),
                        spacingParser.Spacing(),
                        lexicParser.CharRange(':', ':'),
                        spacingParser.Spacing(),
                        anyExpressionParser.ExpressionRule()
                    ),
                    anyExpressionParser.push(null)
                ),

                spacingParser.Spacing(),
                limitsParser.endCode()
            ),

            compositeNodeParser.NodeRule(),

            Sequence(
                limitsParser.startCode(),
                spacingParser.Spacing(),
                lexicParser.Keyword(KEYWORD_END_ONELINE),
                spacingParser.Spacing(),
                limitsParser.endCode()
            ),

            push(new OnelineNode(
                positionTrackerParser.pop(2),
                compositeNodeParser.pop(),
                anyExpressionParser.pop()))
        );
    }
}

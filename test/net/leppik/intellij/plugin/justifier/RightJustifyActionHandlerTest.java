package net.leppik.intellij.plugin.justifier;

import junit.framework.TestCase;

public class RightJustifyActionHandlerTest extends TestCase {

    public void testSingleLineJustify() throws Exception {
        JustifierAsserter ja = new JustifierAsserter(new RightJustifyAction.RightJustifierFactory());
        ja.assertInternalConversion("", "");
        ja.assertInternalConversion("    ", "    ");
        ja.assertInternalConversion("Hello, you", "Hello, you");
        ja.assertInternalConversion(": Hello, you :", ": Hello, you :");

        ja.assertInternalConversion("    Hello, you", "    Hello, you");
        ja.assertInternalConversion("    Hello, you", "   Hello, you ");
        ja.assertInternalConversion("    Hello, you", "Hello, you    ");
    }

    public void testAddsSpacesToShortSelections() {
        RightJustifyAction.RightJustifierFactory factory = new RightJustifyAction.RightJustifierFactory();

        longThenShort: {
            String[] lines = {
                    "12345",
                    "5"
            };
            SelectionContext ctx = SelectionContext.context(lines, ".", "");

            final RightJustifyAction.RightJustifier justifier = factory.justifier(ctx.selections, ctx.text);
            assertEquals("12345", justifier.justify(ctx.selections.get(0), ctx.text));
            assertEquals("    5", justifier.justify(ctx.selections.get(1), ctx.text));
        }
        shortThenLong: {
            String[] lines = {
                    "5",
                    "12345"
            };
            SelectionContext ctx = SelectionContext.context(lines, ".", "");

            final RightJustifyAction.RightJustifier justifier = factory.justifier(ctx.selections, ctx.text);
            assertEquals("    5", justifier.justify(ctx.selections.get(0), ctx.text));
            assertEquals("12345", justifier.justify(ctx.selections.get(1), ctx.text));
        }
    }
}
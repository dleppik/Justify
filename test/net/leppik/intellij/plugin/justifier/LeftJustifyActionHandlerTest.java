package net.leppik.intellij.plugin.justifier;

import junit.framework.TestCase;

public class LeftJustifyActionHandlerTest extends TestCase {

    public void testJustify() throws Exception {
        JustifierAsserter ja = new JustifierAsserter(new LeftJustifyAction.LeftJustifierFactory());

        ja.assertInternalConversion("", "");
        ja.assertInternalConversion("Hello, you", "Hello, you");
        ja.assertInternalConversion(": Hello, you", ": Hello, you"); // Make sure spaces don't get eaten
        ja.assertInternalConversion("Hello, you :", "Hello, you :");


        ja.assertInternalConversion("Hello, you    ", "Hello, you    ");
        ja.assertInternalConversion("Hello, you    ", " Hello, you   ");
        ja.assertInternalConversion("Hello, you    ", "    Hello, you");
    }
}
package net.leppik.intellij.plugin.justifier;

import junit.framework.TestCase;

import java.util.Arrays;

public class JustifierAsserter {
    public final JustifierFactory factory;

    public JustifierAsserter(JustifierFactory factory) {
        this.factory = factory;
    }

    public void assertInternalConversion(String expected, String initial) {
        String line = "|"+initial+"|";
        SelectionInLine selection = new SelectionInLine(0, line.length(), 1, line.length()-1);
        Justifier justifier = factory.justifier(Arrays.asList(selection), line);
        TestCase.assertEquals(expected, justifier.justify(selection, line));
    }


}

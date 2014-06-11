package net.leppik.intellij.plugin.justifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectionContext {
    public final List<SelectionInLine> selections;
    public final String text;

    public static SelectionContext context(String[] lines, String linePrefix, String lineSuffix) {
        List<SelectionInLine> selections = new ArrayList<SelectionInLine>(lines.length);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            int lineStart = sb.length();
            int selectionStart = lineStart+linePrefix.length();
            int selectionEnd = selectionStart+line.length();
            int lineEnd = selectionEnd+lineSuffix.length();
            SelectionInLine sel = new SelectionInLine(lineStart, lineEnd, selectionStart, selectionEnd);
            selections.add(sel);
            sb.append(linePrefix + line + lineSuffix + "\n");
        }
        return new SelectionContext(selections, sb.toString());
    }

    public SelectionContext(List<SelectionInLine> selections, String text) {
        this.selections = Collections.unmodifiableList(selections);
        this.text = text;
    }
}

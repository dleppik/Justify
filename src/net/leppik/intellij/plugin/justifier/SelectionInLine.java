package net.leppik.intellij.plugin.justifier;

/**
 * Describes a selected string and the line that contains it.
 */
public class SelectionInLine {
    final int lineStart;
    final int lineEnd;
    final int selectionStart;
    final int selectionEnd;

    public SelectionInLine(int lineStart, int lineEnd, int selectionStart, int selectionEnd) {
        this.lineStart      = lineStart;
        this.lineEnd        = lineEnd;
        this.selectionStart = selectionStart;
        this.selectionEnd   = selectionEnd;

        if (selectionStart < lineStart  ||  lineEnd < selectionEnd) {
            throw new IllegalArgumentException("Selection is out of bounds");
        }
        if (lineEnd < lineStart) {
            throw new IllegalArgumentException("Line has negative length");
        }
        if (selectionEnd < selectionStart) {
            throw new IllegalArgumentException("Selection has negative length");
        }
    }


    public CharSequence line(     CharSequence chars) { return chars.subSequence(lineStart,      lineEnd); }
    public CharSequence selection(CharSequence chars) { return chars.subSequence(selectionStart, selectionEnd); }
}

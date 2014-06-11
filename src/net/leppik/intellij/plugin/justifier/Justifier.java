package net.leppik.intellij.plugin.justifier;

/**
 * Justifies lines from a list provided by its {@link JustifierFactory}.
 */
public interface Justifier {

    /** Given a selection in a line, return replacement text for just the selection. */
    public String justify(SelectionInLine selection, CharSequence charSequence);
}

package net.leppik.intellij.plugin.justifier;

import java.util.List;

/**
 * Analyzes a list of selections and produces a Justifier that has been initialized to justify those exact lines.
 */
public interface JustifierFactory {
    public Justifier justifier(List<SelectionInLine> selections, CharSequence charSequence);
}

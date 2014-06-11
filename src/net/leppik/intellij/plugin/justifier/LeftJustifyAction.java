package net.leppik.intellij.plugin.justifier;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

import java.util.List;

/**
 * Moves spaces on the left to the right.
 */
public class LeftJustifyAction extends TextComponentEditorAction {
    protected LeftJustifyAction() { super(new LeftJustifyActionHandler()); }

    static class LeftJustifyActionHandler extends JustifyActionHandler {
        LeftJustifyActionHandler() {
            super(new LeftJustifierFactory());
        }

    }

    static class LeftJustifierFactory implements JustifierFactory {
        @Override
        public LeftJustifier justifier(List<SelectionInLine> selections, CharSequence charSequence) {
            return new LeftJustifier();
        }
    }

    static class LeftJustifier implements Justifier {
        @Override
        public String justify(SelectionInLine selection, CharSequence charSequence) {
            String text = selection.selection(charSequence).toString();
            boolean keepTrailingSpaces = selection.lineEnd != selection.selectionEnd;

            StringBuilder sb = new StringBuilder();
            boolean inLeadingSpaces = true;
            int leadingSpaceCount = 0;

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (inLeadingSpaces) {
                    if (c == ' ') {
                        leadingSpaceCount++;
                        continue;
                    }
                    inLeadingSpaces = false;
                }

                // Past leading spaces

                if (keepTrailingSpaces ||  c != ' ')
                    sb.append(c);

            }
            for (int i=0; i<leadingSpaceCount; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }
}

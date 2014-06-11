package net.leppik.intellij.plugin.justifier;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;

import java.util.List;

public class RightJustifyAction extends TextComponentEditorAction {
    protected RightJustifyAction() { super(new RightJustifyActionHandler()); }

    static class RightJustifyActionHandler extends JustifyActionHandler {
        RightJustifyActionHandler() {
            super(new RightJustifierFactory());
        }
    }

    static class RightJustifierFactory implements JustifierFactory {
        @Override
        public RightJustifier justifier(List<SelectionInLine> selections, CharSequence charSequence) {
            int justifiedLength = 0;
            for (SelectionInLine selection : selections) {
                int length = selection.selectionEnd-selection.selectionStart;
                if (justifiedLength < length)
                    justifiedLength = length;
            }
            return new RightJustifier(justifiedLength);
        }
    }

    static class RightJustifier implements Justifier {
        private final int justifiedLength;

        RightJustifier(int justifiedLength) {
            this.justifiedLength = justifiedLength;
        }


        @Override
        public String justify(SelectionInLine selection, CharSequence charSequence) {
            return justify(selection.selection(charSequence).toString());
        }

        public String justify(String text) {
            StringBuilder sb = new StringBuilder();
            String trimmed = text.trim();

            int numSpaces = justifiedLength - trimmed.length();
            for (int i=0; i<numSpaces; i++) {
                sb.append(' ');
            }
            sb.append(trimmed);
            return sb.toString();
        }
    }
}
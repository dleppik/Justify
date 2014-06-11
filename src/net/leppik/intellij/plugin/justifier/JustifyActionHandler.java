package net.leppik.intellij.plugin.justifier;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
* Created by dleppik on 5/29/14.
*/
public abstract class JustifyActionHandler extends EditorWriteActionHandler {

    private final JustifierFactory justifierFactory;

    protected JustifyActionHandler(JustifierFactory justifierFactory) {
        super(false);  // Don't run for each carat
        this.justifierFactory = justifierFactory;
    }

    @Override
    public void executeWriteAction(final Editor editor, @Nullable Caret caret, final DataContext dataContext) {
        if (editor.getCaretModel().supportsMultipleCarets()) {
            final CaretModel caretModel = editor.getCaretModel();

            caretModel.runBatchCaretOperation(new Runnable() {
                @Override
                public void run() {
                    final Document doc = editor.getDocument();
                    List<SelectionInLine> selections = new ArrayList<SelectionInLine>();
                    for (Caret caret : caretModel.getAllCarets()) {
                        SelectionInLine sel = selectionInLine(doc, caret);
                        if (sel != null)
                            selections.add(sel);
                    }
                    CharSequence chars = editor.getDocument().getCharsSequence();
                    Justifier justifier = justifierFactory.justifier(selections, chars);

                    for (Caret caret : caretModel.getAllCarets()) {
                        SelectionInLine selection = selectionInLine(doc, caret);
                        if (selection != null) {
                            String replacement = justifier.justify(selection, chars);
                            doc.replaceString(selection.selectionStart, selection.selectionEnd, replacement);
                        }
                    }
                }
            });
        }
    }

    private SelectionInLine selectionInLine(Document doc, Caret caret) {
        SelectionInLine sel = null;
        int selectionStart = caret.getSelectionStart();
        int selectionEnd = caret.getSelectionEnd();
        int lineNum = doc.getLineNumber(selectionStart);
        if (doc.getLineNumber(selectionEnd) == lineNum) {  // For now, support only 1 line
            int lineStart = doc.getLineStartOffset(lineNum);
            int lineEnd = doc.getLineEndOffset(lineNum);
            sel = new SelectionInLine(lineStart, lineEnd, selectionStart, selectionEnd);
        }
        return sel;
    }
}

package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.jabref.gui.JabRefFrame;
import org.jabref.gui.worker.bibsonomy.DeletePostsWorker;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;

/**
 * {@link DeleteSelectedEntriesAction} runs the {@link DeletePostsWorker}.
 */
public class DeleteSelectedEntriesAction extends AbstractBibSonomyAction {

    public void actionPerformed(ActionEvent e) {
        DeletePostsWorker worker = new DeletePostsWorker(getJabRefFrame(), getJabRefFrame().getCurrentBasePanel().getSelectedEntries().toArray(new BibEntry[0]));
        performAsynchronously(worker);
    }

    public DeleteSelectedEntriesAction(JabRefFrame jabRefFrame) {
        super(jabRefFrame, Localization.lang("Delete selected entries"), new ImageIcon(DeleteSelectedEntriesAction.class.getResource("/images/bibsonomy/document--minus.png")));
    }
}

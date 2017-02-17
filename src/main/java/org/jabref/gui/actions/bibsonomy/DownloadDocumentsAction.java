package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.ImageIcon;

import org.jabref.gui.JabRefFrame;
import org.jabref.gui.worker.bibsonomy.DownloadDocumentsWorker;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.entry.BibEntry;

/**
 * {@link DownloadDocumentsAction} collects all entries of a the {@link org.jabref.model.database.BibDatabase BibDatabase} and fetches all documents available for the post.
 */
public class DownloadDocumentsAction extends AbstractBibSonomyAction {

    public DownloadDocumentsAction(JabRefFrame jabRefFrame) {
        super(jabRefFrame, Localization.lang("Download my documents"), new ImageIcon(DownloadDocumentsAction.class.getResource("/images/bibsonomy/document-pdf-text.png")));
    }

    public void actionPerformed(ActionEvent e) {
        if (getJabRefFrame().getCurrentBasePanel() != null) {
            Collection<BibEntry> entries = getJabRefFrame().getCurrentBasePanel().getDatabase().getEntries();
            for (BibEntry entry : entries) {
                DownloadDocumentsWorker worker = new DownloadDocumentsWorker(getJabRefFrame(), entry, false);
                performAsynchronously(worker);
            }
        }
    }
}

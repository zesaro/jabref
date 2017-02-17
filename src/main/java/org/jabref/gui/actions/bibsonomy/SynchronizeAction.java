package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import org.jabref.gui.JabRefFrame;
import org.jabref.gui.worker.bibsonomy.SynchronizationWorker;
import org.jabref.logic.l10n.Localization;


/**
 * {@link SynchronizeAction} runs the {@link SynchronizationWorker}.
 */
public class SynchronizeAction extends AbstractBibSonomyAction {

    public void actionPerformed(ActionEvent e) {
        SynchronizationWorker worker = new SynchronizationWorker(getJabRefFrame());
        performAsynchronously(worker);
    }

    public SynchronizeAction(JabRefFrame jabRefFrame) {
        super(jabRefFrame, Localization.lang("Synchronize"), new ImageIcon(SynchronizeAction.class.getResource("/images/bibsonomy/arrow-circle-double-135.png")));

    }
}

package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.jabref.gui.bibsonomy.BibSonomySettingsDialog;
import org.jabref.logic.l10n.Localization;

/**
 * {@link CloseBibSonomySettingsDialogByCancelAction} closes the {@link BibSonomySettingsDialog} without saving the properties
 */
public class CloseBibSonomySettingsDialogByCancelAction extends AbstractAction {

    private BibSonomySettingsDialog settingsDialog;

    public void actionPerformed(ActionEvent e) {
        settingsDialog.setVisible(false);
    }

    public CloseBibSonomySettingsDialogByCancelAction(BibSonomySettingsDialog settingsDialog) {
        super(Localization.lang("Cancel"), new ImageIcon(CloseBibSonomySettingsDialogByCancelAction.class.getResource("/images/bibsonomy/cross.png")));
        this.settingsDialog = settingsDialog;
    }

}

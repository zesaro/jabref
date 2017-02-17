package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;

import org.jabref.gui.JabRefFrame;
import org.jabref.gui.dbproperties.DatabasePropertiesDialog;

public class OpenDatabasePropertiesAction extends AbstractBibSonomyAction {

    private DatabasePropertiesDialog databasePropertiesDialog;

    public OpenDatabasePropertiesAction(JabRefFrame jabRefFrame) {
        super(jabRefFrame);
    }

    public void actionPerformed(ActionEvent e) {
        if (databasePropertiesDialog == null) {
            databasePropertiesDialog = new DatabasePropertiesDialog(getJabRefFrame());
            databasePropertiesDialog.setPanel(getJabRefFrame().getCurrentBasePanel());
        }
        databasePropertiesDialog.setVisible(true);
    }

}

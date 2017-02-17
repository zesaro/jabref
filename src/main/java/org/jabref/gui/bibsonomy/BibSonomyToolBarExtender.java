package org.jabref.gui.bibsonomy;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JToolBar;

import org.jabref.bibsonomy.BibSonomyGlobals;
import org.jabref.bibsonomy.BibSonomySidePaneComponent;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.actions.bibsonomy.DeleteSelectedEntriesAction;
import org.jabref.gui.actions.bibsonomy.ExportSelectedEntriesAction;
import org.jabref.gui.actions.bibsonomy.ToggleSidePaneComponentAction;


/**
 * {@link BibSonomyToolBarExtender} add the service specific buttons to the tool bar.
 */
public class BibSonomyToolBarExtender {

    public static void extend(JabRefFrame jabRefFrame, BibSonomySidePaneComponent sidePaneComponent) {

        for (Component rp : jabRefFrame.getComponents()) {

            if (rp instanceof JRootPane) {

                for (Component lp : ((JRootPane) rp).getComponents()) {

                    if (lp instanceof JLayeredPane) {

                        for (Component p : ((JLayeredPane) lp).getComponents()) {

                            if (p instanceof JPanel) {

                                for (Component tb : ((JPanel) p).getComponents()) {

                                    if (tb instanceof JToolBar) {

                                        JToolBar toolBar = (JToolBar) tb;

                                        JButton searchEntries = new JButton(new ToggleSidePaneComponentAction(sidePaneComponent));
                                        searchEntries.setText(BibSonomyGlobals.BIBSONOMY_NAME);
                                        toolBar.add(searchEntries, 5);

                                        JButton exportEntries = new JButton(new ExportSelectedEntriesAction(jabRefFrame));
                                        exportEntries.setText(null);
                                        toolBar.add(exportEntries, 6);

                                        JButton deleteEntries = new JButton(new DeleteSelectedEntriesAction(jabRefFrame));
                                        deleteEntries.setText(null);
                                        toolBar.add(deleteEntries, 7);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

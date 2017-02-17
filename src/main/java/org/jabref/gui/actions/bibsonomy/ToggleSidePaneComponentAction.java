package org.jabref.gui.actions.bibsonomy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.jabref.bibsonomy.BibSonomySidePaneComponent;
import org.jabref.gui.JabRefFrame;
import org.jabref.gui.SidePaneManager;
import org.jabref.gui.bibsonomy.BibSonomySidePanel;
import org.jabref.logic.l10n.Localization;

/**
 * Display or hide the {@link BibSonomySidePanel}.
 */
public class ToggleSidePaneComponentAction extends AbstractAction {

    private SidePaneManager manager;
    private JabRefFrame jabRefFrame;

    private BibSonomySidePaneComponent sidePaneComponent;

    public void actionPerformed(ActionEvent e) {
        if (!manager.hasComponent(BibSonomySidePaneComponent.class)) {
            manager.register(sidePaneComponent);
        }

        if (jabRefFrame.getTabbedPane().getTabCount() > 0) {
            manager.toggle(BibSonomySidePaneComponent.class);
        }

    }

    public ToggleSidePaneComponentAction(BibSonomySidePaneComponent sidePaneComponent) {
        super(Localization.lang("Search entries"), new ImageIcon(ToggleSidePaneComponentAction.class.getResource("/images/bibsonomy/tag-label.png")));

        this.sidePaneComponent = sidePaneComponent;
        this.manager = sidePaneComponent.getSidePaneManager();
        this.jabRefFrame = sidePaneComponent.getJabRefFrame();
    }
}

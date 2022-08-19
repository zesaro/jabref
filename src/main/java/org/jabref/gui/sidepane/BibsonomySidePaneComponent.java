package org.jabref.gui.sidepane;

import org.jabref.gui.DialogService;
import org.jabref.gui.actions.SimpleCommand;
import org.jabref.preferences.BibsonomyPreferences;

public class BibsonomySidePaneComponent extends SidePaneComponent {

    private final BibsonomyPreferences bibsonomyPreferences;
    private final DialogService dialogService;

    public BibsonomySidePaneComponent(SidePaneType sidePaneType, SimpleCommand closeCommand, SimpleCommand moveUpCommand, SimpleCommand moveDownCommand, SidePaneContentFactory contentFactory, BibsonomyPreferences bibsonomyPreferences, DialogService dialogService) {
        super(sidePaneType, closeCommand, moveUpCommand, moveDownCommand, contentFactory);
        this.bibsonomyPreferences = bibsonomyPreferences;
        this.dialogService = dialogService;
    }
}

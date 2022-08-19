package org.jabref.gui.bibsonomy;

import javax.swing.undo.UndoManager;

import javafx.scene.layout.BorderPane;

import org.jabref.gui.DialogService;
import org.jabref.gui.StateManager;
import org.jabref.gui.util.TaskExecutor;
import org.jabref.preferences.BibsonomyPreferences;
import org.jabref.preferences.PreferencesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ToDo: implement
 */
public class BibsonomyPanel extends BorderPane {

    private static final Logger LOGGER = LoggerFactory.getLogger(BibsonomyPanel.class);

    private final StateManager stateManager;
    private final DialogService dialogService;
    private final TaskExecutor taskExecutor;
    private final PreferencesService preferencesService;
    private final BibsonomyPreferences bibsonomyPreferences;
    private final UndoManager undoManager;

    public BibsonomyPanel(TaskExecutor taskExecutor,
                          StateManager stateManager,
                          PreferencesService preferences,
                          BibsonomyPreferences bibsonomyPreferences,
                          DialogService dialogService,
                          UndoManager undoManager) {
        this.taskExecutor = taskExecutor;
        this.stateManager = stateManager;
        this.preferencesService = preferences;
        this.bibsonomyPreferences = bibsonomyPreferences;
        this.dialogService = dialogService;
        this.undoManager = undoManager;
        initialize();
    }

    private void initialize() { }
}

package org.jabref.gui.preferences.bibsonomy;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.jabref.gui.preferences.AbstractPreferenceTabView;
import org.jabref.gui.preferences.PreferencesTab;

import com.airhacks.afterburner.views.ViewLoader;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;

public class BibsonomyTab extends AbstractPreferenceTabView<BibsonomyTabViewModel> implements PreferencesTab {

    private final ControlsFxVisualizer validationVisualizer = new ControlsFxVisualizer();
    @FXML private Label usernameLabel;
    @FXML private TextField username;
    @FXML private TextField apiKey;
    @FXML private Label apiKeyLabel;
    @FXML private TextField applicationUrl;
    @FXML private Label applicationUrlLabel;
    @FXML private CheckBox storeApiKey;
    @FXML private Label apiKeyHintLabel;

    public BibsonomyTab() {
        ViewLoader.view(this).root(this).load();
    }

    @Override
    public String getTabName() {
        return "BibSonomy";
    }

    public void initialize() {
        this.viewModel = new BibsonomyTabViewModel(dialogService, preferencesService, preferencesService.getBibsonomyPreferences(), preferencesService.getTelemetryPreferences(), preferencesService.getOwnerPreferences(), preferencesService.getTimestampPreferences());
    }
}

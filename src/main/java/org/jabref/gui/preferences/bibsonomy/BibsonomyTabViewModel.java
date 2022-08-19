package org.jabref.gui.preferences.bibsonomy;

import org.jabref.gui.DialogService;
import org.jabref.gui.preferences.PreferenceTabViewModel;
import org.jabref.logic.preferences.OwnerPreferences;
import org.jabref.logic.preferences.TimestampPreferences;
import org.jabref.preferences.BibsonomyPreferences;
import org.jabref.preferences.PreferencesService;
import org.jabref.preferences.TelemetryPreferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BibsonomyTabViewModel implements PreferenceTabViewModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(BibsonomyTabViewModel.class);

    private final DialogService dialogService;
    private final BibsonomyPreferences bibsonomyPreferences;
    private final PreferencesService preferencesService;
    private final TelemetryPreferences telemetryPreferences;
    private final OwnerPreferences ownerPreferences;
    private final TimestampPreferences timestampPreferences;

    public BibsonomyTabViewModel(DialogService dialogService, PreferencesService preferencesService,
                                 BibsonomyPreferences bibsonomyPreferences, TelemetryPreferences telemetryPreferences,
                                 OwnerPreferences ownerPreferences, TimestampPreferences timestampPreferences) {
        this.dialogService = dialogService;
        this.bibsonomyPreferences = bibsonomyPreferences;
        this.preferencesService = preferencesService;
        this.telemetryPreferences = telemetryPreferences;
        this.ownerPreferences = ownerPreferences;
        this.timestampPreferences = timestampPreferences;
    }

    @Override
    public void setValues() {
    }

    @Override
    public void storeSettings() {
    }
}

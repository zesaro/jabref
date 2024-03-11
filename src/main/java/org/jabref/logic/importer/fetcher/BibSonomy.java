package org.jabref.logic.importer.fetcher;

import java.util.Objects;

import org.jabref.logic.importer.ImportFormatPreferences;
import org.jabref.logic.importer.ImporterPreferences;

public class BibSonomy implements CustomizableKeyFetcher {

    private final String API_URL = "https://www.bibsonomy.org/api";

    private final ImporterPreferences importerPreferences;
    private final ImportFormatPreferences preferences;

    public BibSonomy(ImportFormatPreferences preferences, ImporterPreferences importerPreferences) {
        this.preferences = Objects.requireNonNull(preferences);
        this.importerPreferences = importerPreferences;
    }

    @Override
    public String getName() {
        return "BibSonomy";
    }

    // @Override
    // public Optional<HelpFile> getHelpPage() {
    //  return CustomizableKeyFetcher.super.getHelpPage();
    // }

    // @Override
    // public URLDownload getUrlDownload(URL url) {
    //  return CustomizableKeyFetcher.super.getUrlDownload(url);
    // }

    @Override
    public String getTestUrl() {
        return API_URL;
    }
}

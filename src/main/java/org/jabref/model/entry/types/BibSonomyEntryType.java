package org.jabref.model.entry.types;

import java.util.Locale;

public enum BibSonomyEntryType implements EntryType {
    Presentation("Presentation"),
    Preprint("preprint");

    private final String displayName;

    BibSonomyEntryType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getName() {
        return displayName.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}

package org.jabref.model.entry.field;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

/**
 * BibSonomy fields. These are not normal fields but mostly place holders with special functions.
 */
public enum BibSonomyField implements Field {
    DESCRIPTION("description"),
    INTERHASH("interhash"),
    INTRAHASH("intrahash"),
    USERNAME("username"),
    MISC_ID("misc_id");

    private final String name;
    private final Set<FieldProperty> properties;

    BibSonomyField(String name) {
        this.name = name;
        this.properties = EnumSet.noneOf(FieldProperty.class);
    }

    BibSonomyField(String name, FieldProperty first, FieldProperty... rest) {
        this.name = name;
        this.properties = EnumSet.of(first, rest);
    }

    @Override
    public Set<FieldProperty> getProperties() {
        return Collections.unmodifiableSet(properties);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isStandardField() {
        return false;
    }

    public static <T> Optional<BibSonomyField> fromName(String name) {
        return Arrays.stream(BibSonomyField.values())
                     .filter(field -> field.getName().equalsIgnoreCase(name))
                     .findAny();
    }
}

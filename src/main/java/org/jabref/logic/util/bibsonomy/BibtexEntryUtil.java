package org.jabref.logic.util.bibsonomy;

import java.util.Set;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.Field;
import org.jabref.model.strings.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper methods for BibtexEntry objects (the internal jabref representation)
 *
 * @author Dominik Benz <benz@cs.uni-kassel.de>
 */
@SuppressWarnings("checkstyle:MissingDeprecated")
public class BibtexEntryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BibtexEntryUtil.class);

    /**
     * Check the (string) equality of two BibTex entries
     */
    public static boolean areEqual(final BibEntry b1, final BibEntry b2) {
        final Set<Field> commonFields = b1.getFields();
        commonFields.addAll(b2.getFields());

        LOGGER.debug("Total nr. of common fields: " + commonFields.size());

        for (final Field field : commonFields) {
            LOGGER.debug("Comparing field: " + field);

            // fields that should be ignored
            if ((field != null) && !field.getName().startsWith("__")) {
                final String fieldName = field.getName();
                if (!"id".equals(fieldName) && !"".equals(fieldName) && !"timestamp".equals(fieldName) && !"owner".equals(fieldName)) {
                    // check if b1 lacks a field that b2 has
                    if (StringUtil.isEmpty(b1.getField(field).orElse("")) && !StringUtil.isEmpty(b2.getField(field).orElse(""))) {
                        LOGGER.debug("field " + field + " is null for b1 but not for b2");
                        return false;
                    }
                    // check if b2 lacks a field that b1 has
                    if (StringUtil.isEmpty(b2.getField(field).orElse("")) && !StringUtil.isEmpty(b1.getField(field).orElse(""))) {
                        LOGGER.debug("field " + field + " is null for b2 but not for b1");
                        return false;
                    }
                    // check if both are empty/null -> OK
                    if (StringUtil.isEmpty(b1.getField(field).orElse("")) && StringUtil.isEmpty(b2.getField(field).orElse(""))) {
                        continue;
                    }
                    // check for fields of b1 if they are the same in b2
                    if (!b1.getField(field).equals(b2.getField(field))) {
                        LOGGER.debug("Found inequality for field: " + field);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

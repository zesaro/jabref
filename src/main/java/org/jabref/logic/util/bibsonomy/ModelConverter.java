package org.jabref.logic.util.bibsonomy;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.BibEntryType;
import org.jabref.model.entry.Month;
import org.jabref.model.entry.field.BibSonomyField;
import org.jabref.model.entry.field.Field;
import org.jabref.model.entry.field.FieldFactory;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.BibtexEntryTypeDefinitions;
import org.jabref.model.strings.StringUtil;
import org.jabref.preferences.JabRefPreferences;

import org.bibsonomy.model.BibTex;
import org.bibsonomy.model.Group;
import org.bibsonomy.model.Post;
import org.bibsonomy.model.Resource;
import org.bibsonomy.model.Tag;
import org.bibsonomy.model.User;
import org.bibsonomy.model.util.PersonNameParser.PersonListParserException;
import org.bibsonomy.model.util.PersonNameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.bibsonomy.util.ValidationUtils.present;

/**
 * Converts between BibSonomy's and JabRef's BibTeX model.
 */
public class ModelConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelConverter.class);

    private static final Set<String> EXCLUDE_FIELDS = new HashSet<>(Arrays.asList("abstract", // added separately
            "bibtexAbstract", // added separately
            "bibtexkey", "entrytype", // added at beginning of entry
            "misc", // contains several fields; handled separately
            "month", // handled separately
            "openURL", // not added
            "simHash0", // not added
            "simHash1", // not added
            "simHash2", // not added
            "simHash3", // not added
            "description", "keywords", "comment", "id"));

    /**
     * dates in JabRef are stored as strings, in BibSonomy as Date objects. We have to supply two formats - the first is the one which exists when having downloaded entries from BibSonomy, the second one when entries were created from scratch within JabRef.
     */
    private static final SimpleDateFormat BIBSONOMY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final SimpleDateFormat JABREF_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

    // ToDo: Use {@link PreferencesService}
    // separates tags
    private static final String JABREF_KEYWORD_SEPARATOR = JabRefPreferences.getInstance().get("groupKeywordSeparator", ", ");

    /**
     * Converts a BibSonomy post into a JabRef BibEntry
     *
     * @param post A Post you need to convert
     * @return An optional BibEntry
     * @since 3.7
     */
    public static Optional<BibEntry> convertPostOptional(final Post<? extends Resource> post) {
        BibEntry entry = convertPost(post);
        if (entry != null) {
            return Optional.of(entry);
        }
        return Optional.empty();
    }

    /**
     * Converts a BibSonomy post into a JabRef BibEntry
     *
     * @param post The post you want to convert
     * @return A compatible BibEntry for JabRef
     */
    private static BibEntry convertPost(final Post<? extends Resource> post) {
        try {
            final BibTex bibtex = (BibTex) post.getResource();
            final BibEntry entry = new BibEntry();

            // JabRef generates an ID for the entry
            copyStringProperties(entry, bibtex);

            List<String> authorString = new LinkedList<>();
            bibtex.getAuthor().forEach(author -> authorString.add(author.toString()));
            if (!authorString.isEmpty()) {
                entry.setField(StandardField.AUTHOR, StringUtil.stripBrackets(authorString.toString()));
            } else {
                entry.clearField(StandardField.AUTHOR);
            }

            List<String> editorString = new LinkedList<>();
            bibtex.getEditor().forEach(editor -> editorString.add(editor.toString()));
            if (!editorString.isEmpty()) {
                entry.setField(StandardField.EDITOR, StringUtil.stripBrackets(editorString.toString()));
            } else {
                entry.clearField(StandardField.EDITOR);
            }

            /*
             * convert entry type (Is never null but getType() returns null for
             * unknown types and JabRef knows less types than we.)
             *
             * FIXME: a nicer solution would be to implement the corresponding classes for the missing entrytypes.
             *  @see org.jabref.model.entry.types.BibSonomyEntryType
             */
            Optional<BibEntryType> optEntryType = getEntryType(bibtex);
            if (optEntryType.isPresent()) {
                entry.setType(optEntryType.get().getType());

                copyMiscProperties(entry, bibtex);

                copyMonth(entry, bibtex);

                final String bibAbstract = bibtex.getAbstract();
                if (present(bibAbstract)) {
                    entry.setField(StandardField.ABSTRACT, bibAbstract);
                }

                copyTags(entry, post);

                copyGroups(entry, post);

                // set comment + description
                final String description = post.getDescription();
                if (present(description)) {
                    entry.setField(BibSonomyField.DESCRIPTION, post.getDescription());
                    entry.setField(StandardField.COMMENT, post.getDescription());
                }

                if (present(post.getDate())) {
                    entry.setField(StandardField.TIMESTAMP, BIBSONOMY_DATE_FORMAT.format(post.getDate()));
                }

                if (present(post.getUser())) {
                    entry.setField(BibSonomyField.USERNAME, post.getUser().getName());
                }

                return entry;
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Could not convert BibSonomy post into a JabRef BibTeX entry.", e);
        }

        return null;
    }

    public static void copyGroups(final BibEntry entry, final Post<? extends Resource> post) {
        // set groups - will be used in jabref when exporting to bibsonomy
        if (present(post.getGroups())) {
            final Set<Group> groups = post.getGroups();
            final StringBuilder groupsBuffer = new StringBuilder();

            for (final Group group : groups) {
                groupsBuffer.append(group.getName()).append(" ");
            }

            final String groupsBufferString = groupsBuffer.toString().trim();
            if (present(groupsBufferString)) {
                entry.setField(StandardField.GROUPS, groupsBufferString);
            }
        }
    }

    public static void copyTags(final BibEntry entry, final Post<? extends Resource> post) {
        // concatenate tags using the JabRef keyword separator
        final Set<Tag> tags = post.getTags();
        final StringBuilder tagsBuffer = new StringBuilder();
        for (final Tag tag : tags) {
            tagsBuffer.append(tag.getName()).append(JABREF_KEYWORD_SEPARATOR);
        }

        // remove last separator
        if (!tags.isEmpty()) {
            tagsBuffer.delete(tagsBuffer.lastIndexOf(JABREF_KEYWORD_SEPARATOR), tagsBuffer.length());
        }
        final String tagsBufferString = tagsBuffer.toString();
        if (present(tagsBufferString)) {
            entry.setField(StandardField.KEYWORDS, tagsBufferString);
        }
    }

    /**
     * Copies the month from Bibsonomy's month to JabRef's month field
     *
     * @param entry  the entry to copy to
     * @param bibtex the entry to copy from
     */
    public static void copyMonth(final BibEntry entry, final BibTex bibtex) {
        Objects.requireNonNull(entry);
        Objects.requireNonNull(bibtex);
        final String month = bibtex.getMonth();
        if (present(month)) {
            final Optional<Month> parsedMonth = Month.parse(month);
            if (parsedMonth.isPresent()) {
                entry.setMonth(parsedMonth.get());
            } else {
                // fallback if it could not be parsed: just write the plain value
                entry.setField(FieldFactory.parseField("month"), month);
            }
        }
    }

    public static void copyMiscProperties(final BibEntry entry, final BibTex bibtex) {
        if (present(bibtex.getMisc()) || present(bibtex.getMiscFields())) {
            bibtex.parseMiscField();

            // FIXME: if the misc field erroneously contains the intrahash, it is overwriting the correct one, which is set above!
            if (bibtex.getMiscFields() != null) {
                for (final String key : bibtex.getMiscFields().keySet()) {
                    if ("id".equals(key)) {
                        entry.setField(BibSonomyField.MISC_ID, bibtex.getMiscField(key));
                        continue;
                    }

                    if (key.startsWith("__")) {
                        continue;
                    }

                    entry.setField(FieldFactory.parseField(key), bibtex.getMiscField(key));
                }
            }
        }
    }

    protected static void copyStringProperties(BibEntry entry, BibTex bibtex) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final BeanInfo info = Introspector.getBeanInfo(bibtex.getClass());
        final PropertyDescriptor[] descriptors = info.getPropertyDescriptors();

        for (final PropertyDescriptor pd : descriptors) {
            final Method getter = pd.getReadMethod();
            final Object o = getter.invoke(bibtex, (Object[]) null);

            if (String.class.equals(pd.getPropertyType()) && (o != null) && !EXCLUDE_FIELDS.contains(pd.getName())) {
                final String value = ((String) o);
                if (present(value)) {
                    final Field field = FieldFactory.parseField(pd.getName().toLowerCase());
                    entry.setField(field, value);
                }
            }
        }
    }

    /**
     * Convert a JabRef BibEntry into a BibSonomy post
     */
    public static Post<BibTex> convertEntry(final BibEntry entry) {
        final Post<BibTex> post = new Post<>();
        final BibTex bibtex = new BibTex();
        post.setResource(bibtex);

        bibtex.setMisc("");

        final List<String> knownFields = copyStringPropertiesToBibsonomyModel(bibtex, entry);

        Optional<String> authorName = entry.getField(StandardField.AUTHOR);
        Optional<String> editorName = entry.getField(StandardField.EDITOR);

        try {
            if (authorName.isPresent()) {
                bibtex.setAuthor(PersonNameUtils.discoverPersonNames(authorName.get()));
            }

            if (editorName.isPresent()) {
                bibtex.setEditor(PersonNameUtils.discoverPersonNames(editorName.get()));
            }
        } catch (
                PersonListParserException e) {
            ExceptionUtils.logErrorAndThrowRuntimeException(LOGGER, e, "Could not convert person names");
        }

        knownFields.add("author");
        knownFields.add("editor");

        // add unknown Properties to misc
        entry.getFields().forEach(field -> {
            Optional<String> fieldName = entry.getField(field);
            if (!knownFields.contains(field.getName()) && !EXCLUDE_FIELDS.contains(field.getName()) && !field.getName().startsWith("__") && fieldName.isPresent()) {
                bibtex.addMiscField(field.getName(), fieldName.get());
            }
        });

        bibtex.serializeMiscFields();

        // set the key
        Optional<String> citeKeyOpt = entry.getCitationKey();
        citeKeyOpt.ifPresent(citeKey -> bibtex.setBibtexKey(StringUtil.toUTF8(citeKey)));

        Optional<String> typeOpt = entry.getField(StandardField.TYPE);
        typeOpt.ifPresent(type -> bibtex.setEntrytype(StringUtil.toUTF8(type.toLowerCase())));

        // set the date of the post
        final Optional<String> entryTimestampOpt = entry.getField(StandardField.TIMESTAMP);
        entryTimestampOpt.ifPresent(entryTimestamp -> {
            try {
                post.setDate(BIBSONOMY_DATE_FORMAT.parse(StringUtil.toUTF8(entryTimestamp)));
            } catch (
                    ParseException ex) {
                LOGGER.debug("Could not parse BibSonomy date format - trying JabrefDateFormat...");
            }
            try {
                post.setDate(JABREF_DATE_FORMAT.parse(StringUtil.toUTF8(entryTimestamp)));
            } catch (
                    ParseException ex) {
                LOGGER.debug("Could not parse Jabref date format - set date to NULL");
                post.setDate(null); // this is null anyway, but just to make it clear
            }
        });

        final Optional<String> entryAbstractOpt = entry.getField(StandardField.ABSTRACT);
        entryAbstractOpt.ifPresent(abstractOpt -> bibtex.setAbstract(StringUtil.toUTF8(abstractOpt)));

        final Optional<String> entryKeywordsOpt = entry.getField(StandardField.KEYWORDS);
        entryKeywordsOpt.ifPresent(entryKeywords -> {
            for (String keyword : entryKeywords.split(JABREF_KEYWORD_SEPARATOR)) {
                post.addTag(keyword);
            }
        });

        final Optional<String> entryUsernameOpt = entry.getField(BibSonomyField.USERNAME);
        entryUsernameOpt.ifPresent(entryUsername -> post.setUser(new User(StringUtil.toUTF8(entryUsername))));

        // Set the groups
        final Optional<String> entryGroupsOpt = entry.getField(StandardField.GROUPS);
        entryGroupsOpt.ifPresent(entryGroups -> {
            final String[] groupsArray = entryGroups.split(" ");
            final Set<Group> groups = new HashSet<>();

            for (final String group : groupsArray) {
                groups.add(new Group(StringUtil.toUTF8(group)));
            }

            post.setGroups(groups);
        });

        final Optional<String> entryDescriptionOpt = entry.getField(BibSonomyField.DESCRIPTION);
        entryDescriptionOpt.ifPresent(entryDescription -> post.setDescription(StringUtil.toUTF8(entryDescription)));

        final Optional<String> entryCommentOpt = entry.getField(StandardField.COMMENT);
        entryCommentOpt.ifPresent(entryComment -> post.setDescription(StringUtil.toUTF8(entryComment)));

        final Optional<String> entryMonthOpt = entry.getField(StandardField.MONTH);
        entryMonthOpt.ifPresent(entryMonth -> bibtex.setMonth(StringUtil.toUTF8(entryMonth)));

        return post;
    }

    /**
     * @param bibtex target object
     * @param entry  source object
     * @return list of all copied property names
     */
    public static List<String> copyStringPropertiesToBibsonomyModel(final BibTex bibtex, final BibEntry entry) {
        final List<String> knownFields = new ArrayList<>(50);

        final BeanInfo info;
        try {
            info = Introspector.getBeanInfo(bibtex.getClass());
        } catch (
                IntrospectionException e) {
            ExceptionUtils.logErrorAndThrowRuntimeException(LOGGER, e, "could not introspect");
            return knownFields;
        }
        final PropertyDescriptor[] descriptors = info.getPropertyDescriptors();

        // set all known properties of the BibTex
        for (PropertyDescriptor pd : descriptors) {
            if (!String.class.equals(pd.getPropertyType())) {
                continue;
            }
            Field bibtexField = FieldFactory.parseField(pd.getName().toLowerCase());
            if (present(entry.getField(bibtexField)) && !EXCLUDE_FIELDS.contains(pd.getName().toLowerCase())) {
                final Object value = entry.getField(bibtexField);
                try {
                    pd.getWriteMethod().invoke(bibtex, value);
                } catch (
                        Exception e) {
                    ExceptionUtils.logErrorAndThrowRuntimeException(LOGGER, e, "could not convert property " + pd.getName());
                    return knownFields;
                }
                knownFields.add(pd.getName());
            }
        }
        return knownFields;
    }

    /**
     * TODO: WTF?!
     *
     * @param bibtex
     * @return
     */
    private static Optional<BibEntryType> getEntryType(BibTex bibtex) {
        List<BibEntryType> entries = BibtexEntryTypeDefinitions.ALL.stream().filter(bibEntryType -> bibEntryType.getType().getName().equalsIgnoreCase(bibtex.getEntrytype())).toList();
        return entries.isEmpty() ? Optional.empty() : entries.stream().findFirst();
    }
}

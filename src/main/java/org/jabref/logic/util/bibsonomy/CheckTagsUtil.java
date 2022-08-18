package org.jabref.logic.util.bibsonomy;

import java.util.List;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;

import org.apache.commons.lang3.NotImplementedException;

import static org.bibsonomy.util.ValidationUtils.present;

/**
 * {@link CheckTagsUtil} check a list of posts, if a posts has no tags assigned
 *  @author Waldemar Biller <biller@cs.uni-kassel.de>
 */
@SuppressWarnings("checkstyle:MissingDeprecated")
public class CheckTagsUtil {

	private static final String DEFAULT_TAG = "jabref:noKeywordAssigned";
	private static final int MAX_NUM_MISSING_TAGS = 10;
	private static final String TITLE_FIELD = "title";

	private List<BibEntry> entries;

	public CheckTagsUtil(List<BibEntry> entries) {
		this.entries = entries;
	}

	/**
	 * loop through list of posts and check if tags are present for each
	 */
	public int hasAPostNoTagsAssigned() {
        throw new NotImplementedException();
        /*
        // ToDo: Do not call JOptionPane from here
        int numPostsMissingTags = 0;
        String postsMissingTags = "";
        for (BibEntry entry : entries) {
            if (!present(entry.getField(StandardField.KEYWORDS))) {
                // if the user has chosen to not to be warned when exporting entries without keywords, we add the default tag silently.
                if (PluginProperties.ignoreNoTagsAssigned()) {
                    assignDefaultTag(entry);
                } else {
                    numPostsMissingTags++;
                    postsMissingTags += entry.getField(TITLE_FIELD) + "\n";
                }
            }
        }

        // if posts without tags are present, ask the user what to do
        // (can happen only when user setting "ignoreNoTagsAssigned" is FALSE, see comment above)
        if (numPostsMissingTags > 0 && jabRefFrame != null) {
            String message;
            if (numPostsMissingTags <= MAX_NUM_MISSING_TAGS) {
                message = "The following selected entries have no keywords assigned: \n\n"
                        + postsMissingTags
                        + "\nDo you want to continue exporting them? If you choose yes, "
                        + "then the keyword '"
                        + DEFAULT_TAG
                        + "' will be added as default tag.";
            } else {
                message = "More than "
                        + MAX_NUM_MISSING_TAGS
                        + " selected entries have no keywords assigned; "
                        + "Do you want to continue exporting them? If you choose yes, "
                        + "then the keyword '" + DEFAULT_TAG
                        + "' will be added as default tag.";
            }
            return JOptionPane.showConfirmDialog(jabRefFrame, message, "Post is missing tags", JOptionPane.YES_NO_OPTION);
        }

        // all posts have assigned tags -> return OK
        return JOptionPane.DEFAULT_OPTION;
        */
	}

	/**
	 * add default tag to each post which has no tags assigned.
	 */
	public void assignDefaultTag() {
		for (BibEntry entry : entries) {
			if (!present(entry.getField(StandardField.KEYWORDS))) {
                entry.setField(StandardField.KEYWORDS, DEFAULT_TAG);
            }
		}
	}

	public static void assignDefaultTag(BibEntry entry) {
		entry.setField(StandardField.KEYWORDS, DEFAULT_TAG);
	}
}

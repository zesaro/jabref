package org.jabref.logic.util.bibsonomy;

import java.util.Comparator;

import org.bibsonomy.common.enums.GroupingEntity;
import org.bibsonomy.model.Tag;

public class TagComparator implements Comparator<Tag> {

	private static final int LESS_THAN = -100;
    private static final int EQUAL = 0;
    private static final int GREATER_THAN = 100;
	private GroupingEntity grouping;

	public TagComparator(GroupingEntity grouping) {
		this.grouping = grouping;
	}

	public int compare(Tag currentTag, Tag nextTag) {
        if (grouping == GroupingEntity.USER) {
            if (currentTag.getUsercount() < nextTag.getUsercount()) {
                return GREATER_THAN;
            } else if (currentTag.getUsercount() > nextTag.getUsercount()) {
                return LESS_THAN;
            } else {
                return EQUAL;
            }
        }
        if (currentTag.getGlobalcount() < nextTag.getGlobalcount()) {
            return GREATER_THAN;
        } else if (currentTag.getGlobalcount() > nextTag.getGlobalcount()) {
            return LESS_THAN;
        } else {
            return EQUAL;
        }
    }
}

package org.jabref.gui.util.comparator;

import java.util.Optional;

import org.jabref.gui.specialfields.SpecialFieldValueViewModel;
import org.jabref.model.entry.field.SpecialFieldValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialFieldComparatorTest {

    private SpecialFieldComparator comparator;
    private final SpecialFieldValue value1 = SpecialFieldValue.PRIORITY_HIGH;
    private final SpecialFieldValue value2 = SpecialFieldValue.PRIORITY_LOW;
    private final SpecialFieldValue value3 = SpecialFieldValue.READ;
    private final Optional<SpecialFieldValueViewModel> prio1 = Optional.of(new SpecialFieldValueViewModel(value1));
    private final Optional<SpecialFieldValueViewModel> prio3 = Optional.of(new SpecialFieldValueViewModel(value2));
    private final Optional<SpecialFieldValueViewModel> read = Optional.of(new SpecialFieldValueViewModel(value3));

    @BeforeEach
    void setUp() {
        comparator = new SpecialFieldComparator();
    }

    @Test
    void compareHigherPriorityFirst() {
        assertEquals(-2, comparator.compare(prio1, prio3));
    }

    @Test
    void compareLowerPriorityFirst() {
        assertEquals(2, comparator.compare(prio3, prio1));
    }

    @Test
    void compareSamePriority() {
        assertEquals(0, comparator.compare(prio1, prio1));
    }

    @Test
    void compareUnrelatedFields() {
        assertEquals(-11, comparator.compare(prio1, read));
    }

    @Test
    void compareTwoEmptyInputs() {
        assertEquals(0, comparator.compare(Optional.empty(), Optional.empty()));
    }

    @Test
    void compareTwoInputsWithFirstEmpty() {
        assertEquals(1, comparator.compare(Optional.empty(), prio1));
    }

    @Test
    void compareTwoInputsWithSecondEmpty() {
        assertEquals(-1, comparator.compare(prio1, Optional.empty()));
    }
}

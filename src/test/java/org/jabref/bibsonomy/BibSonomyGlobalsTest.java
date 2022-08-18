package org.jabref.bibsonomy;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BibSonomyGlobalsTest {

    @Test
    public void TestBibSonomyName() {
        assertEquals("BibSonomy", BibSonomyGlobals.BIBSONOMY_NAME);
    }
}

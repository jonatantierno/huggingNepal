package com.katmandu.katmandu;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonatan on 6/05/15.
 */
public class TownSummaryTest {
    @Test
    public void shouldTurnTownToString(){
        TownSummary summaryUnderTest = TownSummary.buildUpToDate("Town Name", TownStatus.BLACK, 12345);
        String serialized = summaryUnderTest.toString();
        TownSummary deserialized = TownSummary.deserialize(serialized);

        assertEquals(deserialized,summaryUnderTest);
    }
    @Test
    public void shouldTurnDraftToString(){
        TownSummary summaryUnderTest = TownSummary.buildDraft("Town Name", TownStatus.BLACK, "12345");
        String serialized = summaryUnderTest.toString();
        TownSummary deserialized = TownSummary.deserialize(serialized);

        assertEquals(deserialized,summaryUnderTest);
    }
}
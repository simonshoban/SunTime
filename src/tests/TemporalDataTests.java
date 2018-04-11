package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import data.TemporalData;
import exceptions.InvalidTimeZoneException;

/**
 * Tests the TemporalData class.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class TemporalDataTests {
    /**
     * Throws InvalidTimeZoneException when given a time zone code that doesn't use GMT or UTF format.
     */
    @Test
    void throwsInvalidTimeZoneExceptionWhenGivenNonGMTorUTFTimeZoneCode() {
        assertThrows(InvalidTimeZoneException.class, () -> TemporalData.formatTimeZoneCode("abcd"));
    }
    
    /**
     * Time zone string should be converted into GMT(Sign)HH:MM format.
     * 
     * @throws InvalidTimeZoneException when given a string that doesn't use GMT or UTF
     */
    @Test
    void pacificStandardTimeStringReturnsCorrectGMTTimeZoneCode() throws InvalidTimeZoneException {
        assertTrue(TemporalData.formatTimeZoneCode(" UTC/GMT -8 hours").toString().equals("GMT-08:00"));
    }
}

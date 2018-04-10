package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import data.TemporalData;
import exceptions.InvalidTimeZoneException;

public class TemporalDataTests {
    @Test
    void throwsInvalidTimeZoneExceptionWhenGivenNonGMTorUTFTimeZoneCode() {
        assertThrows(InvalidTimeZoneException.class, () -> TemporalData.formatTimeZoneCode("abcd"));
    }
    
    @Test
    void pacificStandardTimeStringReturnsCorrectGMTTimeZoneCode() throws InvalidTimeZoneException {
        assertTrue(TemporalData.formatTimeZoneCode(" UTC/GMT -8 hours").toString().equals("GMT-08:00"));
    }
}

package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.InvalidTimeZoneException;
import toolkit.Toolkit;

/**
 * Tests the Toolkit.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
class ToolkitTests {

    @Test
    void firstLetterUppercaseAndEverythingElseLowercase() {
        assertTrue(Toolkit.capitalize("AbC").equals("Abc"));
    }
    
    @Test
    void timeZoneFormattingShouldThrowExceptionWhenGivenStringWithoutGMT() {
        assertThrows(InvalidTimeZoneException.class, () -> Toolkit.formatTimeZoneCode("ajsdhfakjsf"));
    }
    
    @Test
    void pacificStandardTimeStringReturnsCorrectGMTTimeZoneCode() throws InvalidTimeZoneException {
        assertTrue(Toolkit.formatTimeZoneCode(" UTC/GMT -8 hours").getDisplayName().equals("GMT-08:00"));
    }

}

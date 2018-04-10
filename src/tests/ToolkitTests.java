package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
        assertTrue(Toolkit.capitalize("aBc").equals("Abc"));
    }

    @Test
    void stringIsInsertedIntoOtherString() {
        assertEquals(Toolkit.insertIntoStringAt("hello", "abcd", 2), "heabcdllo");
    }
}

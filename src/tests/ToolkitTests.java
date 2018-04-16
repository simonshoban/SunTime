package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.ExceptionFactory;
import exceptions.NotYetImplementedException;
import toolkit.Toolkit;

/**
 * Tests the Toolkit.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
class ToolkitTests {
    /**
     * The first letter of a given string should be upper case while the rest of it is lower case.
     */
    @Test
    void firstLetterUppercaseAndEverythingElseLowercase() {
        assertTrue(Toolkit.capitalize("aBc").equals("Abc"));
    }

    /**
     * A string should be inserted into another string at the given index.
     */
    @Test
    void stringIsInsertedIntoOtherString() {
        assertEquals(Toolkit.insertIntoStringAt("hello", "abcd", 2), "heabcdllo");
    }
    
    /**
     * The thread version of the setTimeout() method should execute the Runnable after the given delay.
     * @throws NotYetImplementedException 
     */
    @Test
    void testSetTimeoutThreadVersion() throws NotYetImplementedException {
        ExceptionFactory.throwNotYetImplementedException();
    }

    /**
     * The TimerTask version of the setTimeout() method should execute the TimerTask after the given delay.
     * @throws NotYetImplementedException 
     */
    @Test
    void testSetTimeoutTimerTaskVersion() throws NotYetImplementedException {
        ExceptionFactory.throwNotYetImplementedException();
    }
}

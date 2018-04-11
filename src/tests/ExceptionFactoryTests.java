package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import data.WebAddress;
import exceptions.ExceptionFactory;
import exceptions.InvalidURLException;
import exceptions.SaintPetersburgException;
import toolkit.Toolkit;

/**
 * Tests the ExceptionFactory.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class ExceptionFactoryTests {
    /**
     * SaintPetersburgException should be thrown when given a WebAddress pointing to Saint Petersburg, Russia.
     */
    @Test
    void throwsSaintPetersburgExceptionWhenGivenWebAddressPointingToSaintPetersburg() {
        WebAddress webAddress = new WebAddress(Toolkit.DOMAIN, "Saint Petersburg", "Russia");
        String dummyExtension = "dummy/extension";
        assertThrows(SaintPetersburgException.class, 
                () -> ExceptionFactory.throwInvalidURLException(webAddress, dummyExtension));
    }
    
    /**
     * Regular InvalidURLException should be thrown under other conditions.
     */
    @Test
    void regularInvalidURLExceptionIsThrownOtherwise() {
        WebAddress webAddress = new WebAddress(Toolkit.DOMAIN, "Vancouver", "Canada");
        String dummyExtension = "dummy/extension";
        try {
            ExceptionFactory.throwInvalidURLException(webAddress, dummyExtension);
        } catch (InvalidURLException i) {
            assertFalse(i instanceof SaintPetersburgException);
        }
    }
}

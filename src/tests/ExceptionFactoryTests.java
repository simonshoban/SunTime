package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.WebAddress;
import exceptions.ExceptionFactory;
import exceptions.InvalidURLException;
import exceptions.SaintPetersburgException;
import toolkit.Toolkit;

public class ExceptionFactoryTests {
    
    @Test
    void throwsSaintPetersburgExceptionWhenGivenWebAddressPointingToSaintPetersburg() {
        WebAddress webAddress = new WebAddress(Toolkit.DOMAIN, "Saint Petersburg", "Russia");
        String dummyExtension = "dummy/extension";
        assertThrows(SaintPetersburgException.class, 
                () -> ExceptionFactory.throwInvalidURLException(webAddress, dummyExtension));
    }
    
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

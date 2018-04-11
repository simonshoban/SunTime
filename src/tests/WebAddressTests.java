package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import data.WebAddress;
import toolkit.Toolkit;

/**
 * Tests the WebAddress class.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WebAddressTests {
    private WebAddress saintJohns = new WebAddress(Toolkit.DOMAIN, "sT. joHNs", "CAnaDa");
    private WebAddress saintPetersburg = new WebAddress(Toolkit.DOMAIN, "saINt PEterSBurG", "ruSSia");
    
    /**
     * City should be lower case and whitespace should be replaced by hyphens.
     */
    @Test
    void cityIsProperlyFormattedForTimeAndDateDotCom() {
        assertTrue(saintJohns.getCity().equals("st-johns"));
    }
    
    /**
     * Country should be lower case and whitespace should be replaced by hyphens.
     */
    @Test
    void countryIsProperlyFormattedForTimeAndDateDotCom() {
        assertTrue(saintJohns.getCountry().equals("canada"));
    }
    
    /**
     * Capitalized version of the city should be capitalized and keep original formatting.
     */
    @Test
    void cityIsProperlyCapitalizedAndRadixPointIsMaintained() {
        assertTrue(saintJohns.getCapitalizedCity().equals("St. Johns"));
    }
    
    /**
     * WebAddress should be able to tell if it is pointing to Saint Petersburg, Russia.
     */
    @Test
    void detectsSaintPetersburgRussia() {
        assertTrue(saintPetersburg.isSaintPetersburg());
    }
}

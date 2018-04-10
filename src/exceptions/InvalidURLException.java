package exceptions;

import data.WebAddress;

/**
 * An exception that is thrown when the program receives an 
 * Invalid URL to scrape information from.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InvalidURLException extends SunTimeException {
    protected WebAddress webAddress;
    protected String extension;
    
    /**
     * Constructs an InvalidURLException object.
     * 
     * @param webAddress - The invalid WebAddress object
     * @param extension - The URL extension of the invalid address
     */
    protected InvalidURLException(WebAddress webAddress, String extension) {
        this.webAddress = webAddress;
        this.extension = extension;
    }
    
    /**
     * Prints error message.
     */
    public void printErrorMessage() {
        error("Invalid URL: " + webAddress.getDomain() + extension + "/n");
        error("Domain: " + webAddress.getDomain());
        error("Country: " + webAddress.getCountry());
        error("City: " + webAddress.getCity());
        error("Extension: " + extension + "\n");
    }
}

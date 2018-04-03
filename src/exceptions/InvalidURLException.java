package exceptions;

import data.WebAddress;

/**
 * An exception that is thrown when the program receives an 
 * Invalid URL to scrape information from.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class InvalidURLException extends Exception {
    private WebAddress webAddress;
    private String extension;
    
    /**
     * Constructs an InvalidURLException object.
     * 
     * @param webAddress - The invalid WebAddress object
     * @param extension - The URL extension of the invalid address
     */
    public InvalidURLException(WebAddress webAddress, String extension) {
        this.webAddress = webAddress;
        this.extension = extension;
    }
    
    /**
     * Prints error message.
     */
    public void printErrorMessage() {
        errPrint("Invalid URL: " + webAddress.getDomain() + extension + "/n");
        errPrint("Domain: " + webAddress.getDomain());
        errPrint("Country: " + webAddress.getCountry());
        errPrint("City: " + webAddress.getCity());
        errPrint("Extension: " + extension + "\n");
    }
    
    /**
     * Helper function that prints messages to stderr.
     * 
     * @param message - The message to print
     */
    private void errPrint(String message) {
        System.err.println(message);
    }
}

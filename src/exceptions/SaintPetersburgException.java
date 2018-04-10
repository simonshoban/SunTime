package exceptions;

import data.WebAddress;

/**
 * An exception that is thrown when the user requests data for Saint Petersburg, Russia.
 * Due to a typo on timeanddate.com, there is no data for "Saint Petersburg", only "Saint Peterburg"
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SaintPetersburgException extends InvalidURLException {
    /**
     * Constructs a SaintPetersburgException.
     * 
     * @param webAddress - The invalid WebAddress object
     * @param extension  - The URL extension of the invalid address
     */
    protected SaintPetersburgException(WebAddress webAddress, String extension) {
        super(webAddress, extension);
    }

    /**
     * Prints error message.
     */
    @Override
    public void printErrorMessage() {
        error("SaintPetersburgException!");
        error("Saint Petersburg is misspelled as Saint Peterburg on timeanddate.com");
        error("Please try again with: Saint Peterburg");
    }
}

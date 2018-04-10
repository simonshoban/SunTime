package exceptions;

/**
 * An exception that is thrown when something receives an invalid time zone.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InvalidTimeZoneException extends SunTimeException {
    private String invalidTimeZone;
    
    /**
     * Constructs an InvalidTimeZoneException.
     * 
     * @param invalidTimeZone - the invalid time zone that caused the error
     */
    protected InvalidTimeZoneException(String invalidTimeZone) {
        this.invalidTimeZone = invalidTimeZone;
    }

    @Override
    public void printErrorMessage() {
        error("InvalidTimeZoneException!");
        error("Invalid time zone code: " + invalidTimeZone);
    }
}

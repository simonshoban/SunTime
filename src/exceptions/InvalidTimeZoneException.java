package exceptions;

/**
 * An exception that is thrown when something receives an invalid time zone.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class InvalidTimeZoneException extends SunTimeException {
    private String invalidTimeZone;
    
    /**
     * Constructs an InvalidTimeZoneException.
     * 
     * @param invalidTimeZone - the invalid time zone that caused the error
     */
    public InvalidTimeZoneException(String invalidTimeZone) {
        this.invalidTimeZone = invalidTimeZone;
    }

    @Override
    public void printErrorMessage() {
        error(invalidTimeZone);
    }
}

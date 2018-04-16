package exceptions;

import data.WebAddress;

/**
 * Creates and throws exceptions.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class ExceptionFactory {
    /**
     * Throws an InvalidURLException, may throw a SaintPetersburgException which is also an InvalidURLException.
     * 
     * @param webAddress - The WebAddress containing the invalid URL
     * @param extension - The extension that was part of the invalid URL
     * @throws InvalidURLException always
     */
    public static void throwInvalidURLException(WebAddress webAddress, String extension) throws InvalidURLException {
        if (webAddress.isSaintPetersburg()) {
            throw new SaintPetersburgException(webAddress, extension);
        } else {
            throw new InvalidURLException(webAddress, extension);
        }
    }
    
    /**
     * Throws an InvalidTimeZoneException.
     * 
     * @param invalidTimeZone - The invalid time zone String
     * @throws InvalidTimeZoneException always
     */
    public static void throwInvalidTimeZoneException(String invalidTimeZone) throws InvalidTimeZoneException {
        throw new InvalidTimeZoneException(invalidTimeZone);
    }
    
    /**
     * Throws a NotYetImplementedException.
     * 
     * @throws NotYetImplementedException always
     */
    public static void throwNotYetImplementedException() throws NotYetImplementedException {
        throw new NotYetImplementedException();
    }
}

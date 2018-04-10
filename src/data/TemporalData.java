package data;

import java.time.ZoneId;
import java.time.ZoneOffset;

import exceptions.ExceptionFactory;
import exceptions.InvalidTimeZoneException;

/**
 * Holds time-related data.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class TemporalData extends SunTimeData {
    private static final int TIME_ZONE_FORMAT_OFFSET = 3;
    private static final int LENGTH_OF_OFFSET_WITHOUT_LEADING_ZERO = 5;
    private ZoneId timeZone;
    
    /**
     * Constructs a TemporalData.
     */
    public TemporalData() {
        
    }
    
    /**
     * Inserts time zone data into the TemporalData.
     * 
     * @param timeZoneString - A string representing the time zone
     */
    public void insertTimeZone(String timeZoneString) {
        try {
            timeZone = formatTimeZoneCode(timeZoneString);
        } catch (InvalidTimeZoneException e) {
            e.printErrorMessage();
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the time zone.
     * 
     * @return timeZone as a TimeZone
     */
    public ZoneId getTimeZone() {
        return timeZone;
    }
    
    /**
     * Formats a given string into a ZoneId.
     * 
     * @param unformattedData - The unformatted time zone code
     * @return a ZoneId
     * @throws InvalidTimeZoneException when given a time zone code that doesn't use GMT or UTF format
     */
    public static ZoneId formatTimeZoneCode(String unformattedData) throws InvalidTimeZoneException {
        String formattedData = unformattedData.replaceAll("\\s+", "");        
        
        if (isGMT(formattedData)) {
            formattedData = "Z";
        } else {
            formattedData = removeGMTandUTF(formattedData);
            formattedData = formattedData.replaceAll("hours?", "");
            formattedData = insertLeadingZero(formattedData);
        }
        
        System.out.println("Offset: " + formattedData);
        
        return ZoneId.ofOffset("GMT", ZoneOffset.of(formattedData));
    }
    
    /**
     * Checks if the given time zone is GMT (no offset).
     * 
     * @param timeZone - The string containing the time zone to check
     * @return true if there is no offset, false otherwise
     */
    private static boolean isGMT(String timeZone) {
        return timeZone.equals("NoUTC/GMToffset");
    }
    
    /**
     * Removes "GMT" and "UTF" from a time zone string.
     * 
     * @param timeZoneString - A string containing a time zone
     * @return The timeZoneString without "GMT" or "UTF"
     * @throws InvalidTimeZoneException when given a time zone string that doesn't use GMT or UTF format
     */
    private static String removeGMTandUTF(String timeZoneString) throws InvalidTimeZoneException {
        if (timeZoneString.contains("GMT")) {
            timeZoneString = timeZoneString.substring(timeZoneString.indexOf("GMT") + TIME_ZONE_FORMAT_OFFSET);
        } else if (timeZoneString.contains("UTF")) {
            timeZoneString = timeZoneString.substring(timeZoneString.indexOf("UTF") + TIME_ZONE_FORMAT_OFFSET);    
        } else {
            ExceptionFactory.throwInvalidTimeZoneException("Not using UTF or GMT");
        }
        
        return timeZoneString;
    }
    
    /**
     * Inserts a leading zero into the time zone offset if it isn't already there.
     * 
     * @param offset - The string containing the time zone offset with or without a leading zero
     * @return - A time zone offset with a leading zero
     */
    private static String insertLeadingZero(String offset) {
        if (offset.contains(":30") && offset.length() == LENGTH_OF_OFFSET_WITHOUT_LEADING_ZERO) {
            String sign = offset.substring(0, 1);
            offset = offset.substring(1);
            offset = sign + "0" + offset;
        }
        
        return offset;
    }
}

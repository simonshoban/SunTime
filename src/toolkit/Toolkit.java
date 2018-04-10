package toolkit;

import java.time.ZoneId;
import java.time.ZoneOffset;

import exceptions.ExceptionFactory;
import exceptions.InvalidTimeZoneException;

/**
 * A toolkit of useful stuff.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Toolkit {
    /**
     * The web domain that this app is built to scrape.
     */
    public static final String DOMAIN = "https://www.timeanddate.com/";
    private static final int MILLISECONDS_IN_SECONDS = 1000;
    private static final int TIME_ZONE_FORMAT_OFFSET = 3;
    
    /**
     * Capitalizes the string.
     * 
     * @param string - The string to be capitalized
     * @return a capitalized version of the given string
     */
    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() 
                + string.substring(1).toLowerCase();
    }
    
    /**
     * Creates a new thread to execute a runnable after a delay.
     * 
     * @param runnable - The thing to be executed
     * @param delay - The delay in milliseconds
     */
    public static void setTimeout(Runnable runnable, long delay) {
        System.out.println("difference: " + delay + "\n");
        
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }
    
    /**
     * Converts seconds to milliseconds.
     * 
     * @param seconds - The seconds to convert
     * @return seconds as milliseconds
     */
    public static long convertSecondsToMilliseconds(long seconds) {
        return seconds * MILLISECONDS_IN_SECONDS;
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
        
        System.out.println("fd: " + formattedData);
        
        return ZoneId.ofOffset("GMT", ZoneOffset.of(formattedData));
    }
    
    /**
     * Checks if the given time zone is GMT (no offset).
     * 
     * @param timeZone
     * @return
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
            System.out.println("GMT: " + timeZoneString);
            timeZoneString = timeZoneString.substring(timeZoneString.indexOf("GMT") + TIME_ZONE_FORMAT_OFFSET);
        } else if (timeZoneString.contains("UTF")) {
            System.out.println("UTF: " + timeZoneString);
            timeZoneString = timeZoneString.substring(timeZoneString.indexOf("UTF") + TIME_ZONE_FORMAT_OFFSET);    
        } else {
            ExceptionFactory.throwInvalidTimeZoneException("Not using UTF or GMT");
        }
        
        return timeZoneString;
    }
    
    /**
     * Inserts a leading zero into the time zone offset if it isn't already there.
     * 
     * @param string
     * @return
     */
    private static String insertLeadingZero(String string) {
        if (string.contains(":30") && string.length() == 5) {
            String sign = string.substring(0, 1);
            string = string.substring(1);
            string = sign + "0" + string;
        }
        
        return string;
    }
}

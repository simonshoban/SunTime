package data;

import java.time.ZoneId;

import exceptions.InvalidTimeZoneException;
import toolkit.Toolkit;

/**
 * Holds time-related data.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class TemporalData {
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
            timeZone = Toolkit.formatTimeZoneCode(timeZoneString);
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
}

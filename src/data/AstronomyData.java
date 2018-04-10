package data;

import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.List;

/**
 * Holds astronomy data from timeandate.com.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class AstronomyData extends SunTimeData {
    private ArrayList<String> sunriseTimes;
    private ArrayList<String> sunsetTimes;
    
    /**
     * Constructs an AstronomyData object.
     */
    public AstronomyData() {
        sunriseTimes = new ArrayList<String>();
        sunsetTimes = new ArrayList<String>();
    }
    
    /**
     * Inserts data into the arrays.
     * 
     * @param tableRows - an Elements object containing the HTML table rows 
     * with astronomy information
     */
    public void insertData(Elements tableRows) {
        for (Element row : tableRows) {
            try {
                sunriseTimes.add(row.child(1).text());
                sunsetTimes.add(row.child(2).text());
            } catch (IndexOutOfBoundsException e) {
                // Useless table row is discarded
            }
        }       
    }
    
    /**
     * Gets a list of sunrise times.
     * 
     * @return - sunriseTimes as an unmodifiable list
     */
    public List<String> getSunriseTimes() {
        return Collections.unmodifiableList(sunriseTimes);
    }
    
    /**
     * Gets a list of sunset times.
     * 
     * @return - sunsetTimes as an unmodifiable list
     */
    public List<String> getSunsetTimes() {
        return Collections.unmodifiableList(sunsetTimes);
    }
}

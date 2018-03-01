package data;

/**
 * Holds weather data from timeanddate.com.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WeatherArrays {
    private String[] nextFiveHours;
    
    /**
     * Constructs a WeatherArrays object.
     * 
     * @param temperatures
     */
    public WeatherArrays(String[] temperatures) {
        nextFiveHours = temperatures;
    }
    
    /**
     * Insert temperature data for the next five hours into the weather array.
     * 
     * @param temperatures - An array of strings containing the next five
     * hours of temperature data
     */
    public void insertNextFiveHours(String[] temperatures) {
        nextFiveHours = temperatures;
    }
    
    /**
     * Gets the next five hours of temperature data from the weather array.
     * 
     * @return nextFiveHours as a String[5]
     */
    public String[] getNextFiveHours() {
        return nextFiveHours;
    }
}

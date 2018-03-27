package data;

import gui.ImagePanel;

/**
 * Holds weather data from timeanddate.com.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WeatherData {
    private String[] nextFiveHours;
    private String[] fiveHourTimes;
    private ImagePanel[] weatherImages;
    
    /**
     * Constructs a WeatherData object.
     * 
     * @param temperatures - An array containing the next five hours of 
     * temperatures
     */
    public WeatherData(String[] temperatures) {
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
     * Inserts the times for the next five hours.
     * 
     * @param times - An array of strings containing the next five hours
     */
    public void insertFiveHourTimes(String[] times) {
        fiveHourTimes = times;
    }
    
    /**
     * Inserts an array of ImagePanels into the weather data.
     * 
     * @param images - An ImagePanel[] with weather images
     */
    public void insertWeatherImages(ImagePanel[] images) {
        weatherImages = images;
    }
    
    /**
     * Gets the next five hours of temperature data from the weather array.
     * 
     * @return nextFiveHours as a String[5]
     */
    public String[] getNextFiveHours() {
        return nextFiveHours;
    }
    
    public String[] getFiveHourTimes() {
        return fiveHourTimes;
    }
    
    /**
     * Gets the next five hours of weather images.
     * 
     * @return weatherImages as an ImagePanel[]
     */
    public ImagePanel[] getWeatherImages() {
        return weatherImages;
    }
}

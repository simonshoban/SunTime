package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

/**
 * Holds weather data from timeanddate.com.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WeatherData {
    private String[] nextFiveHours;
    private String[] fiveHourTimes;
    private BufferedImage[] weatherImages;
    
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
     * @param fileLocations - An String[] containing all the file locations of the weather images 
     */
    public void insertWeatherImages(String[] fileLocations) {
        
        for (int index = 0; index < fileLocations.length; index++) {
            try {
                URL url = new URL(fileLocations[index]);
                File file = new File("not_null");
                
                FileUtils.copyURLToFile(url, file);
                weatherImages[index] = ImageIO.read(file);
            } catch (IOException i) {
                i.printStackTrace();
            }           
        }
    }
    
    /**
     * Gets the next five hours of temperature data from the weather array.
     * 
     * @return nextFiveHours as a String[5]
     */
    public String[] getNextFiveHours() {
        return nextFiveHours;
    }
    
    /**
     * Gets the fiveHourTimes.
     * 
     * @return fiveHourTimes as a String[]
     */
    public String[] getFiveHourTimes() {
        return fiveHourTimes;
    }
    
    /**
     * Gets the next five hours of weather images.
     * 
     * @return weatherImages as a BufferedImage[]
     */
    public BufferedImage[] getWeatherImages() {
        return weatherImages;
    }
}

package main;

import gui.WindowFrame;
import gui.MainContainer;
import data.AstronomyData;
import data.Parser;
import data.WeatherData;
import data.WebAddress;

/**
 * Drives the program.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public final class Main {
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;
    private static String domain = "https://www.timeanddate.com/";
    private static String city = "vancouver";
    private static String country = "canada";
    
    /**
     * Prevents the creation of Main objects.
     */
    private Main() {
    }

    /**
     * Point of entry.
     * 
     * @param args - unused
     */
    public static void main(String[] args) {
        WebAddress webAddress = new WebAddress(domain, country, city);
        Parser webParser = new Parser(webAddress);
        
        AstronomyData astronomyData = webParser.getAstronomyArrays();
        WeatherData weatherData = webParser.getWeatherArrays();
        
        MainContainer container = new MainContainer(astronomyData, weatherData);
        WindowFrame windowFrame = new WindowFrame(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        container.init();
        windowFrame.add(container);
        windowFrame.init();
    }
}

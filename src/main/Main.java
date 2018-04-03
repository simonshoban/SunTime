package main;

import gui.WindowFrame;
import toolkit.Toolkit;
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
        WebAddress webAddress = new WebAddress(Toolkit.DOMAIN, city, country);
        Parser webParser = new Parser(webAddress);
        
        AstronomyData astronomyData = webParser.getAstronomyArrays();
        WeatherData weatherData = webParser.getWeatherArrays();
        
        WindowFrame windowFrame = new WindowFrame(
                WINDOW_WIDTH, 
                WINDOW_HEIGHT, 
                webAddress
                );
        
        MainContainer container = new MainContainer(
                astronomyData, 
                weatherData, 
                windowFrame,
                webParser
                );
        
        container.init();
        windowFrame.add(container);
        windowFrame.init();
        
        System.out.println();
    }
}

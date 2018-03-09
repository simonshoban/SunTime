package main;

import gui.WindowFrame;
import gui.MainContainer;
import data.AstronomyArrays;
import data.Parser;
import data.Scraper;
import data.WeatherArrays;

/**
 * Drives the program.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public final class Main {
    private static String webAddress = "https://www.timeanddate.com/";
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;
    
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
        Parser webParser = new Parser();
        Scraper webScraper = new Scraper(webAddress);
        
        webScraper.scrapeAstronomyAndWeather(webParser);
        
        AstronomyArrays astronomyData = webParser.getAstronomyArrays();
        WeatherArrays weatherData = webParser.getWeatherArrays();
        
        MainContainer container = new MainContainer(astronomyData, weatherData);
        WindowFrame windowFrame = new WindowFrame(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        container.init();
        windowFrame.add(container);
    }
}

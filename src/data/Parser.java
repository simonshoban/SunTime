package data;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import gui.ImagePanel;

/**
 * Parses HTML data into something more useful.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Parser {
    private static final int SIZE_OF_TEMPERATURES = 6;
    private AstronomyData astronomyData;
    private WeatherData weatherData;
    
    /**
     * Constructs a Parser object.
     * 
     * @param webAddress - The web address to parse scraped information from
     */
    public Parser(WebAddress webAddress) {
        astronomyData = new AstronomyData();
        Scraper webScraper = new Scraper(webAddress);
        webScraper.scrapeAstronomyAndWeather(this);
    }
    
    /**
     * Parses astronomy data and inserts it into the AstrnomyArrays.
     * 
     * @param document - the HTML document to parse from
     */
    public void parseAstronomyData(Document document) {
        Element table = document.getElementById("as-monthsun").child(1);
        Elements tableRows = table.getElementsByTag("tr");

        astronomyData.insertData(tableRows);
    }
    
    /**
     * Parses weather data and inserts it into the WeatherData.
     * 
     * @param document - the HTML document to parse from
     */
    public void parseWeatherData(Document document) {
        Element table = document.getElementById("wt-5hr").child(0);
        Element temperatureRow = table.getElementsByClass("soft").first();
        Elements images = table.getElementsByTag("img");
        Elements temperature = temperatureRow.getElementsByTag("td");
        String[] temperatures = new String[SIZE_OF_TEMPERATURES];
        ImagePanel[] imagePanels = new ImagePanel[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            temperatures[index] = temperature.get(index).text();
            String imageLocation = images.get(index).absUrl("src");
            System.out.println(images.get(index).absUrl("src"));
            imagePanels[index] = new ImagePanel(imageLocation);
        }
        
        weatherData = new WeatherData(temperatures);
        weatherData.insertWeatherImages(imagePanels);
    }
    
    /**
     * Gets the astronomy arrays.
     * 
     * @return - astronomyData as an AstronomyData
     */
    public AstronomyData getAstronomyArrays() {
        return astronomyData;
    }
    
    /**
     * Gets the weather arrays.
     * 
     * @return - weatherData as a WeatherData
     */
    public WeatherData getWeatherArrays() {
        return weatherData;
    }
}

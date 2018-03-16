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
    private AstronomyArrays astronomyArrays;
    private WeatherArrays weatherArrays;
    
    /**
     * Constructs a Parser object.
     * 
     * @param webAddress - The web address to parse scraped information from
     */
    public Parser(WebAddress webAddress) {
        astronomyArrays = new AstronomyArrays();
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

        astronomyArrays.insertData(tableRows);
    }
    
    /**
     * Parses weather data and inserts it into the WeatherArrays.
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
        
        weatherArrays = new WeatherArrays(temperatures);
        weatherArrays.insertWeatherImages(imagePanels);
    }
    
    /**
     * Gets the astronomy arrays.
     * 
     * @return - astronomyArrays as an AstronomyArrays
     */
    public AstronomyArrays getAstronomyArrays() {
        return astronomyArrays;
    }
    
    /**
     * Gets the weather arrays.
     * 
     * @return - weatherArrays as a WeatherArrays
     */
    public WeatherArrays getWeatherArrays() {
        return weatherArrays;
    }
}

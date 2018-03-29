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
    private Scraper webScraper;
    
    /**
     * Constructs a Parser object.
     * 
     * @param webAddress - The web address to parse scraped information from
     */
    public Parser(WebAddress webAddress) {
        this.webScraper = new Scraper(webAddress);
        webScraper.scrapeAstronomyAndWeather(this);
    }
    
    public void updateParser(WebAddress newWebAddress) {
        webScraper.changeCity(newWebAddress);
        webScraper.scrapeAstronomyAndWeather(this);
    }
    
    /**
     * Parses astronomy data and inserts it into the AstrnomyArrays.
     * 
     * @param documents - the HTML documents to parse from
     */
    public void parseAstronomyData(Document[] documents) {
        astronomyData = new AstronomyData();
        
        for (Document document : documents) {
            Element table = document.getElementById("as-monthsun").child(1);
            Elements tableRows = table.getElementsByTag("tr");
            astronomyData.insertData(tableRows);
        }
    }
    
    /**
     * Parses weather data and inserts it into the WeatherData.
     * 
     * @param document - the HTML document to parse from
     */
    public void parseWeatherData(Document document) {
        Element table = document.getElementById("wt-5hr").child(0);
        parseTemperatureData(table);
        parseTimeData(table);
        parseImageData(table);
    }
    
    private void parseTemperatureData(Element table) {
        Element temperatureRow = table.getElementsByClass("soft").first();
        Elements temperature = temperatureRow.getElementsByTag("td");
        String[] temperatures = new String[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            temperatures[index] = temperature.get(index).text();
        }
        
        weatherData = new WeatherData(temperatures);
    }
    
    private void parseTimeData(Element table) {
        Element timeRow = table.getElementsByClass("h2").first();
        Elements fiveHourTimes = timeRow.getElementsByTag("td");
        String[] times = new String[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            times[index] = fiveHourTimes.get(index).text();
        }
        
        weatherData.insertFiveHourTimes(times);
    }
    
    private void parseImageData(Element table) {
        Elements images = table.getElementsByTag("img");
        ImagePanel[] imagePanels = new ImagePanel[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            String imageLocation = images.get(index).absUrl("src");
            System.out.println(images.get(index).absUrl("src"));
            imagePanels[index] = new ImagePanel(imageLocation);
        }

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

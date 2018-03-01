package data;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Parses HTML data into something more useful.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Parser {
    private static final int SIZE_OF_TEMPERATURES = 5;
    private AstronomyArrays astronomyArrays;
    private WeatherArrays weatherArrays;
    
    /**
     * Constructs a Parser object.
     */
    public Parser() {
        astronomyArrays = new AstronomyArrays();
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
        Elements temperature = temperatureRow.getElementsByTag("td");
        String[] temperatures = new String[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            temperatures[index] = temperature.get(index).text();
        }
        
        weatherArrays = new WeatherArrays(temperatures);
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

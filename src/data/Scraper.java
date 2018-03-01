package data;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Scrapes timeanddate.com for information.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Scraper {
    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;
    private final String country = "usa";
    private final String city = "honolulu";
    private String domain;
    private String astronomyDir;
    private String weatherDir;
    private String url;
    private Document doc;
    private Parser parser;
    
    /**
     * Constructs a Scraper object.
     * 
     * @param webAddress - The website to be scraped
     */
    public Scraper(String webAddress) {     
        domain = webAddress;
        astronomyDir = "sun/" + country + "/" + city;
        weatherDir = "weather/" + country + "/" + city;
        parser = new Parser();
        
        for (int month = JANUARY; month <= DECEMBER; month++) {
            scrapeAstronomy(month);
        }
        
        scrapeWeather();
    }
    
    /**
     * Scrapes astronmy HTML data for the requested month.
     * 
     * @param month - The month to be scraped
     */
    public void scrapeAstronomy(int month) {
        try {
            url = domain + astronomyDir + "?month=" + month;
            doc = Jsoup.connect(url).get();
            System.out.println(url);
            parser.parseAstronomyData(doc);
        } catch (HttpStatusException e) {
            System.out.println("Invalid URL: " + url);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Scrapes weather HTML data.
     */
    public void scrapeWeather() {
        try {
            url = domain + weatherDir;
            doc = Jsoup.connect(url).get();
            System.out.println(url);
            parser.parseWeatherData(doc);
        } catch (HttpStatusException e) {
            System.out.println("Invalid URL: " + url);
            System.exit(0);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    /**
     * Gets the astronomy arrays from the parser.
     * 
     * @return - astronomyArrays
     */
    public AstronomyArrays getAstronomyArrays() {
        return parser.getAstronomyArrays();
    }
    
    /**
     * Gets the weather arrays from the parser.
     * 
     * @return - weatherArrays
     */
    public WeatherArrays getWeatherArrays() {
        return parser.getWeatherArrays();
    }
}

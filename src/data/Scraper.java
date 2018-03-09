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
    private final String country = "canada";
    private final String city = "vancouver";
    private String domain;
    private String astronomyDir;
    private String weatherDir;
    private String url;
    private Document doc;
    
    /**
     * Constructs a Scraper object.
     * 
     * @param webAddress - The website to be scraped
     */
    public Scraper(String webAddress) {     
        domain = webAddress;
        astronomyDir = "sun/" + country + "/" + city;
        weatherDir = "weather/" + country + "/" + city;
    }
    
    
    public void scrapeAstronomyAndWeather(Parser webParser) {
        for (int month = JANUARY; month <= DECEMBER; month++) {
            scrapeAstronomy(month, webParser);
        }
        
        scrapeWeather(webParser);
    }
    
    /**
     * Scrapes astronmy HTML data for the requested month.
     * 
     * @param month - The month to scrape astronomy data for
     */
    public void scrapeAstronomy(int month, Parser parser) {
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
    public void scrapeWeather(Parser parser) {
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
}

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
    private static final int NUM_OF_DOCS = 12;
    private String domain;
    private String domainExtension;
    private String astronomyDir;
    private String weatherDir;
    private String url;
    private Document doc;
    
    /**
     * Constructs a Scraper object.
     * 
     * @param webAddress - The website to be scraped
     */
    public Scraper(WebAddress webAddress) {     
        domain = webAddress.getDomain();
        domainExtension = webAddress.getCountry() + "/" + webAddress.getCity();
        astronomyDir = "sun/" + domainExtension;
        weatherDir = "weather/" + domainExtension;
    }
    
    /**
     * Change the city to scrape information from.
     * 
     * @param newCity - The new city to scrape information for
     * @param newCountry - The country that the new city belongs to
     */
    public void changeCity(String newCity, String newCountry) {
        domainExtension = newCountry + "/" + newCity;
        astronomyDir = "sun/" + domainExtension;
        weatherDir = "weather/" + domainExtension;
    }
    
    /**
     * Scrapes astronomy and weather information for the city.
     * 
     * @param webParser - The Parser that parses the astronomy and weather data
     */
    public void scrapeAstronomyAndWeather(Parser webParser) {
        scrapeAstronomy(webParser);     
        scrapeWeather(webParser);
    }
    
    /**
     * Scrapes astronomy HTML data.
     * 
     * @param parser - The Parser that parses the astronomy data
     */
    public void scrapeAstronomy(Parser parser) {
        Document[] astroDocs = gatherAstronomyDocuments();
        parser.parseAstronomyData(astroDocs);
    }
    
    /**
     * Gathers a list of astronomy documents into an array of Documents.
     * 
     * @return astroDocs - An array of Documents
     */
    private Document[] gatherAstronomyDocuments() {
        Document[] astroDocs = new Document[NUM_OF_DOCS];
        for (int month = JANUARY; month <= DECEMBER; month++) {
            try {
                url = domain + astronomyDir + "?month=" + month;
                doc = Jsoup.connect(url).get();
                System.out.println(url);
                astroDocs[month - 1] = doc;
            } catch (HttpStatusException e) {
                System.out.println("Invalid URL: " + url);
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }     
        }
        
        return astroDocs;
    }
    
    /**
     * Scrapes weather HTML data.
     * 
     * @param parser - The Parser that parses the weather data
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

package data;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import exceptions.InvalidURLException;

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
    private WebAddress webAddress;
    
    /**
     * Constructs a Scraper object.
     * 
     * @param webAddress - The website to be scraped
     */
    public Scraper(WebAddress webAddress) {
        this.webAddress = webAddress;
        domain = webAddress.getDomain();
        domainExtension = webAddress.getCountry() + "/" + webAddress.getCity();
        astronomyDir = "sun/" + domainExtension;
        weatherDir = "weather/" + domainExtension;
    }
    
    /**
     * Change the city to scrape information from.
     * 
     */
    public void changeCity(WebAddress webAddress) {
        this.webAddress = webAddress;
        domainExtension = webAddress.getCountry() + "/" + webAddress.getCity();
        astronomyDir = "sun/" + domainExtension;
        weatherDir = "weather/" + domainExtension;
    }
    
    /**
     * Scrapes astronomy and weather information for the city.
     * 
     * @param webParser - The Parser that parses the astronomy and weather data
     */
    public void scrapeAstronomyAndWeather(Parser webParser) {
        try {
            scrapeAstronomy(webParser);     
            scrapeWeather(webParser);   
        } catch (InvalidURLException i) {
            //System.exit(0);
        }
    }
    
    /**
     * Scrapes astronomy HTML data.
     * 
     * @param parser - The Parser that parses the astronomy data
     */
    public void scrapeAstronomy(Parser parser) throws InvalidURLException {
        Document[] astroDocs = gatherAstronomyDocuments();
        parser.parseAstronomyData(astroDocs);   
    }
    
    /**
     * Gathers a list of astronomy documents into an array of Documents.
     * 
     * @return astroDocs - An array of Documents
     */
    private Document[] gatherAstronomyDocuments() throws InvalidURLException {
        Document[] astroDocs = new Document[NUM_OF_DOCS];
        for (int month = JANUARY; month <= DECEMBER; month++) {
            String monthExtension = "?month=" + month;
            try {
                url = domain + astronomyDir + monthExtension;
                doc = Jsoup.connect(url).get();
            } catch (HttpStatusException e) {
                String extension = astronomyDir + monthExtension;
                throw new InvalidURLException(webAddress, extension);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(url);
            astroDocs[month - 1] = doc;
        }
        
        return astroDocs;
    }
    
    /**
     * Scrapes weather HTML data.
     * 
     * @param parser - The Parser that parses the weather data
     */
    public void scrapeWeather(Parser parser) throws InvalidURLException {
        try {
            url = domain + weatherDir;
            doc = Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            throw new InvalidURLException(webAddress, weatherDir);
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println(url);
        parser.parseWeatherData(doc);
    }
}

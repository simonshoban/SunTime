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
    private String timeDir;
    private String url;
    private Document doc;
    private WebAddress webAddress;
    private WebAddress backupWebAddress;
    
    /**
     * Constructs a Scraper object.
     * 
     * @param webAddress - The website to be scraped
     */
    public Scraper(WebAddress webAddress) {
        this.webAddress = webAddress;
        backupWebAddress = webAddress;
        domain = webAddress.getDomain();
        domainExtension = webAddress.getCountry() + "/" + webAddress.getCity();
        astronomyDir = "sun/" + domainExtension;
        weatherDir = "weather/" + domainExtension;
        timeDir = "time/zone/" + domainExtension;
    }
    
    /**
     * Change the city to scrape information from.
     * 
     * @param newLocation - The website to be scraped
     */
    public void changeCity(WebAddress newLocation) {
        this.webAddress = newLocation;
        domainExtension = webAddress.getCountry() + "/" + webAddress.getCity();
        astronomyDir = "sun/" + domainExtension;
        weatherDir = "weather/" + domainExtension;
        timeDir = "time/zone/" + domainExtension;
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
            backupWebAddress = webAddress;
        } catch (InvalidURLException i) {
            i.printErrorMessage();
            webAddress = backupWebAddress;
        }
        
        System.out.println();
    }
    
    /**
     * Scrapes astronomy HTML data.
     * 
     * @param parser - The Parser that parses the astronomy data
     * @throws InvalidURLException if invalid URL is used
     */
    public void scrapeAstronomy(Parser parser) throws InvalidURLException {
        Document[] astroDocs = gatherAstronomyDocuments();
        parser.parseAstronomyData(astroDocs);   
    }
    
    /**
     * Gathers a list of astronomy documents into an array of Documents.
     * 
     * @return astroDocs - An array of Documents
     * @throws InvalidURLException if invalid URL is used
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
     * @throws InvalidURLException if invalid URL is used
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
    
    public void scrapeTimeZone(Parser parser) throws InvalidURLException {
        try {
            url = domain + timeDir;
            doc = Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            throw new InvalidURLException(webAddress, timeDir);
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println(url);
        parser.parseTimeZone(doc);
    }
    
    /**
     * Gets the WebAddress object.
     * 
     * @return webAddress as a WebAddress
     */
    public WebAddress getWebAddress() {
        return webAddress;
    }
}

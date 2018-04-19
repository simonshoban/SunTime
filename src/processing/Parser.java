package processing;

import data.AstronomyData;
import data.TemporalData;
import data.WeatherData;
import data.WebAddress;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import exceptions.ExceptionFactory;
import exceptions.InvalidURLException;

/**
 * Constructs a private Scraper and parses it's HTML data into SunTimeData.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Parser {
    private static final int SIZE_OF_TEMPERATURES = 6;
    private AstronomyData astronomyData;
    private WeatherData weatherData;
    private TemporalData temporalData;
    private Scraper webScraper;
    private WebAddress webAddress;
    private WebAddress backupWebAddress;
    
    /**
     * Constructs a Parser object.
     * 
     * @param webAddress - The web address to parse scraped information from
     */
    public Parser(WebAddress webAddress) {
        this.webAddress = webAddress;
        this.webScraper = new Scraper();
        webScraper.scrapeEverything();
    }
    
    /**
     * Updates the data stored in the parser according to the new web address.
     * 
     * @param newWebAddress - The new web address
     */
    public void updateParser(WebAddress newWebAddress) {
        this.webAddress = newWebAddress;
        webScraper.changeCity();
        webScraper.scrapeEverything();
    }
    
    /**
     * Parses astronomy data and inserts it into the AstronomyData.
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
    
    /**
     * Parses the temperature data and inserts it into the WeatherData.
     * 
     * @param table - The HTML table to parse
     */
    private void parseTemperatureData(Element table) {
        Element temperatureRow = table.getElementsByClass("soft").first();
        Elements temperature = temperatureRow.getElementsByTag("td");
        String[] temperatures = new String[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            temperatures[index] = temperature.get(index).text();
        }
        
        weatherData = new WeatherData(temperatures);
    }
    
    /**
     * Parses the time data and inserts it into the WeatherData.
     * 
     * @param table - The HTML table to parse
     */
    private void parseTimeData(Element table) {
        Element timeRow = table.getElementsByClass("h2").first();
        Elements fiveHourTimes = timeRow.getElementsByTag("td");
        String[] times = new String[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            times[index] = fiveHourTimes.get(index).text();
        }
        
        weatherData.insertFiveHourTimes(times);
    }
    
    /**
     * Parses the image data and inserts it into the WeatherData.
     * 
     * @param table - The HTML table to parse
     */
    private void parseImageData(Element table) {
        Elements images = table.getElementsByTag("img");
        BufferedImage[] weatherImages = new BufferedImage[SIZE_OF_TEMPERATURES];
        
        for (int index = 0; index < SIZE_OF_TEMPERATURES; index++) {
            String imageLocation = images.get(index).absUrl("src");
            System.out.println(images.get(index).absUrl("src"));
            try {
                URL url = new URL(imageLocation);
                File file = new File("not_null");
                
                FileUtils.copyURLToFile(url, file);                
                weatherImages[index] = ImageIO.read(file);
                file.delete();
            } catch (IOException i) {
                i.printStackTrace();
            }  
        }

        weatherData.insertWeatherImages(weatherImages);
    }
    
    /**
     * Parses time zone data and inserts it into the TemporalData.
     * 
     * @param document - The HTML document to parse
     */
    public void parseTimeZone(Document document) {
        temporalData = new TemporalData();
        
        Element qFactsDiv = document.getElementById("qfacts");
        Elements mgt25 = qFactsDiv.getElementsByClass("mgt25");
        String timeZoneString = mgt25.get(0).getElementsByClass("big").first().text();
        
        temporalData.insertTimeZone(timeZoneString);
    }
    
    /**
     * Gets the astronomy data.
     * 
     * @return - astronomyData as an AstronomyData
     */
    public AstronomyData getAstronomyData() {
        return astronomyData;
    }
    
    /**
     * Gets the weather data.
     * 
     * @return - weatherData as a WeatherData
     */
    public WeatherData getWeatherData() {
        return weatherData;
    }
    
    /**
     * Gets the temporal data.
     * 
     * @return - temporalData as a TemporalData
     */
    public TemporalData getTemporalData() {
        return temporalData;
    }
    
    /**
     * Gets the WebAddress object.
     * 
     * @return webAddress as a WebAddress
     */
    public WebAddress getWebAddress() {
        return webAddress;
    }
    
    /**
     * Asks the scraper if the given web address is valid.
     * 
     * @param testAddress - The WedAddress to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidAddress(WebAddress testAddress) {
        return webScraper.isValidWebAddress(testAddress);
    }

    /**
     * Scrapes timeanddate.com for information.
     * 
     * @author Simon Shoban
     * @version 1.0
     */
    private class Scraper {
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
        
        /**
         * Constructs a Scraper object.
         */
        Scraper() {
            backupWebAddress = webAddress;
            domain = webAddress.getDomain();
            changeCity();
        }
        
        /**
         * Change the city to scrape information from.
         */
        private void changeCity() {
            domainExtension = webAddress.getCountry() + "/" + webAddress.getCity();
            astronomyDir = "sun/" + domainExtension;
            weatherDir = "weather/" + domainExtension;
            timeDir = "time/zone/" + domainExtension;
        }
        
        /**
         * Checks if the given WebAddress is valid.
         * @param testAddress the WebAddress object to test
         * @return true if valid, false if invalid
         */
        public boolean isValidWebAddress(WebAddress testAddress) {
            webAddress = testAddress;
            changeCity();
            try {                
                Jsoup.connect(domain + astronomyDir).get();
                Jsoup.connect(domain + weatherDir).get();
                Jsoup.connect(domain + timeDir).get();
                return true;
            } catch (HttpStatusException e) {
                useBackupAddress();
                return false;
            } catch (IOException i) {
                useBackupAddress();
                return false;
            }
        }
        
        /**
         * Scrapes astronomy and weather information for the city.
         */
        private void scrapeEverything() {
            try {
                scrapeAstronomy();     
                scrapeWeather();
                scrapeTimeZone();
                updateBackupAddress();
            } catch (InvalidURLException i) {
                i.printErrorMessage();
                useBackupAddress();
            }
        }
        
        /**
         * Replace the current backup address with a new successful web address.
         */
        private void updateBackupAddress() {
            backupWebAddress = webAddress;
        }
        
        /**
         * Uses the backup address in case the new address causes an error.
         */
        private void useBackupAddress() {
            webAddress = backupWebAddress;
            changeCity();
        }
        
        /**
         * Scrapes astronomy HTML data.
         * 
         * @throws InvalidURLException if invalid URL is used
         */
        private void scrapeAstronomy() throws InvalidURLException {
            Document[] astroDocs = gatherAstronomyDocuments();
            parseAstronomyData(astroDocs);   
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
                    ExceptionFactory.throwInvalidURLException(webAddress, extension);
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
         * @throws InvalidURLException if invalid URL is used
         */
        private void scrapeWeather() throws InvalidURLException {
            try {
                url = domain + weatherDir;
                doc = Jsoup.connect(url).get();
            } catch (HttpStatusException e) {
                ExceptionFactory.throwInvalidURLException(webAddress, weatherDir);
            } catch (IOException i) {
                i.printStackTrace();
            }
            System.out.println(url);
            parseWeatherData(doc);
        }
        
        /**
         * Scrapes information relating to the time zone of the location.
         * 
         * @throws InvalidURLException if invalid URL is used
         */
        private void scrapeTimeZone() throws InvalidURLException {
            try {
                url = domain + timeDir;
                doc = Jsoup.connect(url).get();
            } catch (HttpStatusException e) {
                ExceptionFactory.throwInvalidURLException(webAddress, timeDir);
            } catch (IOException i) {
                i.printStackTrace();
            }
            System.out.println(url);
            parseTimeZone(doc);
        }
    }
}

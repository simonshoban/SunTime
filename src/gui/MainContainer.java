package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.Timer;

import data.WebAddress;
import toolkit.Toolkit;
import processing.Parser;

/**
 * The outermost JPanel container.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MainContainer extends JPanel {
    private static final int COLOURS_TIMER_DELAY = 1000;
    private static final int SCRAPER_TIMER_DELAY = 3600000;
    private WindowFrame frame;
    private Parser parser;
    private ArrayList<SunTimePanel> sunTimePanels;
    private WeatherPanel temperaturePanel;
    private AstronomyPanel sunPanel;
    private TimePanel timePanel;
    private SliderPanel sliderPanel;
    private LocationPanel locationPanel;
    private Timer coloursTimer;
    private Timer scraperTimer;
    private ZoneId zoneID;
    
    /**
     * Constructs a MainContainer object.
     * 
     * @param frame - The WindowFrame that this MainContainer belongs to
     * @param parser - The parser used to create the weather and astronomy data
     */
    public MainContainer(WindowFrame frame, Parser parser) {
        this.frame = frame;
        this.parser = parser;
        zoneID = ZoneId.systemDefault();
        
        setLayout(new BorderLayout());
        
        createPanels();
        createColoursTimer();
        synchronizeHourlyScraping();                       
        fillSunTimePanels();
    }
    
    /**
     * Creates all the panels.
     */
    private void createPanels() {
        sunPanel = new AstronomyPanel(parser.getAstronomyData());
        temperaturePanel = new WeatherPanel(parser.getWeatherData());
        timePanel = new TimePanel(zoneID);       
        sliderPanel = new SliderPanel(this);
        locationPanel = new LocationPanel(this);        
    }
    
    /**
     * Creates the coloursTimer.
     */
    private void createColoursTimer() {
        coloursTimer = new Timer(COLOURS_TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateColours();
            }
        });        
    }
    
    /**
     * Adds all SunTimePanels to the sunTimePanels ArrayList.
     */
    private void fillSunTimePanels() {
        sunTimePanels = new ArrayList<SunTimePanel>();
        sunTimePanels.add(sunPanel);
        sunTimePanels.add(temperaturePanel);
        sunTimePanels.add(timePanel);
        sunTimePanels.add(locationPanel);   
    }
    
    /**
     * Initializes the MainContainer.
     */
    public void init() {
        initSunTimePanels();
        coloursTimer.start();
        
        addElements();
    }
    
    /**
     * Initialize every SunTimePanel.
     */
    private void initSunTimePanels() {
        for (SunTimePanel panel : sunTimePanels) {
            panel.init();
        }
    }
    
    /**
     * Adds all panels to the MainContainer.
     */
    private void addElements() {
        add(sunPanel, BorderLayout.WEST);
        add(sliderPanel, BorderLayout.SOUTH);
        add(timePanel, BorderLayout.NORTH);
        add(temperaturePanel, BorderLayout.CENTER);
        add(locationPanel, BorderLayout.EAST);       
    }
    
    /**
     * Updates the daily information displayed in the panels.
     * 
     * @param dayOfYear - The day of the year to display information for
     */
    public void updateDailyInformation(int dayOfYear) {
        sunPanel.updateAstronomyInfo(dayOfYear);
        timePanel.updateTime(dayOfYear);
    }
    
    /**
     * Scrapes new information based on the new given location.
     * 
     * @param newLocation - The new location
     */
    public void scrapeNewInfo(WebAddress newLocation) {
        updateWebAddress(newLocation);
        updateSunTimeData();
        updateDailyInformation(getDayOfYear());
        updateTimeZonesOfDynamicPanels();
        updateSlider(getDayOfYear());
        synchronizeHourlyScraping();
    }
    
    /**
     * Gets the day of the year from the zoneID.
     * 
     * @return - the day of the year according to the zoneID
     */
    private int getDayOfYear() {
        return ZonedDateTime.now(zoneID).getDayOfYear();
    }
    
    /**
     * Updates objects that hold WebAddresses.
     * 
     * @param newLocation - The new WebAddress
     */
    private void updateWebAddress(WebAddress newLocation) {
        if (parser.isValidAddress(newLocation)) {
            parser.updateParser(newLocation);
            frame.updateTitle(newLocation);
            zoneID = parser.getTemporalData().getTimeZone();
        }        
    }
    
    /**
     * Updates the panels that hold SunTimeData.
     */
    private void updateSunTimeData() {
        sunPanel.updateAstronomyData(parser.getAstronomyData());
        temperaturePanel.updateWeatherData(parser.getWeatherData());       
    }
    
    /**
     * Updates the time zones of the dynamic panels.
     */
    private void updateTimeZonesOfDynamicPanels() {
        timePanel.updateTimeZone(zoneID);
        
        for (SunTimePanel sunTimePanel : sunTimePanels) {
            sunTimePanel.changeTimeZone(zoneID);
        }
    }
    
    /**
     * Updates the background colours of each SunTimePanel depending 
     * on the time of day.
     */
    private void updateColours() {
        for (SunTimePanel sunTimePanel : sunTimePanels) {
            sunTimePanel.updateColours();
            repaint();
        }
    }
    
    /**
     * Updates the JSlider in the SliderPanel to the given day.
     * @param dayOfYear - the day to set the slider to
     */
    private void updateSlider(int dayOfYear) {
        sliderPanel.setSliderTo(dayOfYear);
    }
    
    /**
     * Synchronizes the hourly scraping to the clock.
     */
    private void synchronizeHourlyScraping() {
        if (isExactHour()) {
            scrapeHourly();
        } else {
            scrapeAtTheNextHour();
        }
    }
    
    /**
     * Scrapes new information at the next hour.
     */
    private void scrapeAtTheNextHour() {
        ZonedDateTime nextHour = getTheNextExactHour();
        long difference = getDifferenceFromNextHour(nextHour);
        createNextHourTimer(difference);
    }
    
    /**
     * Creates a local timer that will scrape at the next hour. 
     * 
     * @param delay - The delay before scraping
     */
    private void createNextHourTimer(long delay) {
        System.out.println("Will scrape in " + delay + " milliseconds");
        
        Toolkit.setTimeout(new TimerTask() {
            public void run() {
                scrapeHourly();
            }
        }, delay);
    }
    
    /**
     * Gets the time difference between now and the next hour.
     * 
     * @param nextHour - The LocalTime object representing the next hour
     * @return the number of milliseconds between now and the next hour
     */
    private long getDifferenceFromNextHour(ZonedDateTime nextHour) {
        long difference;
        
        difference = ZonedDateTime.now(zoneID).until(nextHour, ChronoUnit.SECONDS);
        difference = Toolkit.convertSecondsToMilliseconds(difference);
        
        return difference;
    }
    
    /**
     * Gets the next exact hour.
     * 
     * @return the next exact hour
     */
    private ZonedDateTime getTheNextExactHour() {
        ZonedDateTime nextHour = ZonedDateTime.now(zoneID);
        
        nextHour = nextHour.plusHours(1);
        nextHour = nextHour.withMinute(0);
        nextHour = nextHour.withSecond(0);    
        
        return nextHour;
    }
    
    /**
     * Checks if it's an exact hour right now.
     * 
     * @return true if it's an exact hour, false otherwise
     */
    private boolean isExactHour() {
        ZonedDateTime now = ZonedDateTime.now(zoneID);
        
        return now.getMinute() == 0 && now.getSecond() == 0;
    }
    
    /**
     * Scrapes new information at the hour.
     */
    private void scrapeHourly() {
        scrapeNewInfo(parser.getWebAddress());     
        createScraperTimer();
        
        scraperTimer.start();
    }
    
    /**
     * Creates the ScraperTimer.
     */
    private void createScraperTimer() {
        scraperTimer = new Timer(SCRAPER_TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrapeNewInfo(parser.getWebAddress());
            }
        });       
    }
}

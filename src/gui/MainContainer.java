package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import data.WeatherData;
import data.WebAddress;
import toolkit.Toolkit;
import data.AstronomyData;
import data.Parser;

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
    
    /**
     * Constructs a MainContainer object.
     * 
     * @param astronomyData - Used to construct the AstronomyPanel
     * @param weatherData - Used to construct the WeatherPanel
     * @param frame - The WindowFrame that this MainContainer belongs to
     * @param parser - The parser used to create the weather and astronomy data
     */
    public MainContainer(AstronomyData astronomyData, WeatherData weatherData, WindowFrame frame, Parser parser) {
        this.frame = frame;
        this.parser = parser;
        
        setLayout(new BorderLayout());
        
        sunPanel = new AstronomyPanel(astronomyData);
        temperaturePanel = new WeatherPanel(weatherData);
        timePanel = new TimePanel();       
        sliderPanel = new SliderPanel(this);
        locationPanel = new LocationPanel(this);
        
        coloursTimer = new Timer(COLOURS_TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateColours();
            }
        });
        
        synchronizeHourlyScraping();
        
        sunTimePanels = new ArrayList<SunTimePanel>();
        
        fillSunTimePanels();
    }
    
    /**
     * Adds all SunTimePanels to the sunTimePanels ArrayList.
     */
    private void fillSunTimePanels() {
        sunTimePanels.add(sunPanel);
        sunTimePanels.add(temperaturePanel);
        sunTimePanels.add(timePanel);
        sunTimePanels.add(locationPanel);   
    }
    
    /**
     * Initializes the MainContainer.
     */
    public void init() {
        sunPanel.init();
        temperaturePanel.init();
        coloursTimer.start();
        
        addElements();

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
        parser.updateParser(newLocation);
        sunPanel.updateAstronomyData(parser.getAstronomyArrays());
        temperaturePanel.updateWeatherData(parser.getWeatherArrays());
        frame.updateTitle(newLocation);
        
        updateDailyInformation(sliderPanel.getDay());
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
     * Synchronizes the hourly scraping to the clock.
     */
    private void synchronizeHourlyScraping() {
        if (isExactHour()) {
            scrapeHourly();
        } else {
            scrapeAtTheHour();
        }
    }
    
    /**
     * Scrapes new information at the next hour.
     */
    private void scrapeAtTheHour() {
        LocalTime nextHour = getTheNextExactHour();
        long difference = getDifferenceFromNextHour(nextHour);
        
        Toolkit.setTimeout(() -> scrapeHourly(), difference);       
    }
    
    /**
     * Gets the time difference between now and the next hour.
     * 
     * @param nextHour - The LocalTime object representing the next hour
     * @return the number of milliseconds between now and the next hour
     */
    private long getDifferenceFromNextHour(LocalTime nextHour) {
        long difference;
        
        difference = LocalTime.now().until(nextHour, ChronoUnit.SECONDS);           
        difference = Toolkit.convertSecondsToMilliseconds(difference);
        
        return difference;
    }
    
    /**
     * Gets the next exact hour.
     * 
     * @return the next exact hour
     */
    private LocalTime getTheNextExactHour() {
        LocalTime nextHour = LocalTime.now();
        
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
        LocalTime now = LocalTime.now();
        
        return now.getMinute() == 0 && now.getSecond() == 0;
    }
    
    /**
     * Scrapes new information at the hour.
     */
    private void scrapeHourly() {
        scrapeNewInfo(parser.getScraper().getWebAddress());     
        createScraperTimer();
        
        scraperTimer.start();
    }
    
    /**
     * Creates the ScraperTimer.
     */
    private void createScraperTimer() {
        scraperTimer = new Timer(SCRAPER_TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrapeNewInfo(parser.getScraper().getWebAddress());
            }
        });       
    }
}

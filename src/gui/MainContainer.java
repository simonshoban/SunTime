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
    
    public void scrapeNewInfo(WebAddress newLocation) {
        parser.updateParser(newLocation);
        sunPanel.updateAstronomyData(parser.getAstronomyArrays());
        temperaturePanel.updateWeatherData(parser.getWeatherArrays());
        
        updateDailyInformation(sliderPanel.getDay());
        
        frame.updateTitle(newLocation);
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
    
    private void synchronizeHourlyScraping() {
        LocalTime now = LocalTime.now();
        if (now.getMinute() == 0 && now.getSecond() == 0) {

            scrapeHourly();
        } else {
            LocalTime nextHour = LocalTime.now();
            
            nextHour = nextHour.plusHours(1);
            nextHour = nextHour.withMinute(0);
            nextHour = nextHour.withSecond(0);
            
            long difference = LocalTime.now().until(nextHour, ChronoUnit.SECONDS) * 1000;
            
            System.out.println("difference: " + difference);
            
            Toolkit.setTimeout(() -> scrapeHourly(), difference);
        }
    }
    
    private void scrapeHourly() {
        scrapeNewInfo(parser.getScraper().getWebAddress());
        
        scraperTimer = new Timer(SCRAPER_TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrapeNewInfo(parser.getScraper().getWebAddress());
            }
        });
        
        scraperTimer.start();
    }
    
   
}

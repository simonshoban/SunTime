package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import data.WeatherData;
import data.WebAddress;
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
    private static final int TIMER_DELAY = 1000;
    private WindowFrame frame;
    private Parser parser;
    private ArrayList<SunTimePanel> sunTimePanels;
    private WeatherPanel temperaturePanel;
    private AstronomyPanel sunPanel;
    private TimePanel timePanel;
    private SliderPanel sliderPanel;
    private LocationPanel locationPanel;
    private Timer timer;
    
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
        
        timer = new Timer(TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateColours();
            }
        });
        
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
        sunPanel.init();
        temperaturePanel.init();
        timer.start();
        
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
    
    public void changeLocation(WebAddress newLocation) {
        parser.updateParser(newLocation);
        sunPanel = new AstronomyPanel(parser.getAstronomyArrays());
        temperaturePanel = new WeatherPanel(parser.getWeatherArrays());
        
        sunPanel.init();
        sunPanel.revalidate();
        sunPanel.repaint();
        temperaturePanel.init();
        
        add(sunPanel, BorderLayout.WEST);
        add(temperaturePanel, BorderLayout.CENTER);
        
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
}

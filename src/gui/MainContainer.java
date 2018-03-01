package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import data.Scraper;

/**
 * The outermost JPanel container.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MainContainer extends JPanel {
    private static final int TIMER_DELAY = 1000;
    private ArrayList<SunTimePanel> sunTimePanels;
    private Scraper scraper;
    private WeatherPanel temperaturePanel;
    private AstronomyPanel sunPanel;
    private TimePanel timePanel;
    private SliderPanel sliderPanel;
    private Timer timer;
    
    /**
     * Constructs a MainContainer object.
     * 
     * @param webAddress - The web address to be passed to the Scraper object
     */
    public MainContainer(String webAddress) {
        setLayout(new BorderLayout());
        
        scraper = new Scraper(webAddress);
        sunPanel = new AstronomyPanel(scraper.getAstronomyArrays());
        temperaturePanel = new WeatherPanel(scraper.getWeatherArrays());
        timePanel = new TimePanel();       
        sliderPanel = new SliderPanel(this);
        
        timer = new Timer(TIMER_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateColours();
            }
        });
        
        sunTimePanels = new ArrayList<SunTimePanel>();
        
        sunTimePanels.add(sunPanel);
        sunTimePanels.add(temperaturePanel);
        sunTimePanels.add(timePanel);
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
    }
    
    /**
     * Updates all the information displayed in the panels.
     * 
     * @param dayOfYear - The day of the year to display information for
     */
    public void updateDisplayedInformation(int dayOfYear) {
        sunPanel.updateAstronomyInfo(dayOfYear);
        timePanel.updateTime(dayOfYear);
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

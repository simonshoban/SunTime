package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import data.WeatherArrays;
import data.AstronomyArrays;

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
    private WeatherPanel temperaturePanel;
    private AstronomyPanel sunPanel;
    private TimePanel timePanel;
    private SliderPanel sliderPanel;
    private Timer timer;
    
    /**
     * Constructs a MainContainer object.
     */
    public MainContainer(AstronomyArrays astronomyArrays, WeatherArrays weatherArrays) {
        setLayout(new BorderLayout());
        
        sunPanel = new AstronomyPanel(astronomyArrays);
        temperaturePanel = new WeatherPanel(weatherArrays);
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

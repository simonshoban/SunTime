package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.WeatherArrays;

/**
 * Displays weather information.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WeatherPanel extends SunTimePanel {
    private static final Font TEXT_FONT = new Font(
            "Helvetica", 
            Font.PLAIN, 
            60);
    
    private JLabel temperature;
    private JSlider fiveHours;
    private WeatherArrays weatherArrays;
    
    /**
     * Constructs a WeatherPanel object.
     * 
     * @param weatherArrays - The weather information to be displayed
     */
    public WeatherPanel(WeatherArrays weatherArrays) {
        this.weatherArrays = weatherArrays;
        temperature = new JLabel(weatherArrays.getNextFiveHours()[0]);
        fiveHours = new JSlider(JSlider.VERTICAL, 0, 4, 0);
    }
    
    /**
     * Initializes the WeatherPanel.
     */
    public void init() {
        setElementFont();
        setElementBackground();
        setElementForeground();
        setLabelAlignment();
        setSliderTickers();
        addSliderListener();
        setSliderSize();
        addElements();
    }
    
    /**
     * Sets the font of the elements.
     */
    private void setElementFont() {
        temperature.setFont(TEXT_FONT);
    }
    
    /**
     * Sets the background colour of the elements.
     */
    private void setElementBackground() {
        fiveHours.setBackground(getBackgroundColour());
    }
    
    /**
     * Sets the foreground colour of the elements.
     */
    private void setElementForeground() {
        temperature.setForeground(Color.white);
        fiveHours.setForeground(Color.white);
    }
    
    /**
     * Sets the horizontal and vertical alignment of the JLabel.
     */
    private void setLabelAlignment() {
        temperature.setHorizontalAlignment(JLabel.CENTER);
        temperature.setVerticalAlignment(JLabel.CENTER);       
    }
    
    /**
     * Adds a ChangeListener to the JSlider.
     */
    private void addSliderListener() {
        fiveHours.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                updateFiveHourInformation(fiveHours.getValue());
            }
        });        
    }
    
    /**
     * Sets and enables slider tickers.
     */
    private void setSliderTickers() {
        fiveHours.setMajorTickSpacing(1);
        fiveHours.setPaintLabels(true);
    }
    
    /**
     * Set the slider size and inverts the direction.
     */
    private void setSliderSize() {
        //fiveHours.setPreferredSize(new Dimension(50, 10));
        fiveHours.setInverted(true);
    }
    
    /**
     * Adds the Swing elements to the WeatherPanel.
     */
    private void addElements() {
        add(fiveHours, BorderLayout.WEST);
        add(temperature, BorderLayout.CENTER);        
    }
    
    /**
     * Updates the display five hour forecast information.
     * 
     * @param hour - How many hours from now to display weather for
     */
    public void updateFiveHourInformation(int hour) {
        temperature.setText(weatherArrays.getNextFiveHours()[hour]);
    }
    
    @Override
    public void updateColours() {
        super.updateColours();
        if (fiveHours != null) {
            fiveHours.setBackground(getBackgroundColour());
        }
    }
}

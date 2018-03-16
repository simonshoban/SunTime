package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
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
    private JSlider nextFiveHours;
    private JLabel image;
    private WeatherArrays weatherArrays;
    
    /**
     * Constructs a WeatherPanel object.
     * 
     * @param weatherArrays - The weather information to be displayed
     */
    public WeatherPanel(WeatherArrays weatherArrays) {
        this.weatherArrays = weatherArrays;
        temperature = new JLabel(weatherArrays.getNextFiveHours()[0]);
        image = new JLabel(new ImageIcon(weatherArrays.getWeatherImages()[0].getImage()));
        nextFiveHours = new JSlider(JSlider.VERTICAL, 0, 5, 0);
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
        nextFiveHours.setBackground(getBackgroundColour());
    }
    
    /**
     * Sets the foreground colour of the elements.
     */
    private void setElementForeground() {
        temperature.setForeground(Color.white);
        nextFiveHours.setForeground(Color.white);
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
        nextFiveHours.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                updateFiveHourInformation(nextFiveHours.getValue());
                updateWeatherImage(nextFiveHours.getValue());
            }
        });        
    }
    
    /**
     * Sets and enables slider tickers.
     */
    private void setSliderTickers() {
        nextFiveHours.setMajorTickSpacing(1);
        nextFiveHours.setPaintLabels(true);
    }
    
    /**
     * Set the slider size and inverts the direction.
     */
    private void setSliderSize() {
        //nextFiveHours.setPreferredSize(new Dimension(50, 10));
        nextFiveHours.setInverted(true);
    }
    
    /**
     * Adds the Swing elements to the WeatherPanel.
     */
    private void addElements() {
        add(nextFiveHours, BorderLayout.WEST);
        add(temperature, BorderLayout.CENTER);
        add(image, BorderLayout.NORTH);
    }
    
    /**
     * Updates the displayed five hour forecast information.
     * 
     * @param hour - How many hours from now to display weather for
     */
    public void updateFiveHourInformation(int hour) {
        temperature.setText(weatherArrays.getNextFiveHours()[hour]);
    }
    
    /**
     * Updates the displayed weather image.
     * 
     * @param hour - How many hours from now to display the image for
     */
    public void updateWeatherImage(int hour) {
        ((ImageIcon) image.getIcon()).setImage(weatherArrays.getWeatherImages()[hour].getImage());
        repaint();
    }
    
    @Override
    public void updateColours() {
        super.updateColours();
        if (nextFiveHours != null) {
            nextFiveHours.setBackground(getBackgroundColour());
        }
    }
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDateTime;

import javax.swing.JPanel;

/**
 * A JPanel that changes its background colour depending on the time of day.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SunTimePanel extends JPanel {
    private static final int RED_MULTIPLIER = 8;
    private static final int GREEN_MULTIPLIER = 12;
    private static final int BLUE_MULTIPLIER = 15;
    
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_HALF_DAY = 43200;
    
    private static final int[] RED_TIMES = {0, 96};
    private static final int[] GREEN_TIMES = {36, 180};
    private static final int[] BLUE_TIMES = {72, 255};
    private static final int[] MULTIPLIER_TIMES = {1, -1};
    
    private double red;
    private double green;
    private double blue;
    private double seconds;
    private int timeOfDay;
    private int multiplier;
    
    private Color backgroundColour;
    
    /**
     * Constructs a SunTimePanel object.
     */
    public SunTimePanel() {
        setLayout(new BorderLayout());
        updateColours();
    }
    
    /**
     * Updates the background colour depending on the time of day.
     */
    public void updateColours() {
        resetValues(); 
        
        red = calculateColour(red, RED_MULTIPLIER);
        green = calculateColour(green, GREEN_MULTIPLIER);
        blue = calculateColour(blue, BLUE_MULTIPLIER);
        
        backgroundColour = new Color((int) red, (int) green, (int) blue);
        
        setBackground(backgroundColour);
    }
    
    /**
     * Resets the values of the colour algorithm.
     */
    private void resetValues() {
        timeOfDay = (isAfternoon()) ? 1 : 0;
        multiplier = MULTIPLIER_TIMES[timeOfDay];
        red = RED_TIMES[timeOfDay];
        green = GREEN_TIMES[timeOfDay];
        blue = BLUE_TIMES[timeOfDay];
        
        calculateSeconds();
    }
    
    /**
     * Calculates the seconds needed for the algorithm.
     */
    private void calculateSeconds() {
        double radius = SECONDS_IN_HALF_DAY;
        double x = LocalDateTime.now().toLocalTime().toSecondOfDay() - SECONDS_IN_HALF_DAY;
        
        seconds = Math.sqrt(Math.pow(radius, 2) - Math.pow(x, 2));
    }
    
    /**
     * Tells you if it's afternoon or not.
     * 
     * @return true if it's past 12, false otherwise
     */
    private boolean isAfternoon() {
        return seconds > SECONDS_IN_HALF_DAY;
    }
    
    /**
     * Calculates the colour values for the background display.
     * 
     * @param colour - The colour value to calculate
     * @param colourMultiplier - The corresponding multiplier for the colour
     * @return the correct colour value
     */
    private double calculateColour(double colour, int colourMultiplier) {
        return colour + multiplier * seconds / SECONDS_IN_HOUR * colourMultiplier;
    }
    
    /**
     * Gets the current background colour of this SunTimePanel.
     * 
     * @return backgroundColour as a Color
     */
    public Color getBackgroundColour() {
        return backgroundColour;
    }
}

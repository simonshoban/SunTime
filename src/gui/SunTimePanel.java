package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JPanel;

/**
 * A JPanel that changes its background colour depending on the time of day.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class SunTimePanel extends JPanel {
    private static final int RED_MULTIPLIER = 8;
    private static final int GREEN_MULTIPLIER = 12;
    private static final int BLUE_MULTIPLIER = 15;
    
    private static final double SECONDS_IN_HOUR = 3600;
    private static final double SECONDS_IN_HALF_DAY = 43200;
    private static final double SECONDS_IN_WHOLE_DAY = 86400;
    
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
    private ZonedDateTime today;
    private ZoneId zoneID;
    
    /**
     * Constructs a SunTimePanel object.
     */
    public SunTimePanel() {
        zoneID = ZoneId.systemDefault();
        today = ZonedDateTime.now(zoneID);
        setLayout(new BorderLayout());
        updateColours();
    }
    
    /**
     * Initializes the SunTimePanel.
     */
    public abstract void init();
    
    /**
     * Updates the background colour depending on the time of day.
     */
    public void updateColours() {
        resetValues(); 
        calculateColours();
        updateBackground();
        //printColourValues();
    }   
    
    /**
     * Changes the time zone of the SunTimePanel.
     * 
     * @param timeZone - The TimeZone object representing the new time zone
     */
    public void changeTimeZone(ZoneId timeZone) {
        this.zoneID = timeZone;
        today = ZonedDateTime.now(zoneID);
    }
    
    /**
     * Gets the current background colour of this SunTimePanel.
     * 
     * @return backgroundColour as a Color
     */
    public Color getBackgroundColour() {
        return backgroundColour;
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
        today = ZonedDateTime.now(zoneID);
        
        calculateSeconds();
    }
    
    /**
     * Calculates the RGB colour values.
     */
    private void calculateColours() {
        red = calculateColour(red, RED_MULTIPLIER);
        green = calculateColour(green, GREEN_MULTIPLIER);
        blue = calculateColour(blue, BLUE_MULTIPLIER);        
    }
    
    /**
     * Updates the background to the new colour.
     */
    private void updateBackground() {
        backgroundColour = new Color((int) red, (int) green, (int) blue);
        
        setBackground(backgroundColour);        
    }
    
    /**
     * Calculates the seconds needed for the algorithm.
     */
    private void calculateSeconds() {
        int now = today.toLocalDateTime().toLocalTime().toSecondOfDay();
        double a = -1 / SECONDS_IN_HALF_DAY;
        double xSquared = Math.pow(now, 2);
        double x = now * SECONDS_IN_WHOLE_DAY * -1;
        double horizontalShift = Math.pow(SECONDS_IN_HALF_DAY, 2);
        double verticalShift = SECONDS_IN_HALF_DAY;
        
        seconds = a * (xSquared + x + horizontalShift) + verticalShift;
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
     * Prints out the RGB colour values of the dynamic background.
     */
    protected void printColourValues() {
        System.out.println("\nRed: " + red);
        System.out.println("Green: " + green);
        System.out.println("Blue: " + blue);
    }
}

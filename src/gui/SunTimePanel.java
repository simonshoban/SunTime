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
    private static final int RED_MORNING = 0;
    private static final int GREEN_MORNING = 36;
    private static final int BLUE_MORNING = 72;
    
    private static final int RED_EVENING = 96;
    private static final int GREEN_EVENING = 180;
    private static final int BLUE_EVENING = 255;
    
    private static final int RED_MULTIPLIER = 8;
    private static final int GREEN_MULTIPLIER = 12;
    private static final int BLUE_MULTIPLIER = 15;
    
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_HALF_DAY = 43200;
    
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
        float seconds = LocalDateTime.now().toLocalTime().toSecondOfDay();
        float red = RED_MORNING;
        float green = GREEN_MORNING;
        float blue = BLUE_MORNING;
        int multiplier = 1;
        
        if (seconds >= SECONDS_IN_HALF_DAY) {
            multiplier = -1;
            red = RED_EVENING;
            green = GREEN_EVENING;
            blue = BLUE_EVENING;
            seconds -= SECONDS_IN_HALF_DAY;
        }
        
        red += multiplier * seconds / SECONDS_IN_HOUR * RED_MULTIPLIER;
        green += multiplier * seconds / SECONDS_IN_HOUR * GREEN_MULTIPLIER;
        blue += multiplier * seconds / SECONDS_IN_HOUR * BLUE_MULTIPLIER;
        
        backgroundColour = new Color((int) red, (int) green, (int) blue);
        
        setBackground(backgroundColour);
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

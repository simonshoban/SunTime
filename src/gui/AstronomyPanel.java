package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.AstronomyArrays;

/**
 * Displays astronomy information.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class AstronomyPanel extends SunTimePanel {
    private static final Font TEXT_FONT = new Font(
            "Helvetica", 
            Font.PLAIN, 
            20);
    
    private AstronomyArrays astronomyArrays;
    private JLabel sunTimes;
    
    /**
     * Constructs an AstronomyPanel.
     * 
     * @param astronomyArrays - The astronomy information to be displayed
     */
    public AstronomyPanel(AstronomyArrays astronomyArrays) {
        this.astronomyArrays = astronomyArrays;
        int day = LocalDateTime.now().getDayOfYear();

        sunTimes = new JLabel("<html>Sun rises at "
                + astronomyArrays.getSunriseTimes().get(day - 1)
                +  "<br/>Sun sets at "
                + astronomyArrays.getSunsetTimes().get(day - 1)
                + "</html>");
    }
    
    /**
     * Sets up the JLabels in this AstronomyPanel.
     */
    public void init() {
        setLabelFont();
        setLabelForeground();
        setLabelAlignment();
        addLabels();
    }
    
    /**
     * Sets the font of the labels.
     */
    private void setLabelFont() {
        sunTimes.setFont(TEXT_FONT);
    }
    
    /**
     * Sets the foreground of the labels.
     */
    private void setLabelForeground() {
        sunTimes.setForeground(Color.white);
    }
    
    /**
     * Sets the vertical and horizontal alignment of the labels.
     */
    private void setLabelAlignment() {
        sunTimes.setHorizontalAlignment(JLabel.CENTER);
        sunTimes.setVerticalAlignment(JLabel.CENTER);
    }
    
    /**
     * Adds the labels to this AstronomyPanel.
     */
    private void addLabels() {
        add(sunTimes, BorderLayout.CENTER);
    }
    
    /**
     * Changes the displayed Astronomy information depending on the given day.
     * 
     * @param day - The day to display astronomy information for
     */
    public void updateAstronomyInfo(int day) {
        sunTimes.setText("<html>Sun rises at "
                + astronomyArrays.getSunriseTimes().get(day - 1)
                +  "<br/>Sun sets at "
                + astronomyArrays.getSunsetTimes().get(day - 1)
                + "</html>");
    }
}

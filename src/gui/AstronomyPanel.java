package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import javax.swing.JLabel;

import data.AstronomyData;

/**
 * Displays astronomy information.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AstronomyPanel extends SunTimePanel {
    private static final Font TEXT_FONT = new Font(
            "Helvetica", 
            Font.PLAIN, 
            20);
    
    private AstronomyData astronomyData;
    private JLabel sunTimes;
    
    /**
     * Constructs an AstronomyPanel.
     * 
     * @param astronomyData - The astronomy information to be displayed
     */
    public AstronomyPanel(AstronomyData astronomyData) {
        this.astronomyData = astronomyData;
        int day = LocalDateTime.now().getDayOfYear();

        sunTimes = new JLabel("<html>Sun rises at "
                + astronomyData.getSunriseTimes().get(day - 1)
                +  "<br/>Sun sets at "
                + astronomyData.getSunsetTimes().get(day - 1)
                + "</html>");
    }
    
    /**
     * Updates the stored AstronomyData.
     * 
     * @param newData - The new AstronomyData
     */
    public void updateAstronomyData(AstronomyData newData) {
        astronomyData = newData;
        init();
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
        sunTimes.setFont(getTextFont());
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
                + astronomyData.getSunriseTimes().get(day - 1)
                +  "<br/>Sun sets at "
                + astronomyData.getSunsetTimes().get(day - 1)
                + "</html>");
        //System.out.println("stimes: " + astronomyData.getSunriseTimes().get(day-1));
    }

    /**
     * Gets the text font.
     * 
     * @return TEXT_FONT
     */
    public static Font getTextFont() {
        return TEXT_FONT;
    }
}

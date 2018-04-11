package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JLabel;

/**
 * Displays the day and month of the year.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TimePanel extends SunTimePanel {
    private static final int FONT_SIZE = 42;
    private JLabel time;
    private ZonedDateTime now;
    private ZoneId zoneID;
    
    /**
     * Constructs a TimePanel object.
     * 
     * @param zoneID - The timezone
     */
    public TimePanel(ZoneId zoneID) {
        this.zoneID = zoneID;
        now = ZonedDateTime.now(zoneID);
        constructElements();
    }
    
    /**
     * Constructs the elements of this panel.
     */
    private void constructElements() {
        String month = now.getMonth().getDisplayName(TextStyle.FULL, Locale.CANADA);
        int dayOfMonth = now.getDayOfMonth();
        
        time = new JLabel(month + " " + dayOfMonth);
    }
    
    /**
     * Initializes the TimePanel.
     */
    public void init() {
        time.setFont(new Font("Helvetica", Font.BOLD, FONT_SIZE));
        time.setForeground(Color.white);
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setVerticalAlignment(JLabel.CENTER);
        
        add(time, BorderLayout.NORTH);       
    }
    
    /**
     * Updates the displayed time information depending on the given day.
     * 
     * @param dayOfYear - The day of the year to display time information for
     */
    public void updateTime(int dayOfYear) {
        ZonedDateTime notNow = now.withDayOfYear(dayOfYear);
        String month = notNow.getMonth().getDisplayName(TextStyle.FULL, Locale.CANADA);
        int dayOfMonth = notNow.getDayOfMonth();
        
        time.setText(month + " " + dayOfMonth);
    }
    
    /**
     * Updates the time zone of this panel.
     * 
     * @param timeZone - The new time zone
     */
    public void updateTimeZone(ZoneId timeZone) {
        zoneID = timeZone;
    }
}

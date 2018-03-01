package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JLabel;

public class TimePanel extends SunTimePanel {
    private static final int FONT_SIZE = 42;
    private JLabel time;
    private Calendar calendar = Calendar.getInstance();
    
    /**
     * Constructs a TimePanel object.
     */
    public TimePanel() {
        time = new JLabel(
                calendar.getDisplayName(
                        Calendar.MONTH, Calendar.LONG, Locale.getDefault()
                        )
                + " " + calendar.get(Calendar.DAY_OF_MONTH)
                );
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
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        
        time.setText(
                calendar.getDisplayName(
                        Calendar.MONTH, Calendar.LONG, Locale.getDefault()
                        )
                + " " + calendar.get(Calendar.DAY_OF_MONTH)
                );
    }
}

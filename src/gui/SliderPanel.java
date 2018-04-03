package gui;

import java.awt.Dimension;
import java.time.LocalDateTime;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A JPanel that holds the date slider.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SliderPanel extends JPanel {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 365;
    private static final int SLIDER_WIDTH = 1800;
    private static final int SLIDER_HEIGHT = 80;
    private static final int MAJOR_TICK_SPACING = 30;
    private static final int MINOR_TICK_SPACING = 7;
    private JSlider slider;

    /**
     * Constructs a SliderPanel object.
     * 
     * @param mainContainer - The MainContainer that this SliderPanel belongs to
     */
    public SliderPanel(MainContainer mainContainer) {
        slider = new JSlider(
                FIRST_DAY, LAST_DAY, LocalDateTime.now().getDayOfYear()
                );
        slider.setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
        slider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(MINOR_TICK_SPACING);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                mainContainer.updateDailyInformation(slider.getValue());
            }
        });
        
        add(slider);
    }
    
    public int getDay() {
        return slider.getValue();
    }
}

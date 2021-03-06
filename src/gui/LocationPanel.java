package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import data.WebAddress;
import toolkit.Toolkit;

/**
 * A SunTimePanel that contains the text boxes to change location.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LocationPanel extends SunTimePanel {
    private MainContainer container;
    private JTextField city;
    private JTextField country;
    private JButton submit;
    
    /**
     * Constructs a LocationPanel.
     * 
     * @param container - The MainContainer that this SunTimePanel belongs to
     */
    public LocationPanel(MainContainer container) {
        this.container = container;
        city = new JTextField("Saint Peterburg");
        country = new JTextField("Russia");
        submit = new JButton("Submit");
    }
    
    /**
     * Initializes the LocationPanel.
     */
    public void init() {
        addMouseListenerToSubmitButton();
        addElements();
    }
    
    /**
     * Adds elements to the LocationPanel.
     */
    private void addElements() {
        add(city, BorderLayout.NORTH);
        add(country, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
    }
    
    /**
     * Adds a mouse listener to the submit button.
     */
    private void addMouseListenerToSubmitButton() {
        submit.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                WebAddress newLocation = new WebAddress(
                        Toolkit.DOMAIN, 
                        city.getText(),
                        country.getText()
                    );
                
                container.scrapeNewInfo(newLocation);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) { 
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });       
    }
    
    /**
     * Clears all text fields.
     */
    public void resetAllFields() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Clears a single text field.
     */
    public void resetField() {
        throw new UnsupportedOperationException();
    }
}

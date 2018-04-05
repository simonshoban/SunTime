package gui;

import java.awt.Dimension;
import javax.swing.JFrame;

import data.WebAddress;

/**
 * The application's window frame.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class WindowFrame extends JFrame {
    private int windowWidth;
    private int windowHeight;
    private WebAddress webAddress;
    
    /**
     * Constructs an WindowFrame object.
     * 
     * @param width - The width of the WindowFrame
     * @param height - The height of the WindowFrame
     * @param webAddress - The WebAddress object containing data for the title
     */
    public WindowFrame(int width, int height, WebAddress webAddress) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.webAddress = webAddress;
    }
    
    /**
     * Sets the title, default close operation, size, and visibility of the 
     * WindowFrame object.
     */
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(windowWidth, windowHeight));
        setTitle("Weather - " 
                + webAddress.getCapitalizedCity() + ", " 
                + webAddress.getCapitalizedCountry());
        setVisible(true);
    }
    
    /**
     * Updates the JFrame title based on the new location.
     * 
     * @param newLocation - The new location
     */
    public void updateTitle(WebAddress newLocation) {
        webAddress = newLocation;
        setTitle("Weather - " 
                + webAddress.getCapitalizedCity() + ", " 
                + webAddress.getCapitalizedCountry());
    }
}

package gui;

import java.awt.Dimension;
import javax.swing.JFrame;

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
    
    /**
     * Constructs an WindowFrame object.
     * 
     * @param windowWidth - The width of the WindowFrame
     * @param windowHeight - The height of the WindowFrame
     */
    public WindowFrame(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }
    
    /**
     * Sets the title, default close operation, size, and visibility of the 
     * WindowFrame object.
     */
    public void init() {
        setTitle("Weather");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(windowWidth, windowHeight));
        setVisible(true);
    }
}

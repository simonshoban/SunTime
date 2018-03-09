package gui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * The application's window frame.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class WindowFrame extends JFrame {
    /**
     * Constructs an WindowFrame object.
     * 
     * @param windowWidth - The width of the WindowFrame
     * @param windowHeight - The height of the WindowFrame
     */
    public WindowFrame(int windowWidth, int windowHeight) {
        setTitle("Weather");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(windowWidth, windowHeight));
        setVisible(true);
    }
    
    /**
     * Revalidates the WindowFrame every time something is added.
     * 
     * @param comp - The component to be added to the WindowFrame
     * @return - returns the same component for some reason
     */
    public Component add(Component comp) {
        super.add(comp);
        revalidate();
        
        return comp;
    }
}

package gui;

import java.awt.BorderLayout;
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
     * @param webAddress - The web address to scrape data from
     */
    public WindowFrame(int windowWidth, int windowHeight, String webAddress) {
        MainContainer container = new MainContainer(webAddress);
        
        container.init();
        setTitle("Weather");
        add(container);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(windowWidth, windowHeight));
        setVisible(true);
    }
}

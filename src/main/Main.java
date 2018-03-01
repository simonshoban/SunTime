package main;

import gui.WindowFrame;

/**
 * Drives the program.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public final class Main {
    private static String webAddress = "https://www.timeanddate.com/";
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;
    
    /**
     * Prevents the creation of Main objects.
     */
    private Main() {
    }

    /**
     * Point of entry.
     * 
     * @param args - unused
     */
    public static void main(String[] args) {      
        WindowFrame windowFrame = new WindowFrame(
                WINDOW_WIDTH, 
                WINDOW_HEIGHT, 
                webAddress
                );
    }
}

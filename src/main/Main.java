package main;

import toolkit.Toolkit;

import gui.WindowFrame;
import gui.MainContainer;

import data.WebAddress;

import processing.Parser;

/**
 * Drives the program.
 *
 * @author Simon Shoban
 * @version 1.0
 */
public final class Main {
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;
    private static String city = "vancouver";
    private static String country = "canada";

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
        WebAddress webAddress = new WebAddress(Toolkit.DOMAIN, city, country);
        Parser webParser = new Parser(webAddress);

        WindowFrame windowFrame = new WindowFrame(
                WINDOW_WIDTH,
                WINDOW_HEIGHT,
                webAddress
                );

        MainContainer container = new MainContainer(
                windowFrame,
                webParser
                );

        container.init();
        windowFrame.add(container);
        windowFrame.init();     
    }
}

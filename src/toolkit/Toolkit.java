package toolkit;

/**
 * A toolkit of useful stuff.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Toolkit {
    /**
     * The web domain that this app is built to scrape.
     */
    public static final String DOMAIN = "https://www.timeanddate.com/";
    private static final int MILLISECONDS_IN_SECONDS = 1000;
    
    /**
     * Capitalizes the string.
     * 
     * @param string - The string to be capitalized
     * @return a capitalized version of the given string
     */
    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() 
                + string.substring(1).toLowerCase();
    }
    
    /**
     * Creates a new thread to execute a runnable after a delay.
     * 
     * @param runnable - The thing to be executed
     * @param delay - The delay in milliseconds
     */
    public static void setTimeout(Runnable runnable, long delay) {
        System.out.println("difference: " + delay + "\n");
        
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }
    
    /**
     * Converts seconds to milliseconds.
     * 
     * @param seconds - The seconds to convert
     * @return seconds as milliseconds
     */
    public static long convertSecondsToMilliseconds(long seconds) {
        return seconds * MILLISECONDS_IN_SECONDS;
    }
    
    /**
     * Formats a given string into the correct UTF time zone code.
     * 
     * @param unformattedData - The unformatted time zone code
     * @return a UTF time zone code
     */
    public static String formatTimeZoneCode(String unformattedData) {
        throw new UnsupportedOperationException();
    }
}

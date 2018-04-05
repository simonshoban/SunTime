package toolkit;

/**
 * A toolkit of useful stuff.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Toolkit {
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
        System.out.println("difference: " + delay);
        
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
    
    public static long convertSecondsToMilliseconds(long seconds) {
        return seconds * MILLISECONDS_IN_SECONDS;
    }
}

package toolkit;

import java.util.TimerTask;
import java.util.Timer;

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
     * @return the thread that will execute the given runnable after the delay
     * @deprecated
     */
    public static Thread setTimeout(Runnable runnable, long delay) {
        System.out.println("difference: " + delay + "\n");
        
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        });
        
        thread.start();
        
        return thread;
    }
    
    /**
     * Creates a java.util.Timer and passes it a TimerTask to be executed after delay milliseconds.
     * 
     * @param task - The task you want to perform after a delay
     * @param delay - The number of milliseconds until you want the task to execute
     */
    public static void setTimeout(TimerTask task, long delay) {
        Timer timer = new java.util.Timer();
        
        timer.schedule(task, delay);
    }
    
    /**
     * Inserts a string into another string at the specified index.
     * 
     * @param string - The string to modify
     * @param c - The string to insert
     * @param index - The location to insert the new string into the old string
     * @return The old string containing the new string at the specified location
     */
    public static String insertIntoStringAt(String string, String c, int index) {
        return string.substring(0, index) + c + string.substring(index);
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
}

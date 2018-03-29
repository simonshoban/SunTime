package toolkit;

/**
 * A toolkit of useful stuff.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
public class Toolkit {
    public static String DOMAIN = "https://www.timeanddate.com/";
    
    /**
     * Capitalizes the string.
     * 
     * @param string - The string to be capitalized
     */
    public static String capitalize(String string) {
        return string = string.substring(0, 1).toUpperCase() 
                + string.substring(1).toLowerCase();
    }
}

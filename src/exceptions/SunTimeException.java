package exceptions;

/**
 * The parent class for all exceptions in SunTime.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class SunTimeException extends Exception {
    /**
     * Helper function that prints messages to stderr.
     * 
     * @param message - The message to print
     */
    protected void error(String message) {
        System.err.println(message);
    }
    
    /**
     * Prints error message.
     */
    public abstract void printErrorMessage();
}

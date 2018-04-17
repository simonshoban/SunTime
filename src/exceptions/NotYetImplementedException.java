package exceptions;

/**
 * An exception that is thrown when something tries to use a feature that is not yet implemented.
 * 
 * @author Simon Shoban
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NotYetImplementedException extends SunTimeException {
    /**
     * Constructs a NotYetImplementedException and prints it's error message.
     */
    protected NotYetImplementedException() {
        printErrorMessage();
    }

    @Override
    public void printErrorMessage() {
        error("This feature is not yet implemented");
        printStackTrace();
    }

}

package lebron;

/**
 * Exception class for handling errors specific to the Lebron application.
 * Used to signal invalid user input or command execution failures.
 */
public class LebronException extends Exception {
    /**
     * Constructs a new LebronException with the specified error message.
     *
     * @param message The detailed error message
     */
    public LebronException(String message) {
        super(message);
    }
}

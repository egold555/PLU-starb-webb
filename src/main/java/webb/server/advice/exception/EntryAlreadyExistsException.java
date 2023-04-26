package webb.server.advice.exception;

/**
 * Exception class to be thrown when attempting to create an entity that already exists.
 * Extends the RuntimeException class.
 */
public class EntryAlreadyExistsException extends RuntimeException {

    /**
     * Constructor that takes a message to describe the exception.
     * @param message A String representing the error message.
     */
    public EntryAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructor that takes a message and an underlying cause to describe the exception.
     * @param message A String representing the error message.
     * @param cause A Throwable representing the underlying cause of the exception.
     */
    public EntryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
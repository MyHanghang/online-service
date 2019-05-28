package online.util;

/**
 *
 *
 */
public class ProcessException extends RuntimeException {
    
	private static final long serialVersionUID = 4563065493581654405L;

	public ProcessException(String message) {
        super(message);
    }

    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
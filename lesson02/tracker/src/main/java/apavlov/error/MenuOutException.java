package apavlov.error;

/**
 * The class MenuOutException extends class RuntimeException.
 *
 * @author Pavlov Artem
 * @since 31.08.2017
 */
public class MenuOutException extends RuntimeException {

    /**
     * The constructor class`s MenuOutException.
     *
     * @param message - text error;
     */
    public MenuOutException(String message) {
        super(message);
    }
}

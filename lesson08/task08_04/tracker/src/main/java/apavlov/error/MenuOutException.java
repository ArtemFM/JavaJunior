package apavlov.error;

/**
 * The class MenuOutException extends class RuntimeException.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
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

package apavlov.errors;

/**
 * The class ImpossibleMoveException extends Exception.
 * Return error if way close for figure.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class ImpossibleMoveException extends Exception {

    /**
     * The constructor for class ImpossibleMoveException.
     *
     * @param message - text error;
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}

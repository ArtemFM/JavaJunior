package apavlov.errors;

/**
 * The class IncorrectColorFigureException extends Exception.
 * Return error if color is incorrect.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class IncorrectColorFigureException extends Exception {
    /**
     * The constructor for class IncorrectColorFigureException.
     *
     * @param message - text error;
     */
    public IncorrectColorFigureException(String message) {
        super(message);
    }
}

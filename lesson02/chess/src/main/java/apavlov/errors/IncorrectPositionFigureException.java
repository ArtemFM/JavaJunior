package apavlov.errors;

/**
 * The class OccupiedWayException extends Exception.
 * Return error if incorrect way for figure.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class IncorrectPositionFigureException extends Exception {
    /**
     * The constructor for class IncorrectPositionFigureException.
     *
     * @param message - text error;
     */
    public IncorrectPositionFigureException(String message) {
        super(message);
    }
}

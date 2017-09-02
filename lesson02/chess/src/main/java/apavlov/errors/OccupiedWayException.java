package apavlov.errors;

/**
 * The class OccupiedWayException extends Exception.
 * Return error if incorrect way for figure.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class OccupiedWayException extends Exception {

    /**
     * The constructor for class OccupiedWayException.
     *
     * @param message - text error;
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}

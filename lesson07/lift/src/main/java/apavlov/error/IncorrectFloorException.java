package apavlov.error;

/**
 * The class IncorrectFloorException - exception incorrect choice floor.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class IncorrectFloorException extends RuntimeException {
    /**
     * The constructor for class IncorrectFloorException.
     *
     * @param message - message error;
     */
    public IncorrectFloorException(String message) {
        super(message);
    }
}

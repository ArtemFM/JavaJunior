package apavlov.error;

/**
 * The class IncorrectAttributeException - exception incorrect attributes.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class IncorrectAttributeException extends RuntimeException {
    /**
     * The constructor for class IncorrectAttributeException.
     *
     * @param message - message error;
     */
    public IncorrectAttributeException(String message) {
        super(message);
    }
}

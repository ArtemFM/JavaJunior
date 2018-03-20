package apavlov.error;

/**
 * The class AttributeNotFoundException - throw, when attribute is not found.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class AttributeNotFoundException extends Exception {
    /**
     * The constructor for class AttributeNotFoundException.
     *
     * @param message - message error;
     */
    public AttributeNotFoundException(String message) {
        super(message);
    }
}

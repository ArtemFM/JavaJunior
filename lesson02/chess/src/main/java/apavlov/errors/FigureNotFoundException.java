package apavlov.errors;

/**
 * The class FigureNotFoundException extends Exception.
 * Return error if figure not fount on the board.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class FigureNotFoundException extends Exception {

    /**
     * The constructor for class FigureNotFoundException.
     *
     * @param message - text error;
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}

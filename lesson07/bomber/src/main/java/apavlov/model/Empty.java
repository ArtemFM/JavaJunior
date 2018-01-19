package apavlov.model;

/**
 * The class Empty - model empty cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Empty extends Model {
    /**
     * The constant - symbol for print ot console.
     */
    private static final char SYMBOL = 9617;

    /**
     * The constructor for class Empty.
     *
     * @param symbol - symbol for print to console;
     */
    private Empty(char symbol) {
        super(symbol, Status.EMPTY);
    }

    /**
     * The default constructor for class Empty.
     */
    public Empty() {
        this(SYMBOL);
    }
}

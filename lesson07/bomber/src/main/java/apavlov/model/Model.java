package apavlov.model;

/**
 * The abstract class Model - model for cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public abstract class Model {
    /**
     * The symbol for print to console.
     */
    private final char symbol;

    /**
     * The status for cell.
     */
    private final Status status;

    /**
     * The constructor for class Model.
     *
     * @param symbol - symbol for print;
     * @param status - status cell;
     */
    public Model(char symbol, Status status) {
        this.symbol = symbol;
        this.status = status;
    }

    @Override
    public String toString() {
        return Character.toString(this.symbol);
    }
}

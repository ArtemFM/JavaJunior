package apavlov.models;

/**
 * The class Cell for save position figure.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class Cell {

    /**
     * The var - position figure.
     */
    private String position;

    /**
     * The getter for var position.
     *
     * @return position figure;
     */
    public String getPosition() {
        return position;
    }

    /**
     * The constructor for class Cell.
     *
     * @param position - position figure on board;
     */
    public Cell(String position) {
        this.position = position.toUpperCase();
    }
}

package apavlov.models;

import apavlov.errors.ImpossibleMoveException;

/**
 * The abstract class Figure.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public abstract class Figure {
    /**
     * The var for save color figure.
     */
    private String color;

    /**
     * The var for save position figure.
     */
    private Cell cell;

    /**
     * The getter for var color.
     *
     * @return - color figure;
     */
    public String getColor() {
        return color;
    }

    /**
     * The getter for var cell.
     *
     * @return - position figure;
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * The constructor for abstract class Figure.
     *
     * @param cell  - position figure
     * @param color - color figure;            ;
     */
    public Figure(Cell cell, String color) {
        this.cell = cell;
        this.color = color;
    }

    /**
     * The method get array all way figure to board.
     *
     * @param dist - position finish for figure;
     * @return array all cells on the way;
     * @throws ImpossibleMoveException - bad way for figure;
     */
    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * The method clone figure to new position.
     *
     * @param dist - new position;
     * @return - copy figure with edit position;
     */
    public abstract Figure clone(Cell dist);
}

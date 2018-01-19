package apavlov.model.element.unit.monster;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Status;
import apavlov.model.element.unit.Unit;

/**
 * The class Onion - model unit`s monster for write to cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Onion extends Unit {
    /**
     * The constant - symbol for print to console.
     */
    private static final char SYMBOL = 9412;

    /**
     * The constant - default speed unit (amount move by cell to one second).
     */
    private static final int HEALTH = 1;

    /**
     * The constant - default amount health element.
     */
    private static final int SPEED = 1;

    /**
     * The constructor for class Onion.
     *
     * @param position - position unit;
     * @param board - game board;
     */
    public Onion(Position position, Cell[][] board) {
        super(SYMBOL, Status.MONSTER, board, position, HEALTH, SPEED, false);
    }
}

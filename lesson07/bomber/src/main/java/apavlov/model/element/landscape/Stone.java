package apavlov.model.element.landscape;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Status;
import apavlov.model.element.Element;

/**
 * The class Stone - element landscape game.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Stone extends Element {
    /**
     * The constant - symbol for print to console.
     */
    private static final char SYMBOL = 9619;

    /**
     * The constant - amount health element.
     */
    private static final int HEALTH = 0;

    /**
     * The constructor for class Stone.
     *
     * @param board - board game;
     * @param position - position element to board;
     */
    public Stone(Cell[][] board, Position position) {
        super(SYMBOL, Status.LANDSCAPE, board, position, HEALTH);
    }

    @Override
    public boolean isDead() {
        return false;
    }
}

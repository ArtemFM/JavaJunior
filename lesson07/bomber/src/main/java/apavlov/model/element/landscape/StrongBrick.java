package apavlov.model.element.landscape;

import apavlov.board.Cell;
import apavlov.board.Position;

/**
 * The class StrongBrick - element landscape game.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
class StrongBrick extends Brick {
    /**
     * The constant - symbol for print to console.
     */
    private static final char SYMBOL = 9618;

    /**
     * The constant - amount health element.
     */
    private static final int HEALTH = 2;

    /**
     * The constructor for class StrongBrick.
     *
     * @param board - board game;
     * @param position - position element to board;
     */
    StrongBrick(Cell[][] board, Position position) {
        super(board, position, SYMBOL, HEALTH);
    }
}

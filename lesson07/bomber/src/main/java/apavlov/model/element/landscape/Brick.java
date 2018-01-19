package apavlov.model.element.landscape;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Status;
import apavlov.model.element.Element;

/**
 * The class Brick - element landscape game.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Brick extends Element {
    /**
     * The constant - symbol for print to console.
     */
    private static final char SYMBOL = 9618;

    /**
     * The constant - amount health element.
     */
    private static final int HEALTH = 1;

    /**
     * The constructor for class Brick.
     *
     * @param board - board game;
     * @param position - position element to board;
     * @param symbol - symbol for print;
     * @param health - amount health element;
     */
    Brick(Cell[][] board, Position position, char symbol, int health) {
        super(symbol, Status.LANDSCAPE, board, position, health);
    }

    /**
     * The constructor for class Brick.
     *
     * @param board - board game;
     * @param position - position element to board;
     */
    Brick(Cell[][] board, Position position) {
        this(board, position, SYMBOL, HEALTH);
    }

    @Override
    public void explosionBomb() {
        int health = super.getHealth();
        if (health > 0) {
            super.setHealth(--health);
        }
    }

    @Override
    public boolean isDead() {
        return super.getHealth() <= 0;
    }
}

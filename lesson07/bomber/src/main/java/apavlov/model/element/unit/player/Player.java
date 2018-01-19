package apavlov.model.element.unit.player;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Status;
import apavlov.model.element.unit.Unit;

/**
 * The class Player - model unit`s player for write to cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Player extends Unit {
    /**
     * The constant - symbol for print to console.
     */
    private static final char SYMBOL = 9399;

    /**
     * The constant - default speed unit (amount move by cell to one second).
     */
    private static final int SPEED = 1;

    /**
     * The constant - default amount health element.
     */
    private static final int HEALTH = 1;

    /**
     * The constructor for class Player.
     *
     * @param position - position unit;
     * @param board - game board;
     */
    public Player(Position position, Cell[][] board) {
        super(SYMBOL, Status.PLAYER, board, position, HEALTH, SPEED, true);
    }

    @Override
    public void putAttack() {
        int health = super.getHealth();
        if (health > 0) {
            super.setHealth(--health);
        }
    }
}

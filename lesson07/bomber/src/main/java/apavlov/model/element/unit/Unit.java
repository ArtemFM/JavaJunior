package apavlov.model.element.unit;

import apavlov.api.Move;
import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Status;
import apavlov.model.element.Element;
import apavlov.model.element.bomb.Bomb;
import apavlov.threads.ThreadBomb;

/**
 * The class Unit - model unit`s for write to cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Unit extends Element {
    /**
     * The speed unit - amount move by cell to one second.
     */
    private final int speed;

    /**
     * The unit can ot not can set (put) bomb.
     */
    private final boolean isBomb;

    /**
     * The getter for field speed.
     *
     * @return speed unit;
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * The constructor for class Unit.
     *
     * @param symbol - symbol for print to console;
     * @param status - status cell;
     * @param board - game board;
     * @param position - position unit;
     * @param health - amount health unit;
     * @param speed - speed unit;
     * @param isBomb - can or not can set (put) bomb;
     */
    public Unit(char symbol, Status status, Cell[][] board, Position position, int health, int speed, boolean isBomb) {
        super(symbol, status, board, position, health);
        this.speed = speed;
        this.isBomb = isBomb;
    }

    /**
     * The method put (set) bomb in this position unit.
     */
    public void putBomb() {
        if (this.isBomb) {
            Cell cell = getCell(super.getPosition());
            if (cell != null) {
                Bomb bomb = new Bomb(super.getPosition(), super.getBoard());
                if (cell.putElement(bomb)) {
                    new ThreadBomb(bomb).start();
                }
            }
        }
    }

    /**
     * The method check possibility of the movement.
     *
     * @return true - is possibility or false;
     */
    private boolean isMove() {
        return !isDead() && this.speed > 0;
    }

    /**
     * The method return next position for move unit.
     *
     * @param move - courses move;
     * @return null, if next position incorrect or next position;
     */
    private Position getNextPosition(Move move) {
        Position next = null;
        if (move != null && isMove() && super.getPosition() != null) {
            Position temp = move.nextPosition(super.getPosition());
            if (checkPosition(temp)) {
                next = temp;
            }
        }
        return next;
    }

    /**
     * The method for manual control unit.
     *
     * @param move - courses for move;
     * @return false - is not move or true;
     */
    public boolean move(Move move) {
        boolean result;
        Position next = getNextPosition(move);
        if (result = next != null) {
            Cell nowCell = getCell(super.getPosition());
            Cell nextCell = getCell(next);
            if (result = nowCell != null && nextCell != null) {
                if (result = nextCell.isEmpty()) {
                    nowCell.removeUnit();
                    super.setPosition(next);
                    nextCell.putUnit(this);
                } else {
                    nextCell.putAttack();
                }
            }
        }
        return result;
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

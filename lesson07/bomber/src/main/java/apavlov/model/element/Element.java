package apavlov.model.element;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Model;
import apavlov.model.Status;

/**
 * The abstract class Element - model element`s for write to cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public abstract class Element extends Model {
    /**
     * The deep array cells (game board).
     */
    private final Cell[][] board;

    /**
     * Position element.
     */
    private Position position;

    /**
     * Amount health to element.
     */
    private int health;

    /**
     * The getter for field board.
     *
     * @return field board;
     */
    public Cell[][] getBoard() {
        return this.board;
    }

    /**
     * The getter for field health.
     *
     * @return field health;
     */
    protected int getHealth() {
        return health;
    }

    /**
     * The getter for field position.
     *
     * @return field position;
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * The setter for field health.
     *
     * @param health - amount health element;
     */
    protected void setHealth(int health) {
        this.health = health;
    }

    /**
     * The setter for field position.
     *
     * @param position - position element;
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * The constructor for class Element.
     *
     * @param symbol   - symbol for print to console;
     * @param status   - status cell;
     * @param board    - game board;
     * @param position - position element to board;
     * @param health   - amount health element;
     */
    public Element(char symbol, Status status, Cell[][] board, Position position, int health) {
        super(symbol, status);
        this.board = board;
        this.health = health;
        this.position = position;
    }

    /**
     * The method check correct position to board.
     *
     * @param position - position for check;
     * @return true - is correct or false;
     */
    public boolean checkPosition(Position position) {
        boolean result;
        if (result = position != null) {
            if (result = position.getY() >= 0 && position.getY() < this.board.length) {
                result = position.getX() >= 0 && position.getX() < this.board[position.getY()].length;
            }
        }
        return result;
    }

    /**
     * The method return cell by position.
     *
     * @param position - position;
     * @return null, if position incorrect or return cell;
     */
    public Cell getCell(Position position) {
        Cell result = null;
        if (position != null && checkPosition(position)) {
            result = this.board[position.getY()][position.getX()];
        }
        return result;
    }

    /**
     * The method realizes action at the explosion bomb.
     */
    public void explosionBomb() {

    }

    /**
     * The method realizes action at the attack.
     */
    public void putAttack() {

    }

    /**
     * The method check is dead element or is not.
     *
     * @return true - if element is dead or false;
     */
    public boolean isDead() {
        return false;
    }
}

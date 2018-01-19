package apavlov.model.element.bomb;

import apavlov.api.Move;
import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.Status;
import apavlov.model.element.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Bomb - model bomb.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Bomb extends Element {
    /**
     * The constant - symbol for print to console.
     */
    private static final char SYMBOL = 9852;

    /**
     * The constant - default second before explosion.
     */
    private static final int DEFAULT_SECOND = 3;

    /**
     * The constant - default length in cell explosion.
     */
    private static final int DEFAULT_LENGTH = 5;

    /**
     * The amount second delay before explosion.
     */
    private final int delaySeconds;

    /**
     * The length explosion in to cells.
     */
    private final int lengthExplosion;

    /**
     * true - if bomb is activation.
     */
    private boolean deactivation;

    /**
     * The getter for field delaySeconds.
     *
     * @return field delaySeconds;
     */
    public int getDelaySeconds() {
        return delaySeconds;
    }

    /**
     * The constructor for class Bomb.
     *
     * @param position - position bomb;
     * @param delaySeconds - delay seconds before explosion;
     * @param lengthExplosion - length explosion in to cells;
     * @param board - game board;
     */
    private Bomb(Position position, int delaySeconds, int lengthExplosion, Cell[][] board) {
        super(SYMBOL, Status.BOMB, board, position, 0);
        this.delaySeconds = delaySeconds > 0 ? delaySeconds : DEFAULT_SECOND;
        this.lengthExplosion = lengthExplosion > 0 ? lengthExplosion : DEFAULT_LENGTH;
    }

    /**
     * The constructor for class Bomb.
     *
     * @param position - position bomb;
     * @param board - game board;
     */
    public Bomb(Position position, Cell[][] board) {
        this(position, DEFAULT_SECOND, DEFAULT_LENGTH, board);
    }

    /**
     * The method return list cells, where was explosion.
     *
     * @return list cells;
     */
    private List<Cell> getLengthExplosion() {
        List<Cell> resultList = new ArrayList<>();
        if (checkPosition(super.getPosition())) {
            resultList.add(super.getCell(super.getPosition()));
            for (Move move : Move.values()) {
                resultList.addAll(this.getLine(move));
            }
        }
        return resultList;
    }

    /**
     * The method return list cells alone leaves explosion.
     *
     * @param move - way explosion;
     * @return list cell explosion;
     */
    private List<Cell> getLine(Move move) {
        List<Cell> list = new ArrayList<>();
        if (move != null && checkPosition(super.getPosition())) {
            int amountCell = this.lengthExplosion;
            Position next = super.getPosition();
            while (amountCell != 0) {
                next = move.nextPosition(next);
                if (checkPosition(next)) {
                    Cell cell = super.getCell(next);
                    if (!cell.isEmpty()) {
                        list.add(cell);
                    }
                } else {
                    break;
                }
                amountCell--;
            }
        }
        return list;
    }

    @Override
    public void explosionBomb() {
        if (!this.deactivation) {
            this.deactivation = true;
            List<Cell> listExplosion = getLengthExplosion();
            for (Cell cell : listExplosion) {
                if (cell != null) {
                    cell.explosionBomb();
                }
            }
        }
    }

    @Override
    public boolean isDead() {
        return this.deactivation;
    }
}

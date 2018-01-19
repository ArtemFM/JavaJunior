package apavlov.ai;

import apavlov.api.Move;
import apavlov.board.Cell;
import apavlov.model.element.unit.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class AI - for intellectual choice way for unit.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class AI {
    /**
     * constant: 30% chance choice new way for unit.
     */
    private static final double CHANCE_EDIT_WAY = 0.3;

    /**
     * The link class Unit.
     */
    private final Unit unit;

    /**
     * The link class Random for generate random numbers.
     */
    private final Random random;

    /**
     * The constructor for class AI.
     *
     * @param unit - unit;
     */
    public AI(Unit unit) {
        this.unit = unit;
        this.random = new Random();
    }

    /**
     * The method check possibility of the course.
     *
     * @param move - move for unit;
     * @return false - is not possibility or true;
     */
    private boolean checkMove(Move move) {
        boolean result = move != null && this.unit != null;
        if (result) {
            Cell cell = this.unit.getCell(move.nextPosition(this.unit.getPosition()));
            result = cell != null && !cell.isElement();
        }
        return result;
    }

    /**
     * The method return list possibility of the courses.
     *
     * @return list courses;
     */
    private List<Move> getListMoves() {
        List<Move> result = new ArrayList<>();
        if (this.unit != null) {
            for (Move move : Move.values()) {
                if (checkMove(move)) {
                    result.add(move);
                }
            }
        }
        return result;
    }

    /**
     * The method choice course for unit by algorithm.
     *
     * @param move - previous courses;
     * @return next courses;
     */
    public Move getNextMove(Move move) {
        Move result = null;
        if (this.unit != null && this.unit.checkPosition(this.unit.getPosition())) {
            if (move != null) {
                List<Move> listMoves = getListMoves();
                if (!listMoves.isEmpty()) {
                    int chance = this.random.nextInt((int) (1 / CHANCE_EDIT_WAY));
                    if (chance != 0) {
                        result = listMoves.contains(move) ? move : listMoves.get(this.random.nextInt(listMoves.size()));
                    } else {
                        if (listMoves.contains(move) && listMoves.size() > 1) {
                            listMoves.remove(move);
                        }
                        result = listMoves.get(this.random.nextInt(listMoves.size()));
                    }
                }
            } else {
                result = Move.values()[this.random.nextInt(Move.values().length)];
            }
        }
        return result;
    }
}

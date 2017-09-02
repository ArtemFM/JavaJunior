package apavlov.models;

import apavlov.errors.ImpossibleMoveException;

import java.util.Arrays;

/**
 * The class Elephant extend class Figure.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class Elephant extends Figure {

    /**
     * The constructor for class Elephant.
     *
     * @param cell  - position figure to board;
     * @param color - color figure;
     */
    public Elephant(Cell cell, String color) {
        super(cell, color);
    }

    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] cells = new Cell[0];
        char[] posXYStart = getCell().getPosition().toCharArray();
        char[] posXYEnd = dist.getPosition().toCharArray();
        int stepX = posXYStart[0] - posXYEnd[0];
        int stepY = posXYStart[1] - posXYEnd[1];
        int step = Math.abs(stepX) == Math.abs(stepY) ? Math.abs(stepX) : 0;
        if (step != 0) {
            cells = Arrays.copyOf(cells, step);
            char posXNow = posXYStart[0];
            char posYNow = posXYStart[1];
            for (int i = 0; i < step; i++) {
                posXNow = stepX < 0 ? ++posXNow : --posXNow;
                posYNow = stepY < 0 ? ++posYNow : --posYNow;
                cells[i] = new Cell(String.format("%s%s", posXNow, posYNow));
            }
        } else {
            throw new ImpossibleMoveException("Figure is not come this cell!");
        }
        return cells;
    }

    @Override
    public Figure clone(Cell dist) {
        return new Elephant(dist, getColor());
    }
}

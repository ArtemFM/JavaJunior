package apavlov.model.element.landscape;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.element.Element;

/**
 * The enum MakeLandscape - for creating new elements landscape.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public enum MakeLandscape {
    /**
     * The field for creating element Stone.
     */
    STONE {
        @Override
        public Element createElement(Position position, Cell[][] board) {
            return new Stone(board, position);
        }
    },
    /**
     * The field for creating element Brick.
     */
    BRICK {
        @Override
        public Element createElement(Position position, Cell[][] board) {
            return new Brick(board, position);
        }
    },
    /**
     * The field for creating element StrongBrick.
     */
    STRONG_BRICK {
        @Override
        public Element createElement(Position position, Cell[][] board) {
            return new StrongBrick(board, position);
        }
    };

    /**
     * The method create new element landscape.
     *
     * @param position - position for element;
     * @param board - game board;
     * @return new element landscape;
     */
    public abstract Element createElement(Position position, Cell[][] board);
}

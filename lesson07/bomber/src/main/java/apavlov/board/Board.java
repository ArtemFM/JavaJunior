package apavlov.board;

import apavlov.model.element.Element;
import apavlov.model.element.landscape.Stone;
import apavlov.model.element.unit.Unit;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Board - model game board.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Board {
    /**
     * The constant - default width game board.
     */
    private static final int DEFAULT_WIDTH = 20;

    /**
     * The constant - default height game board.
     */
    private static final int DEFAULT_HEIGHT = 15;

    /**
     * The constant - default min length (width, height) game board.
     */
    private static final int MIN_LENGTH = 5;

    /**
     * The link object work with string.
     */
    private final StringBuilder sb;

    /**
     * The array - game board.
     */
    private final Cell[][] board;

    /**
     * The getter for field board.
     *
     * @return link array game board;
     */
    public Cell[][] getBoard() {
        return this.board;
    }

    /**
     * The constructor for class Board.
     *
     * @param width  - width game board;
     * @param height - height game board;
     */
    private Board(int width, int height) {
        this.board = new Cell[getCorrectHeight(height)][getCorrectWidth(width)];
        this.sb = new StringBuilder();
        createBoard();
    }

    /**
     * The default constructor for class Board.
     */
    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * The method return correct value width.
     *
     * @param width - width for check;
     * @return correct width;
     */
    private int getCorrectWidth(int width) {
        if (width < MIN_LENGTH) {
            width = DEFAULT_WIDTH;
        } else if (width % 2 == 0) {
            width++;
        }
        return width;
    }

    /**
     * The method return correct value height.
     *
     * @param height - height for check;
     * @return correct height;
     */
    private int getCorrectHeight(int height) {
        if (height < MIN_LENGTH) {
            height = DEFAULT_HEIGHT;
        } else if (height % 2 == 0) {
            height++;
        }
        return height;
    }

    /**
     * The method create start elements to game board.
     */
    private void createBoard() {
        for (int height = 0; height < this.board.length; height++) {
            for (int width = 0; width < this.board[height].length; width++) {
                if (height == 0 || height == this.board.length - 1 || width == 0 || width == this.board[height].length - 1) {
                    this.board[height][width] = new Cell(new Stone(this.board, new Position(width, height)));
                } else if (width % 2 == 0 && height % 2 == 0) {
                    this.board[height][width] = new Cell(new Stone(this.board, new Position(width, height)));
                } else {
                    this.board[height][width] = new Cell();
                }
            }
        }
    }

    /**
     * The method add new unit to game board.
     *
     * @param unit - unit for addition;
     * @return true, if is add or false;
     */
    public boolean addUnit(Unit unit) {
        boolean result = unit != null;
        if (result) {
            Cell cell = unit.getCell(unit.getPosition());
            if (result = cell != null) {
                result = cell.putUnit(unit);
            }
        }
        return result;
    }

    /**
     * The method add new element to game board.
     *
     * @param element - element for addition;
     * @return true, if is add or false;
     */
    public boolean addElement(Element element) {
        boolean result = element != null;
        if (result) {
            Cell cell = element.getCell(element.getPosition());
            if (result = cell != null) {
                result = cell.putElement(element);
            }
        }
        return result;
    }

    /**
     * The method return list position empty cells.
     *
     * @return list position empty cells;
     */
    public List<Position> getListEmptyCell() {
        List<Position> resultList = new ArrayList<>();
        for (int height = 0; height < this.board.length; height++) {
            for (int width = 0; width < this.board[height].length; width++) {
                if (this.board[height][width].isEmpty()) {
                    resultList.add(new Position(width, height));
                }
            }
        }
        return resultList;
    }

    @Override
    public String toString() {
        sb.delete(0, sb.length());
        for (Cell[] lineX : this.board) {
            for (Cell cell : lineX) {
                sb.append(cell);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}

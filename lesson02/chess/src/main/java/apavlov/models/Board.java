package apavlov.models;


import apavlov.errors.IncorrectPositionFigureException;
import apavlov.errors.IncorrectColorFigureException;
import apavlov.errors.ImpossibleMoveException;
import apavlov.errors.OccupiedWayException;
import apavlov.errors.FigureNotFoundException;

import java.util.Arrays;

/**
 * The class Board for game chess.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class Board {

    /**
     * The constant lenght numeric line x.
     */
    private static final int LENGHT_X = 8;

    /**
     * The constant lenght numeric line y.
     */
    private static final int LENGHT_Y = 8;

    /**
     * The array figures to board.
     */
    private Figure[] board = new Figure[LENGHT_Y * LENGHT_X];

    /**
     * The array kill figures on the board.
     */
    private Figure[] killFigure = new Figure[0];

    /**
     * The method get array kill figures.
     *
     * @return - array kill figures;
     */
    public Figure[] getKillFigure() {
        return Arrays.copyOf(killFigure, killFigure.length);
    }

    /**
     * The method return all figures on the board.
     *
     * @return array figures;
     */
    public Figure[] getFiguresToBoard() {
        Figure[] figures = new Figure[board.length];
        int count = 0;
        for (Figure figure : board) {
            if (figure != null) {
                figures[count++] = figure;
            }
        }
        return Arrays.copyOf(figures, count);
    }

    /**
     * The method add figure to board.
     *
     * @param figure - link object Figure;
     * @throws IncorrectPositionFigureException - error, if incorrect position figure`s;
     * @throws IncorrectColorFigureException    - error, if incorrect color figure;
     */
    public void addFigure(Figure figure) throws IncorrectPositionFigureException, IncorrectColorFigureException {
        if (checkPosition(figure.getCell())) {
            if (figure.getColor().equals("white") || figure.getColor().equals("black")) {
                int posBoard = getPositionToBoard(figure.getCell());
                board[posBoard] = figure;
            } else {
                throw new IncorrectColorFigureException("Color figure is incorrect!");
            }
        } else {
            throw new IncorrectPositionFigureException("Position figure is incorrect!");
        }
    }

    /**
     * The method get position figure to board.
     *
     * @param cell - position figure;
     * @return position figure to board;
     */
    private int getPositionToBoard(Cell cell) {
        char[] posXY = cell.getPosition().toCharArray();
        return ((int) posXY[0] - 65) + (Character.digit(--posXY[1], 10) * LENGHT_Y);
    }

    /**
     * The method check correct position figure.
     *
     * @param cell - position figure;
     * @return result check;
     */
    private boolean checkPosition(Cell cell) {
        boolean result = false;
        String position = cell.getPosition();
        if (position.length() == 2) {
            char[] posXY = position.toCharArray();
            int x = ((int) posXY[0]) - 64;
            int posX = (x >= 1 && x <= LENGHT_X) ? x : -1;
            int posY = (posXY[1] >= '1' && posXY[1] <= Character.forDigit(LENGHT_Y, 10)) ? Character.getNumericValue(posXY[1]) : -1;
            result = posX != -1 && posY != -1;
        }
        return result;
    }

    /**
     * The method move figure with sorce position to dist position.
     *
     * @param source - start position;
     * @param dist   - end position;
     * @return - result correct or incorrect;
     * @throws ImpossibleMoveException - bad end position;
     * @throws OccupiedWayException    - way is close other figure;
     * @throws FigureNotFoundException - figure not found;
     * @throws IncorrectPositionFigureException - incorrect position figure;
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException, IncorrectPositionFigureException {
        boolean result = false;
        if (checkPosition(source) && checkPosition(dist)) {
            if (source.getPosition() != dist.getPosition()) {
                int posSource = getPositionToBoard(source);
                if (board[posSource] != null) {
                    Figure figure = board[posSource];
                    Cell[] way = figure.way(dist);
                    for (int i = 0; i < way.length - 1; i++) {
                        if (board[getPositionToBoard(way[i])] != null) {
                            throw new OccupiedWayException("The way is close for figure!");
                        }
                    }
                    int endPos = getPositionToBoard(way[way.length - 1]);
                    if (board[endPos] != null) {
                        if (!board[endPos].getColor().equals(figure.getColor())) {
                            killFigure = Arrays.copyOf(killFigure, killFigure.length + 1);
                            killFigure[killFigure.length - 1] = board[endPos];
                        } else {
                            throw new OccupiedWayException("The way is close for figure!");
                        }
                    }
                    board[endPos] = figure.clone(dist);
                    board[getPositionToBoard(source)] = null;
                    result = true;

                } else {
                    throw new FigureNotFoundException("Figure is not found!");
                }
            }
        } else {
            throw new IncorrectPositionFigureException("Position figure is incorrect!");
        }
        return result;
    }
}


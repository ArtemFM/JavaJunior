package apavlov;

import apavlov.errors.IncorrectPositionFigureException;
import apavlov.errors.IncorrectColorFigureException;
import apavlov.errors.ImpossibleMoveException;
import apavlov.errors.OccupiedWayException;
import apavlov.errors.FigureNotFoundException;
import apavlov.models.Board;
import apavlov.models.Cell;
import apavlov.models.Elephant;
import apavlov.models.Figure;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for classes Board.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class BoardTest {
    /**
     * The test method check add new figure.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenAddFigure() throws Exception {
        Board board = new Board();
        board.addFigure(new Elephant(new Cell("b3"), "white"));
        assertThat("b3".toUpperCase(), is(board.getFiguresToBoard()[0].getCell().getPosition()));
    }

    /**
     * The test method check exception error position.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = IncorrectPositionFigureException.class)
    public void whenAddFigureAndPositionIncorrect() throws Exception {
        new Board().addFigure(new Elephant(new Cell("c10"), "white"));
    }

    /**
     * The test method check exception error color.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = IncorrectColorFigureException.class)
    public void whenAddFigureAndColorIncorrect() throws Exception {
        new Board().addFigure(new Elephant(new Cell("c1"), "green"));
    }

    /**
     * The test method check when two position is equals.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenMovePositionsEquals() throws Exception {
        boolean result = false;
        Board board = new Board();
        Cell position = new Cell("a1");
        board.addFigure(new Elephant(position, "white"));
        assertThat(result, is(board.move(position, position)));
    }

    /**
     * The test method check when incorrect position.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = IncorrectPositionFigureException.class)
    public void whenMoveIsNotIncorrectPosition() throws Exception {
        Board board = new Board();
        Cell position = new Cell("a1");
        board.addFigure(new Elephant(position, "white"));
        board.move(position, new Cell("i2"));
    }

    /**
     * The test method check when bad possition for move.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveIsImprossible() throws Exception {
        Board board = new Board();
        Cell position = new Cell("a1");
        board.addFigure(new Elephant(position, "white"));
        board.move(position, new Cell("e2"));
    }

    /**
     * The test method check when way is busy other figure.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = OccupiedWayException.class)
    public void whenWayIsBusy() throws Exception {
        Board board = new Board();
        Figure elephantOne = new Elephant(new Cell("a1"), "white");
        Figure elephantTwo = new Elephant(new Cell("c3"), "white");
        board.addFigure(elephantOne);
        board.addFigure(elephantTwo);
        board.move(elephantOne.getCell(), elephantTwo.getCell());
    }

    /**
     * The test method check when figure is not found to board.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenFigureIsNotFound() throws Exception {
        new Board().move(new Cell("a1"), new Cell("c3"));
    }

    /**
     * The test method check when move is good.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenMoveIsGood() throws Exception {
        boolean result = true;
        Board board = new Board();
        Figure elephant = new Elephant(new Cell("a1"), "white");
        board.addFigure(elephant);
        assertThat(result, is(board.move(elephant.getCell(), new Cell("c3"))));
    }

    /**
     * The test method check when move is good and kill enemy figure.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenMoveIsGoodAndKillEnemyFigure() throws Exception {
        boolean result = true;
        Board board = new Board();
        Cell endPos = new Cell("c3");
        Figure elephant = new Elephant(new Cell("a1"), "white");
        Figure elephantEnemy = new Elephant(endPos, "black");
        board.addFigure(elephant);
        board.addFigure(elephantEnemy);
        assertThat(2, is(board.getFiguresToBoard().length));
        assertThat(result, is(board.move(elephant.getCell(), endPos)));
        assertThat(1, is(board.getKillFigure().length));
        assertThat(1, is(board.getFiguresToBoard().length));
    }
}

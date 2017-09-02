package apavlov;

import apavlov.errors.ImpossibleMoveException;
import apavlov.models.Cell;
import apavlov.models.Elephant;
import apavlov.models.Figure;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for classes Elephant.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public class ElephantTest {
    /**
     * The test method for check return good way figure.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenWayIsGood() throws Exception {
        Cell[] resultWay = {new Cell("b2"), new Cell("c3"), new Cell("d4")};
        Figure elephant = new Elephant(new Cell("a1"), "white");
        Cell[] figureWay = elephant.way(new Cell("d4"));
        for (int i = 0; i < figureWay.length; i++) {
            assertThat(resultWay[i].getPosition(), is(figureWay[i].getPosition()));
        }
    }

    /**
     * The test method for check return exception bad way.
     *
     * @throws Exception - check any error;
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenWayIsBad() throws Exception {
        Figure elephant = new Elephant(new Cell("a1"), "white");
        elephant.way(new Cell("e4"));
    }
}

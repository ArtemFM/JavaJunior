package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class TurnArray.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class TurnArrayTest {

    /**
     * The test check turn double array to right.
     */
    @Test
    public void whenTurnToRight() {
        TurnArray turnArray = new TurnArray();
        int[][] startArray = {
                {-3, -2, -1},
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };
        int[][] cheked = {
                {6, 3, 0, -3},
                {7, 4, 1, -2},
                {8, 5, 2, -1}
        };

        int[][] result = turnArray.turnToRight(startArray);

        assertThat(cheked, is(result));
    }

    /**
     * The test check turn double array to left.
     */
    @Test
    public void whenTurnToLeft() {
        TurnArray turnArray = new TurnArray();
        int[][] startArray = {
                {-3, -2, -1},
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };
        int[][] cheked = {
                {-1, 2, 5, 8},
                {-2, 1, 4, 7},
                {-3, 0, 3, 6}
        };

        int[][] result = turnArray.turnToLeft(startArray);

        assertThat(cheked, is(result));
    }
}
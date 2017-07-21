package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class MaxSides.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class MaxSidesTest {

    /**
     * The test for check max side shape.
     */
    @Test
    public void whenMaxSideFigure() {
        Triangle figure = new Triangle(new Point(1, 2), new Point(4, 4), new Point(5, 5));
        double cheked = 5D;

        double result = new MaxSides().max(figure);

        assertThat(result, is(cheked));
    }
}
package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxSidesTest {

    @Test
    public void whenMaxSideFigure() throws Exception {
        Figure figure = new Triangle(new Point(1, 2), new Point(4, 4), new Point(5, 5));
        double cheked = 5D;

        double result = new MaxSides().max(figure);

        assertThat(result, is(cheked));
    }
}
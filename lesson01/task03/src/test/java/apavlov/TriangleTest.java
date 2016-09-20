package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenArea() throws Exception {
        Figure figure = new Triangle(new Point(0, 1), new Point(2, 4), new Point(5, 1));
        String cheked = "7,5";

        String result = String.format("%.1f", figure.area());

        assertThat(result, is(cheked));
    }

    @Test
    public void whenNotArea() throws Exception {
        Figure figure = new Triangle(new Point(1, 2), new Point(2, 3), new Point(3, 4));
        String message = "The area triangle can't be calculated";
        try {
            figure.area();
        } catch (IllegalArgumentException e) {
            assertThat(message, is(e.getMessage()));
        }
    }
}
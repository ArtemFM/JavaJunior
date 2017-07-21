package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Triangle.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class TriangleTest {

    /**
     * The true test for check area triangle.
     */
    @Test
    public void whenArea() {
        Triangle figure = new Triangle(new Point(0, 1), new Point(2, 4), new Point(5, 1));
        String cheked = "7,5";

        String result = String.format("%.1f", figure.area());

        assertThat(result, is(cheked));
    }

    /**
     * The false test for check area triangle.
     */
    @Test
    public void whenNotArea() {
        Triangle figure = new Triangle(new Point(1, 2), new Point(2, 3), new Point(3, 4));
        String message = "The area triangle can't be calculated";
        try {
            figure.area();
        } catch (IllegalArgumentException e) {
            assertThat(message, is(e.getMessage()));
        }
    }
}
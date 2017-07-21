package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Square.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class SquareTest {

    /**
     * The test for check solution square equation.
     */
    @Test
    public void whenCalculate() {
        Square square = new Square(2, 3, 1);
        float cheked = 15F;

        float result = square.calculate(2);

        assertThat(cheked, is(result));
    }
}
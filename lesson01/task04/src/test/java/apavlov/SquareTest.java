package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {

    @Test
    public void whenCalculate() throws Exception {
        Square square = new Square(2, 3, 1);
        float cheked = 15F;

        float result = square.calculate(2);

        assertThat(cheked, is(result));
    }
}
package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {

    @Test
    public void whenNIsNegative() throws Exception {
        Factorial fact = new Factorial();
        String message = "Value n can`t been negative!";
        try {
            fact.calculate(-1);
        } catch (IllegalArgumentException e) {
            assertThat(message, is(e.getMessage()));
        }
    }

    @Test
    public void whenNIsZero() throws Exception {
        Factorial fact = new Factorial();
        int cheked = 1;

        int result = fact.calculate(0);

        assertThat(cheked, is(result));
    }

    @Test
    public void whenNIsPositive() throws Exception {
        Factorial fact = new Factorial();
        int cheked = 24;

        int result = fact.calculate(4);

        assertThat(cheked, is(result));
    }
}
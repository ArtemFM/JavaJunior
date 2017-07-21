package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Factorial.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class FactorialTest {

    /**
     * The test when solution false (negative number).
     */
    @Test
    public void whenNIsNegative() {
        Factorial fact = new Factorial();
        String message = "Value n can`t been negative!";
        try {
            fact.calculate(-1);
        } catch (IllegalArgumentException e) {
            assertThat(message, is(e.getMessage()));
        }
    }

    /**
     * The test when n (end solution factorial) equal zero.
     */
    @Test
    public void whenNIsZero() {
        Factorial fact = new Factorial();
        int cheked = 1;

        int result = fact.calculate(0);

        assertThat(cheked, is(result));
    }

    /**
     * The test when n (end solution factorial) positive number.
     */
    @Test
    public void whenNIsPositive() {
        Factorial fact = new Factorial();
        int cheked = 24;

        int result = fact.calculate(4);

        assertThat(cheked, is(result));
    }
}
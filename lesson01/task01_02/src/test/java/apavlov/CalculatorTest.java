package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Calculator.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class CalculatorTest {

    /**
     * The test for check addition two number.
     */
    @Test
    public void whenAdd() {
        Calculator calc = new Calculator();
        double result = 7D;

        calc.add(3D, 4D);

        assertThat(calc.getResult(), is(result));
    }

    /**
     * The test for check difference two number.
     */
    @Test
    public void testSubstruct() {
        Calculator calc = new Calculator();
        double result = 2D;

        calc.substruct(5D, 3D);

        assertThat(calc.getResult(), is(result));
    }

    /**
     * The test for check division two number.
     */
    @Test
    public void testDiv() {
        Calculator calc = new Calculator();
        double result = 3D;

        calc.div(9D, 3D);

        assertThat(calc.getResult(), is(result));
    }

    /**
     * The test for check multiplication two number.
     */
    @Test
    public void testMultiple() {
        Calculator calc = new Calculator();
        double result = 12D;

        calc.multiple(3D, 4D);

        assertThat(calc.getResult(), is(result));
    }
}
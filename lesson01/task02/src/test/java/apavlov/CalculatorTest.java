package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAdd() throws Exception {
        Calculator calc = new Calculator();
        double result = 7D;

        calc.add(3D, 4D);

        assertThat(calc.getResult(), is(result));
    }

    @Test
    public void testSubstruct() throws Exception {
        Calculator calc = new Calculator();
        double result = 2D;

        calc.substruct(5D, 3D);

        assertThat(calc.getResult(), is(result));
    }

    @Test
    public void testDiv() throws Exception {
        Calculator calc = new Calculator();
        double result = 3D;

        calc.div(9D, 3D);

        assertThat(calc.getResult(), is(result));
    }

    @Test
    public void testMultiple() throws Exception {
        Calculator calc = new Calculator();
        double result = 12D;

        calc.multiple(3D, 4D);

        assertThat(calc.getResult(), is(result));
    }
}
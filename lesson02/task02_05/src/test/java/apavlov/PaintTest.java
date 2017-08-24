package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for classes Square and Triangle.
 *
 * @author Pavlov Artem
 * @since 23.08.2017
 */
public class PaintTest {
    /**
     * The method test check print square to console.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDrawSquare() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("******").append(System.lineSeparator());
        sb.append("******").append(System.lineSeparator());
        sb.append("******").append(System.lineSeparator());
        assertThat(sb.toString(), is(new Square(3).pic()));
    }

    /**
     * The method test check print triangle to console.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDrawTriangle() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("   *").append(System.lineSeparator());
        sb.append("  ***").append(System.lineSeparator());
        sb.append(" *****").append(System.lineSeparator());
        assertThat(sb.toString(), is(new Triangle(3).pic()));
    }
}
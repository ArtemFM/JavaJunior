package apavlov;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Calculate.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class CalculateTest {

    /**
     * The test for check output string.
     */
    @Test
    public void checkOutData() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculate.main(null);
        assertThat(out.toString(), is(String.format("Hello, World!")));
    }
}

package apavlov;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The test class ConverterTest for class`s Converter.
 *
 * @author Pavlov Artem
 * @since 17.03.2018
 */
public class StarterTest {
    /**
     * The constant - amount addition values.
     */
    private static final int AMOUNT_VALUES = 1_000_000;

    /**
     * The constant - max limit time to ms (5 minutes).
     */
    private static final long MAX_TIME_MS = 300_000;

    /**
     * The test method for check spend time job program.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckSpendTimeOnWork() throws Exception {
        long ms = System.currentTimeMillis();
        new Starter(AMOUNT_VALUES).start();
        ms = System.currentTimeMillis() - ms;
        long min = ms / 60000;
        ms = ms % 60000;
        long sec = ms / 1000;
        ms = ms % 1000;
        System.out.printf("%sTime spend: %d m. %d s. %d ms.;", System.lineSeparator(), min, sec, ms);
        assertThat(MAX_TIME_MS > ms, is(true));
    }
}
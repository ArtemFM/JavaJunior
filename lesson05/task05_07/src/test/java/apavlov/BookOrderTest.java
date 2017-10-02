package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class BookOrderTest for class`s BookOrder use junit4.
 *
 * @author Pavlov Artem
 * @since 03.10.2017
 */
public class BookOrderTest {
    /**
     * The test for check performance work program.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenTestSpeedPerformanceProgram() throws Exception {
        long start = System.currentTimeMillis();
        BookOrder books = new BookOrder("C:\\orders.xml");
        books.startProcess();
        assertThat(System.currentTimeMillis() - start < 6000, is(true));
        System.out.println(books.toString());
        long stop = System.currentTimeMillis() - start;
        assertThat(stop < 6000, is(true));
        System.out.printf("%s%ss %sms", "Time : ", stop / 1000, stop % 1000);
    }
}
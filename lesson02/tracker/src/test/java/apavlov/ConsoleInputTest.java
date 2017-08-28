package apavlov;

import apavlov.input.ConsoleInput;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class ConsoleInput.
 *
 * @author Pavlov Artem
 * @since 22.08.2017
 */
public class ConsoleInputTest {
    /**
     * The test check input console.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenInputToConsole() throws Exception {
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        String data = consoleInput.ask("Testing question: ");
        System.setIn(inputStream);
        assertThat("2", is(data));
    }

    /**
     * The test check input console by range.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void wheInputToConsoleWithRange() throws Exception {
        java.io.InputStream inputStream = System.in;
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        ConsoleInput consoleInput = new ConsoleInput();
        String data = consoleInput.ask("Testing question: ", 1, 5);
        System.setIn(inputStream);
        assertThat("1", is(data));
    }
}

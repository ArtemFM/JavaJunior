package apavlov;

import apavlov.input.Input;
import apavlov.input.StubInput;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The test class for class StubInput.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class StubInputTest {
    /**
     * The test array emulation input user.
     */
    private List<String> answers = Arrays.asList(new String[]{"1", "2", "3", "4", "5"});

    /**
     * The test check emulation input user`s.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenAskToArray() throws Exception {
        Input input = new StubInput(answers);
        for (String value : answers) {
            assertThat(value, is(input.ask("Test question: ")));
        }
    }
}

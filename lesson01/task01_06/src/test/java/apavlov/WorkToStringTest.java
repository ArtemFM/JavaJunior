package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class WorkToString.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class WorkToStringTest {

    /**
     * The one test when is search substring.
     */
    @Test
    public void whenContainsTrueOne() {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "ana";

        assertThat(true, is(ws.contains(origin, sub)));
    }

    /**
     * The two test when is search substring.
     */
    @Test
    public void whenContainsTrueTwo() {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "da";

        assertThat(true, is(ws.contains(origin, sub)));
    }

    /**
     * The three test when is search substring.
     */
    @Test
    public void whenContainsTrueThree() {
        WorkToString ws = new WorkToString();
        String origin = "Cocon";
        String sub = "con";

        assertThat(true, is(ws.contains(origin, sub)));
    }

    /**
     * The one test when is not search substring.
     */
    @Test
    public void whenContainsFalseFirst() {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "g";

        assertThat(false, is(ws.contains(origin, sub)));
    }

    /**
     * The two test when is not search substring.
     */
    @Test
    public void whenContainsFalseSecond() {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "cos";

        assertThat(false, is(ws.contains(origin, sub)));
    }
}
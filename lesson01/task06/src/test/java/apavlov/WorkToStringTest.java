package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WorkToStringTest {

    @Test
    public void whenContainsTrueOne() throws Exception {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "ana";

        assertThat(true, is(ws.contains(origin, sub)));
    }

    @Test
    public void whenContainsTrueTwo() throws Exception {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "da";

        assertThat(true, is(ws.contains(origin, sub)));
    }

    @Test
    public void whenContainsTrueThree() throws Exception {
        WorkToString ws = new WorkToString();
        String origin = "Cocon";
        String sub = "con";

        assertThat(true, is(ws.contains(origin, sub)));
    }

    @Test
    public void whenContainsFalseFirst() throws Exception {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "g";

        assertThat(false, is(ws.contains(origin, sub)));
    }

    @Test
    public void whenContainsFalseSecond() throws Exception {
        WorkToString ws = new WorkToString();
        String origin = "Anaconda";
        String sub = "cos";

        assertThat(false, is(ws.contains(origin, sub)));
    }
}
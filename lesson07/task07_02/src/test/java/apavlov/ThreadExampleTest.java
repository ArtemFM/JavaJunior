package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class ThreadExampleTest for class`s ThreadExample use junit4.
 *
 * @author Pavlov Artem
 * @since 17.12.2017
 */
public class ThreadExampleTest {
    /**
     * The test object ExampleObject for test threads.
     */
    private ExampleObject obj = new ExampleObject(10);

    /**
     * The method check that value to thread is not value to ExampleObject.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenWorkFirstThread() throws Exception {
        ThreadExample first = new ThreadExample("FirstThread", this.obj, 2, 10_000);
        first.start();
        first.join();
        assertThat(first.getValue(), is(20_010));
        assertThat(this.obj.getValue(), is(10));
        assertThat(this.obj.getValue() != first.getValue(), is(true));
    }

    /**
     * The method check that value to thread is not value to ExampleObject.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenWorkSecondThread() throws Exception {
        ThreadExample second = new ThreadExample("SecondThread", this.obj, 3, 10_000);
        second.start();
        second.join();
        assertThat(second.getValue(), is(30_010));
        assertThat(this.obj.getValue(), is(10));
        assertThat(this.obj.getValue() != second.getValue(), is(true));
    }
}
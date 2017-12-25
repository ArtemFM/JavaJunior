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
    private ExampleObject obj = new ExampleObject(0);

    /**
     * The method check that value to thread is not value to ExampleObject.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenWorkFirstThread() throws Exception {
        ThreadExample first = new ThreadExample("FirstThread", this.obj, 1, 1_000);
        ThreadExample second = new ThreadExample("SecondThread", this.obj, 1, 1_000);
        int result = 2_000;
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(result != this.obj.getValue(), is(true));
    }
}
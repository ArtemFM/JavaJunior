package apavlov;

import apavlov.list.MyQueue;
import apavlov.list.StackInterface;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class MyQueueTest for class`s MyQueue.
 *
 * @author Pavlov Artem
 * @since 19.09.2017
 */
public class MyQueueTest {
    /**
     * The test check correct add new element to queue.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenPushElementToStack() throws Exception {
        StackInterface<String> queue = new MyQueue<>();
        String[] result = {"d", "c", "b", "a"};

        queue.push("a");
        queue.push("b");
        queue.push("c");
        queue.push("d");

        assertThat(queue.toArray(), is(result));
    }

    /**
     * The test check correct return element and delete this element to queue.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenPollElementToStack() throws Exception {
        StackInterface<String> queue = new MyQueue<>();
        String[] result = {"b", "a"};
        String empty = null;

        assertThat(queue.poll(), is(empty));

        queue.push("a");
        queue.push("b");
        queue.push("c");
        queue.push("d");

        assertThat(queue.poll(), is("d"));
        assertThat(queue.poll(), is("c"));
        assertThat(queue.toArray(), is(result));
    }
}
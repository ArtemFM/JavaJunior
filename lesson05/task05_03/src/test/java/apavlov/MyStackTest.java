package apavlov;

import apavlov.list.MyStack;
import apavlov.list.StackInterface;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class MyStackTest for class`s MyStack.
 *
 * @author Pavlov Artem
 * @since 19.09.2017
 */
public class MyStackTest {
    /**
     * The test check correct add new element to stack.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenPushElementToStack() throws Exception {
        StackInterface<String> stack = new MyStack<>();
        String[] result = {"a", "b", "c", "d"};

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        assertThat(stack.toArray(), is(result));
    }

    /**
     * The test check correct return element and delete this element to stack.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenPollElementToStack() throws Exception {
        StackInterface<String> stack = new MyStack<>();
        String[] result = {"a", "b"};
        String empty = null;

        assertThat(stack.poll(), is(empty));

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        assertThat(stack.poll(), is("d"));
        assertThat(stack.poll(), is("c"));
        assertThat(stack.toArray(), is(result));
    }
}
package apavlov;

import apavlov.list.Cycle;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class CycleTest for class`s Cycle use junit4.
 *
 * @author Pavlov Artem
 * @since 27.09.2017
 */
public class CycleTest {
    /**
     * The test method check correct work if list is null.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenLinkedListIsNull() throws Exception {
        Cycle<Integer> list = new Cycle<>();
        assertThat(list.hasCycle(null), is(false));
    }

    /**
     * The test method check correct work, if list is cycle.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenLinkedListIsCycle() throws Exception {
        Cycle<Integer> list = new Cycle<>();
        assertThat(list.hasCycle(list.createLinkList(10, 0, null)), is(true));
        assertThat(list.hasCycle(list.createLinkList(1, 0, null)), is(true));
        assertThat(list.hasCycle(list.createLinkList(10, 9, null)), is(true));
        assertThat(list.hasCycle(list.createLinkList(10, 5, null)), is(true));
    }

    /**
     * The test method check correct work, if list is not cycle.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenLinkedListIsNotCycle() throws Exception {
        Cycle<Integer> list = new Cycle<>();
        assertThat(list.hasCycle(list.createLinkList(10, -1, null)), is(false));
        assertThat(list.hasCycle(list.createLinkList(0, 0, null)), is(false));
        assertThat(list.hasCycle(list.createLinkList(10, 10, null)), is(false));
        assertThat(list.hasCycle(list.createLinkList(10, 15, null)), is(false));
    }
}
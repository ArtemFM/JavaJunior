package apavlov;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class TreeTest for class`s Tree use junit4.
 *
 * @author Pavlov Artem
 * @since 01.10.2017
 */
public class TreeTest {
    /**
     * The test method check correct return count values to tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectReturnSizeTree() throws Exception {
        Tree<String> tree = new Tree<>("1");
        tree.add("1", null);
        tree.add("1", "2");
        tree.add("1", "2");
        tree.add(null, null);
        tree.add(null, "4");
        assertThat(tree.size(), is(4));
    }

    /**
     * The test method check correct check tree is empty or is not empty.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenTreeIsNotEmpty() throws Exception {
        Tree<String> tree = new Tree<>("1");
        assertThat(tree.isEmpty(), is(false));
    }

    /**
     * The test method check correct add new value to tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectAddValuesToTree() throws Exception {
        Tree<String> tree = new Tree<>("1");
        tree.add("1", null);
        tree.add("1", "1");
        tree.add("1", null);
        tree.add("1", "3");
        assertThat(tree.size(), is(3));
        assertThat(tree.toArrayChildrenParent("1"), is(new Object[]{null, "3"}));

        tree.add("2", "5");
        tree.add(null, "4");
        tree.add(null, null);
        tree.add("1", "2");
        assertThat(tree.size(), is(5));
        assertThat(tree.toArrayChildrenParent("1"), is(new Object[]{null, "3", "2"}));
        assertThat(tree.toArrayChildrenParent(null), is(new Object[]{"4"}));

        assertThat(tree.toString(), is("[1, null, 4, 3, 2]"));
    }

    /**
     * The method check correct work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkForeach() throws Exception {
        Tree<String> tree = new Tree<>("1");
        tree.add("1", null);
        tree.add("1", "2");
        tree.add("1", "3");
        tree.add("2", "4");
        tree.add("3", "5");
        tree.add("4", "6");

        String[] result = tree.toArray(new String[0]);
        int index = 0;

        for (String value : tree) {
            assertThat(value, is(result[index++]));
        }
    }

    /**
     * The test method check correct work iterator tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckIsCorrectWorkIterator() throws Exception {
        Tree<String> tree = new Tree<>("1");
        tree.add("1", null);
        tree.add("1", "2");
        tree.add("1", "3");
        tree.add("2", "4");
        tree.add("3", "5");
        tree.add("4", "6");

        String[] result = tree.toArray(new String[0]);
        int index = 0;

        Iterator<String> iterator = tree.iterator();
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(result[index++]));
        }
    }

    /**
     * The test method check throws exception is not correct work iterator.
     *
     * @throws Exception - throws NoSuchElementException if elements the end;
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCheckIsNotCorrectWorkIterator() throws Exception {
        Tree<String> tree = new Tree<>("1");
        Iterator<String> iterator = tree.iterator();
        iterator.next();
        iterator.next();
    }
}
package apavlov;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class SimpleLinkedSetTest for class`s SimpleLinkedSet use junit4.
 *
 * @author Pavlov Artem
 * @since 27.09.2017
 */
public class SimpleLinkedSetTest {
    /**
     * The first test array.
     */
    private String[] arrayFirst = {null};

    /**
     * The second test array.
     */
    private String[] arraySecond = {"1", null, "1", null, "2", "3", "1", null, "4", "4", "5", null};

    /**
     * The test method check correct return size array.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectReturnSizeCollection() throws Exception {
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
        set.add(null);
        set.add("1");
        set.add(null);
        assertThat(set.size(), is(2));
    }

    /**
     * The test method check correct add element`s equal null.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddElementIsNullToCollection() throws Exception {
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>();
        set.add(null);
        assertThat(set.toString(), is("[null]"));
    }

    /**
     * The test method check correct addition array to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckAddArrayToCollection() throws Exception {
        String[] resultFirst = {null};
        String[] resultSecond = {null, "1", "2", "3", "4", "5"};

        SimpleLinkedSet<String> set = new SimpleLinkedSet<>(arrayFirst);
        assertThat(set.toArray(), is(resultFirst));

        set.addAll(arraySecond);
        assertThat(set.toArray(), is(resultSecond));
    }

    /**
     * The test method check correct work foreach to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForeach() throws Exception {
        String[] result = {"1", null, "2", "3", "4", "5"};
        int index = 0;
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>(arraySecond);
        for (String value : set) {
            assertThat(value, is(result[index++]));
        }
    }

    /**
     * The test method check correct work iterator`s collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenIsCorrectWorkIterator() throws Exception {
        String[] result = {"1", null, "2", "3", "4", "5"};
        int index = 0;
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>(arraySecond);
        Iterator<String> iterator = set.iterator();
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
    public void whenIsNotCorrectWorkIterator() throws Exception {
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>(new String[]{"1"});
        Iterator<String> iterator = set.iterator();
        iterator.next();
        iterator.next();
    }
}
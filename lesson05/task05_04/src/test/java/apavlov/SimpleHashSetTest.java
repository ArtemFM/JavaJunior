package apavlov;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class SimpleHashSetTest for class`s SimpleHashSet use junit4.
 *
 * @author Pavlov Artem
 * @since 28.09.2017
 */
public class SimpleHashSetTest {
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
        SimpleHashSet<String> set = new SimpleHashSet<>();
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
        SimpleHashSet<String> set = new SimpleHashSet<>();
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

        SimpleHashSet<String> set = new SimpleHashSet<>(arrayFirst);
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
        String[] result = {null, "1", "2", "3", "4", "5"};
        int index = 0;
        SimpleHashSet<String> set = new SimpleHashSet<>(arraySecond);
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
        String[] result = {null, "1", "2", "3", "4", "5"};
        int index = 0;
        SimpleHashSet<String> set = new SimpleHashSet<>(arraySecond);
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
        SimpleHashSet<String> set = new SimpleHashSet<>(new String[]{"1"});
        Iterator<String> iterator = set.iterator();
        iterator.next();
        iterator.next();
    }
}
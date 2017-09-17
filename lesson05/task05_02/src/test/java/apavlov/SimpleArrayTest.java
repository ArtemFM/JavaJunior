package apavlov;

import org.junit.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class SimpleArrayTest for class`s SimpleArray use junit4.
 *
 * @author Pavlov Artem
 * @since 17.09.2017
 */
public class SimpleArrayTest {
    /**
     * The test class check is empty array or in not.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenArrayIsEmpty() throws Exception {
        SimpleArray<String> array = new SimpleArray<>(0);
        assertThat(array.isEmpty(), is(true));
    }

    /**
     * The test class check size array.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectSizeArray() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        assertThat(array.size(), is(1));
    }

    /**
     * The test class check delete all elements array.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenClearArray() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        assertThat(array.size(), is(2));
        array.clear();
        assertThat(array.isEmpty(), is(true));
    }

    /**
     * The test class check method return array type Object.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetArray() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        String[] result = {"a", "b", "c"};
        assertThat(array.toArray(), is(result));
    }

    /**
     * The test class check method toString.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetStringToArray() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        String[] result = {"a", "b", "c"};
        assertThat(array.toString(), is(Arrays.toString(result)));
    }

    /**
     * The test class check add new element to array.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewElementToArray() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        assertThat(array.size(), is(1));
        assertThat(array.get(0), is("a"));
    }

    /**
     * The method check get add new element by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewElementByIndexToArray() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("c");
        assertThat(array.size(), is(2));

        assertThat(array.add(1, "b"), is(true));
        assertThat(array.get(1), is("b"));
        assertThat(array.size(), is(3));

        assertThat(array.add(5, "e"), is(false));
        assertThat(array.size(), is(3));
    }

    /**
     * The method check get element by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetElementByIndex() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        assertThat(array.get(1), is("b"));
    }

    /**
     * The method check get element by index when index is not range array.
     *
     * @throws Exception - IndexOutOfBoundsException.class;
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIsNotGetElementByIndex() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        String result = array.get(1);
    }

    /**
     * The method check delete element by value.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteElementByValue() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        String[] result = {"a", "c"};
        assertThat(array.delete("h"), is(false));
        assertThat(array.delete("b"), is(true));
        assertThat(array.toArray(), is(result));
    }

    /**
     * The method check delete element to array by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteElementByIndex() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        String[] result = {"a", "b"};
        assertThat(array.delete(5), is(false));
        assertThat(array.delete(2), is(true));
        assertThat(array.toArray(), is(result));
    }

    /**
     * The method check edit element by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenUpdateElementByIndex() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        String[] result = {"a", "h", "c"};
        assertThat(array.update(1, "h"), is("b"));
        assertThat(array.toArray(), is(result));
    }

    /**
     * The method check edit element by index when index is not range array.
     *
     * @throws Exception - IndexOutOfBoundsException.class;
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIsNotUpdateElementByIndex() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        array.update(5, "e");
    }

    /**
     * The method check work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForeach() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        String[] result = {"a", "b", "c"};
        int index = 0;
        for (String value : array) {
            assertThat(value, is(result[index++]));
        }
    }

    /**
     * The method check work iterator`s.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkIterator() throws Exception {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("a");
        array.add("b");
        array.add("c");
        Iterator<String> iterator = array.iterator();
        String[] result = {"a", "b", "c"};
        int index = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(result[index++]));
        }
    }
}
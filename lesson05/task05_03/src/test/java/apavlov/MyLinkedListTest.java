package apavlov;

import apavlov.list.MyLinkedList;
import apavlov.list.MyList;
import org.junit.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class MyLinkedListTest for class`s MyLinkedList use junit4.
 *
 * @author Pavlov Artem
 * @since 18.09.2017
 */
public class MyLinkedListTest {
    /**
     * The test method check is empty list or is not empty.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckIsEmptyOrIsNotEmpty() throws Exception {
        MyList<String> list = new MyLinkedList<>();
        assertThat(list.isEmpty(), is(true));
        list.add("a");
        assertThat(list.isEmpty(), is(false));
    }

    /**
     * The test method check correct return size list.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectSizeList() throws Exception {
        MyList<String> list = new MyLinkedList<>();
        assertThat(list.size(), is(0));
        list.addAll(new String[]{"a", "b", "c"});
        assertThat(list.size(), is(3));
    }

    /**
     * The test method check clear list.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckClearList() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c"});
        assertThat(list.size(), is(3));
        list.clear();
        assertThat(list.size(), is(0));
    }

    /**
     * The test method check correct return array.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetArrayToList() throws Exception {
        String[] result = {"a", "b", "c"};
        MyList<String> list = new MyLinkedList<>(result);
        assertThat(list.size(), is(result.length));
        assertThat(list.toArray(), is(result));
    }

    /**
     * The test method check correct return list to string.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetStringToList() throws Exception {
        String[] result = {"a", "b", "c"};
        MyList<String> list = new MyLinkedList<>(result);
        assertThat(list.size(), is(result.length));
        assertThat(list.toString(), is(Arrays.toString(result)));
    }

    /**
     * The test method check add new element to end list.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewElement() throws Exception {
        MyList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is("a"));
        assertThat(list.get(1), is("b"));
    }

    /**
     * The test method check add new element by index to list.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewElementByIndex() throws Exception {
        MyList<String> list = new MyLinkedList<>();
        String[] result = {"b", "a", "c", "e"};

        list.add(0, "a");
        list.add(0, "b");
        list.add(2, "e");
        list.add(2, "c");

        assertThat(list.add(10, "g"), is(false));
        assertThat(list.size(), is(4));
        assertThat(list.toArray(), is(result));
    }

    /**
     * The test method check add array to list.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenAddArrayToList() throws Exception {
        MyList<String> list = new MyLinkedList<>();
        String[] result = {"b", "a", "c", "e"};
        assertThat(list.addAll(result), is(true));
        assertThat(list.addAll(new String[]{}), is(false));
        assertThat(list.size(), is(result.length));
        assertThat(list.toArray(), is(result));
    }

    /**
     * The test method check correct get element by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetElementByIndex() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        assertThat(list.get(2), is("c"));
    }

    /**
     * The test method check exception get element by index is not range.
     *
     * @throws Exception - NoSuchElementException - when index is not range;
     */
    @Test(expected = NoSuchElementException.class)
    public void whentGetElementByIndexButIndexIsNotRange() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        assertThat(list.get(20), is(""));
    }

    /**
     * The test method check get index element by value.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetIndexElementByValue() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        assertThat(list.get("b"), is(1));
        assertThat(list.get("h"), is(-1));
    }

    /**
     * The test method check correct delete element by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteElementByIndex() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        String[] result = {"b", "d"};
        assertThat(list.delete(10), is(false));
        assertThat(list.delete(0), is(true));
        assertThat(list.delete(list.size() - 1), is(true));
        assertThat(list.delete(list.size() >> 1), is(true));
        assertThat(list.toArray(), is(result));
    }

    /**
     * The test method check correct delete element by value.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteElementByValue() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        String[] result = {"b", "d"};
        assertThat(list.delete("h"), is(false));
        assertThat(list.delete("a"), is(true));
        assertThat(list.delete("e"), is(true));
        assertThat(list.delete("c"), is(true));
        assertThat(list.toArray(), is(result));
    }

    /**
     * The test method correct update element by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenIsUpdateElement() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        String[] result =  {"a", "b", "h", "d", "e"};
        list.update(2, "h");
        assertThat(list.toArray(), is(result));
    }

    /**
     * The test method incorrect update element by index.
     *
     * @throws Exception - NoSuchElementException - when index is not range size list`s;
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIsNotUpdateElement() throws Exception {
        MyList<String> list = new MyLinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        list.update(10, "h");
    }

    /**
     * The test method check correct work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkForeach() throws Exception {
        String[] result = {"a", "b", "c", "d", "e"};
        MyList<String> list = new MyLinkedList<>(result);
        int index = 0;
        for (String value : list) {
            assertThat(value, is(result[index++]));
        }
    }

    /**
     * The test method check correct work iterator for list.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenUseCorrectIteratorForList() throws Exception {
        String[] result = {"a", "b", "c", "d", "e"};
        MyList<String> list = new MyLinkedList<>(result);
        Iterator<String> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(result[index++]));
        }
    }

    /**
     * The test method check incorrect work iterator for list.
     *
     * @throws Exception - NoSuchElementException - when next element is not found to list;
     */
    @Test(expected = NoSuchElementException.class)
    public void whenUseIncorrectIteratorForList() throws Exception {
        MyList<String> list = new MyLinkedList<>();
        list.iterator().next();
    }
}
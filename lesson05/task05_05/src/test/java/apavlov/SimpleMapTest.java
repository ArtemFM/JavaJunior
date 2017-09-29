package apavlov;

import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class SimpleMapTest for class`s SimpleMap use junit4.
 *
 * @author Pavlov Artem
 * @since 29.09.2017
 */
public class SimpleMapTest {
    /**
     * The test method check correct return size collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectReturnSizeCollection() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();

        map.insert("01", 1);
        map.insert("02", 2);
        map.insert(null, 3);
        map.insert("01", 10);

        assertThat(map.size(), is(3));
    }

    /**
     * The test method check correct addition array to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckInsertToCollection() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String result = "{null=3, 1=1, 2=2}";

        assertThat(map.insert("1", 1), is(true));
        assertThat(map.insert("2", 2), is(true));
        assertThat(map.insert(null, 3), is(true));
        assertThat(map.insert("1", 10), is(false));
        assertThat(map.insert(null, null), is(false));
        assertThat(map.size(), is(3));
        assertThat(map.toString(), is(result));
    }

    /**
     * The test method check correct return value use method get.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkMethodGet() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Integer value = null;

        map.insert("01", 1);
        map.insert("02", 2);
        map.insert(null, 3);
        map.insert("01", 10);

        assertThat(map.get("01"), is(1));
        assertThat(map.get(null), is(3));
        assertThat(map.get("05"), is(value));
    }

    /**
     * The test method check correct work delete element to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectDeleteElement() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();

        map.insert("01", 1);
        map.insert("02", 2);
        map.insert(null, 3);
        map.insert("01", 10);

        assertThat(map.size(), is(3));

        assertThat(map.delete("01"), is(true));
        assertThat(map.delete("10"), is(false));
        assertThat(map.delete(null), is(true));

        assertThat(map.size(), is(1));
        assertThat(map.toString(), is("{02=2}"));
    }

    /**
     * The test method check correct work foreach to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForeach() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String[] resultKey = {null, "01", "02"};
        Integer[] resultValue = {3, 1, 2};
        int index = 0;

        map.insert("01", 1);
        map.insert("02", 2);
        map.insert(null, 3);
        map.insert("01", 10);

        for (SimpleMap.Entry element : map) {
            assertThat(element.getKey(), is(resultKey[index]));
            assertThat(element.getValue(), is(resultValue[index++]));
        }
    }

    /**
     * The test method for check correct work iterator.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckIsCorrectWorkIterator() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        String[] resultKey = {null, "01", "02"};
        Integer[] resultValue = {3, 1, 2};
        int index = 0;

        map.insert("01", 1);
        map.insert("02", 2);
        map.insert(null, 3);
        map.insert("01", 10);

        Iterator iterator = map.iterator();
        while (iterator.hasNext()) {
            SimpleMap.Entry element = (SimpleMap.Entry) iterator.next();
            assertThat(element.getKey(), is(resultKey[index]));
            assertThat(element.getValue(), is(resultValue[index++]));
        }
    }

    /**
     * The test method check throws exception is not correct work iterator.
     *
     * @throws Exception - throws NoSuchElementException if elements the end;
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCheckIsNotCorrectWorkIterator() throws Exception {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("01", 1);

        Iterator iterator = map.iterator();
        iterator.next();
        iterator.next();
    }
}
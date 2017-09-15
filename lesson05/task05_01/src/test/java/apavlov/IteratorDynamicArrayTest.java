package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * The test class IteratorDynamicArrayTest for class`s IteratorDynamicArray.
 *
 * @author Pavlov Artem
 * @since 14.09.2017
 */
public class IteratorDynamicArrayTest {
    /**
     * The test method for check work class, if array is null.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenArrayIsNull() throws Exception {
        IteratorDynamicArray iterator = new IteratorDynamicArray(null);
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * The test method for check work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForeach() throws Exception {
        IteratorDynamicArray iterator = new IteratorDynamicArray(new Integer[][]{{1, 2, 3}, {4, 5, 6}});
        int resulValue = 1;
        for (Object value : iterator) {
            assertThat(value, is(resulValue++));
        }
    }

    /**
     * The test method for check correct get next element.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetNextElement() throws Exception {
        IteratorDynamicArray iterator = new IteratorDynamicArray(new Integer[][]{{1, 2, 3}});
        int resultValue = 3;
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(resultValue));
    }

    /**
     * The test method for check correct get next element to array different length.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetNextElementDifferentLength() throws Exception {
        IteratorDynamicArray iterator = new IteratorDynamicArray(new Integer[][]{{1, 2}, {3}});
        int resultValue = 3;
        iterator.next();
        iterator.next();
        assertThat(iterator.next(), is(resultValue));
    }
}
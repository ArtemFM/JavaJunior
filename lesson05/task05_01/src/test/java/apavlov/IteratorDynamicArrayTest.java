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
     * The test method for check work class, if array is empty.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAttayIsEmpty() throws Exception {
        IteratorDynamicArray iterator = new IteratorDynamicArray(new Integer[][]{{}});
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * The test method for check correct get next element foreach to array different length.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetNextElementDifferentLengthForeach() throws Exception {
        Integer[][] inArray = {
                {},
                {1, 2, 3},
                {},
                {4},
                {5, 6, 7, 8, 9},
                {},
                {}
        };
        IteratorDynamicArray<Integer> iterator = new IteratorDynamicArray(inArray);
        int[] resultArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = 0;
        for (int value : iterator) {
            assertThat(value, is(resultArray[index++]));
        }
    }

    /**
     * The test method for check correct get next element iterator to array different length.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetNextElementDifferentLengthIterator() throws Exception {
        Integer[][] inArray = {
                {},
                {},
                {1, 2},
                {3},
                {4},
                {},
                {5, 6, 7, 8, 9},
        };
        IteratorDynamicArray<Integer> iterator = new IteratorDynamicArray(inArray);
        int[] resultArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(resultArray[index++]));
        }
    }
}
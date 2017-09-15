package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class IteratorPrimeArrayTest for class`s IteratorPrimeArray.
 *
 * @author Pavlov Artem
 * @since 14.09.2017
 */
public class IteratorPrimeArrayTest {
    /**
     * The test method for check work class, if array is null.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenArrayIsNull() throws Exception {
        IteratorPrimeArray iterator = new IteratorPrimeArray(null);
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * The test method for check work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForeach() throws Exception {
        int[] values = {1, 2, 1, 1, 2, 5, 3, 4, 7, 7, 10, 7, 15, 17, 21};
        IteratorPrimeArray iterator = new IteratorPrimeArray(values);
        int[] resultValues = {5, 3, 7, 7, 7, 17};
        int index = 0;
        for (Object value : iterator) {
            assertThat(value, is(resultValues[index++]));
        }
    }

    /**
     * The test method for check correct get next element.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetNextElement() throws Exception {
        int[] values = {1, 2, 1, 1, 2, 5, 3, 4, 7, 7, 10, 7, 15, 17, 21};
        IteratorPrimeArray iterator = new IteratorPrimeArray(values);
        int[] resultValues = {5, 3, 7, 7, 7, 17};
        int index = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(resultValues[index++]));
        }
    }
}
package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class IteratorEvenIntTest for class`s IteratorEvenInt.
 *
 * @author Pavlov Artem
 * @since 14.09.2017
 */
public class IteratorEvenIntTest {
    /**
     * The test method for check work class, if array is null.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenArrayIsNull() throws Exception {
        IteratorEvenInt iterator = new IteratorEvenInt(null);
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * The test method for check work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForeach() throws Exception {
        IteratorEvenInt iterator = new IteratorEvenInt(new int[]{1, 2, 3, 4, 5, 6});
        int[] resultValues = {2, 4, 6};
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
        IteratorEvenInt iterator = new IteratorEvenInt(new int[]{1, 2, 1, 1, 2, 5, 3, 4, 7, 7, 10, 7});
        int[] resultValue = {2, 2, 4, 10};
        int index = 0;
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(resultValue[index++]));
        }
    }
}
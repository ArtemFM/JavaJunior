package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class BubbleSort.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class BubbleSortTest {

    /**
     * The test sort array.
     */
    @Test
    public void whenSort() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] startArray = {3, 2, 1, 4, 6, 5, 8, 7, 10, 9};
        int[] checked = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] result = bubbleSort.sort(startArray);

        assertThat(checked, is(result));
    }
}
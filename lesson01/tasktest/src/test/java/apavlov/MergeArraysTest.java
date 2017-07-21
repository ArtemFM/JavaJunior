package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class MergeArrays.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class MergeArraysTest {

    /**
     * The test when arrays is not sort.
     */
    @Test
    public void testCheckSortFalse() {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 2, 3, 1, 4, 5};
        boolean checked = false;

        boolean result = merge.checkSort(a, b);

        assertThat(checked, is(result));
    }

    /**
     * The test when arrays is sort.
     */
    @Test
    public void testCheckSortTrue() {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 5, 7, 8, 9};
        boolean checked = true;

        boolean result = merge.checkSort(a, b);

        assertThat(checked, is(result));
    }

    /**
     * The test combine sorted arrays.
     */
    @Test
    public void testMerge() {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 5, 7, 8, 9};
        int[] checked = {1, 2, 3, 3, 4, 5, 5, 7, 8, 9};

        int[] result = merge.merge(a, b);

        assertThat(checked, is(result));
    }

    /**
     * The test when arrays is not sort and nethod call exception.
     */
    @Test
    public void testMergeException() {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 2, 3, 1, 4, 5};
        String msg = "Error: The arrays is not sort";

        try {
            merge.merge(a, b);
        } catch (UnsupportedOperationException e) {
            assertThat(msg, is(e.getMessage()));
        }
    }
}
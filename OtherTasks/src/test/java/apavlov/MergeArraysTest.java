package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeArraysTest {

    @Test
    public void testCheckSortFalse() throws Exception {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 2, 3, 1, 4, 5};
        boolean checked = false;

        boolean result = merge.checkSort(a, b);

        assertThat(checked, is(result));
    }

    @Test
    public void testCheckSortTrue() throws Exception {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 5, 7, 8, 9};
        boolean checked = true;

        boolean result = merge.checkSort(a, b);

        assertThat(checked, is(result));
    }

    @Test
    public void testMerge() throws Exception {
        MergeArrays merge = new MergeArrays();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 5, 7, 8, 9};
        int[] checked = {1, 2, 3, 3, 4, 5, 5, 7, 8, 9};

        int[] result = merge.merge(a, b);

        assertThat(checked, is(result));
    }

    @Test
    public void testMergeException() throws Exception {
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
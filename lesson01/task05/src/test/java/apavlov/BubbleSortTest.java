package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void whenSort() throws Exception {
        BubbleSort bubbleSort = new BubbleSort();
        int[] startArray = {3, 2, 1, 4, 6, 5, 8, 7, 10, 9};
        int[] cheked = {1, 2, 3 ,4 ,5 , 6, 7, 8, 9, 10};

        int[] result = bubbleSort.sort(startArray);

        assertThat(cheked, is(result));
    }
}
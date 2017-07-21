package apavlov;

/**
 * The class BubbleSort for sort array method bubble.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class BubbleSort {

    /**
     * The method sort array.
     *
     * @param array - array for sort;
     * @return sort array;
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}

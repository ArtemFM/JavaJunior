package apavlov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class ConvertList for convert array to list and list to array.
 *
 * @author Pavlov Artem
 * @since 06.09.2017
 */
public class ConvertList {
    /**
     * The method convert dynamic array to list.
     *
     * @param array - array for convert;
     * @return list;
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>(array.length * array[0].length);
        for (int[] i : array) {
            for (int k : i) {
                list.add(k);
            }
        }
        return list;
    }

    /**
     * The method convert list to dynamic array.
     *
     * @param list - list for convert;
     * @return - dynamic array;
     */
    public int[][] toArray(List<Integer> list) {
        int[] array = new int[list.size()];
        int count = 0;
        for (Integer value : list) {
            if (value != null) {
                array[count++] = value;
            }
        }
        array = Arrays.copyOf(array, count);
        int length = (int) Math.ceil(Math.sqrt(array.length));
        int[][] arrayDyn = new int[length][length];
        int i = 0;
        int k = 0;
        for (int value : array) {
            arrayDyn[i][k++] = value;
            if (k == array.length - 1) {
                i++;
                k = 0;
            }
        }
        return arrayDyn;
    }
}

package apavlov;

import java.util.ArrayList;
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
        int length = (int) Math.ceil(Math.sqrt(list.size()));
        int[][] array = new int[length][length];
        int i = 0;
        int k = 0;
        for (Integer value : list) {
            array[i][k] = value;
            k++;
            if (k == length) {
                k = 0;
                i++;
            }

        }
        return array;
    }
}

package apavlov;

/**
 * The class TurnArray for turn on 90 degrees double array.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class TurnArray {

    /**
     * The method for turn double array on 90 degrees to right.
     *
     * @param array - array for work;
     * @return turn array;
     */
    public int[][] turnToRight(int[][] array) {
        int[][] resultArray = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
               resultArray[j][array.length - i - 1] = array[i][j];
            }
        }
        return resultArray;
    }

    /**
     * The method for turn double array on 90 degrees to left.
     *
     * @param array - array for work;
     * @return turn array;
     */
    public int[][] turnToLeft(int[][] array) {
        int[][] resultArray = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                resultArray[array[i].length - j - 1][i] = array[i][j];
            }
        }
        return resultArray;
    }
}

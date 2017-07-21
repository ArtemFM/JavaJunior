package apavlov;

/**
 * The class StringArray for delete duplicate.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class StringArray {

    /**
     * The method for delete duplicate in the array.
     *
     * @param array - array for work;
     * @return - array without duplicate;
     */
    public String[] deleteDuplicate(String[] array) {
        int countDuplicate = 0;
        for (int i = 0; i < array.length - 1; i++) {
            String temp = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (temp != null && temp.equals(array[j])) {
                    array[j] = null;
                    countDuplicate++;
                }
            }
        }
        String[] resultArray = new String[array.length - countDuplicate];
        int index = 0;
        for (String str : array) {
            if (str != null) {
                resultArray[index] = str;
                index++;
            }
        }
        return resultArray;
    }
}

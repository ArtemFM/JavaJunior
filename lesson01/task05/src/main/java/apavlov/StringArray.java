package apavlov;

public class StringArray {
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

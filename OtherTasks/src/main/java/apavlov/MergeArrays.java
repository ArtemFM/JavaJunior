package apavlov;

public class MergeArrays {
    private int iFirst;
    private int iSecond;
    private int iResult;

    public boolean checkSort(int[] first, int[] second) {
        boolean result = true;
        int length = first.length <= second.length ? second.length : first.length;
        if (length != 0) {
            for (int i = 1; i < length; i++) {
                if (i < first.length && first[i] < first[i - 1]) {
                    result = false;
                    break;
                }
                if (i < second.length && second[i] < second[i - 1]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public int[] merge(int[] first, int[] second) {
        if (!checkSort(first, second)) {
            throw new UnsupportedOperationException("Error: The arrays is not sort");
        }
        int[] result = new int[first.length + second.length];
        while (iFirst < first.length && iSecond < second.length) {
            result[iResult++] = first[iFirst] < second[iSecond] ? first[iFirst++] : second[iSecond++];
        }
        if (iFirst < first.length) {
            for (int i = iFirst; i < first.length; i++) {
                result[iResult++] = first[i];
            }
        }
        if (iSecond < second.length) {
            for (int i = iSecond; i < second.length; i++) {
                result[iResult++] = second[i];
            }
        }
        this.iFirst = 0;
        this.iSecond = 0;
        this.iResult = 0;
        return result;
    }
}

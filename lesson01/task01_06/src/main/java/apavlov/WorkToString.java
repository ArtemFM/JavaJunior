package apavlov;

/**
 * The class WorkToString for search substring.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class WorkToString {

    /**
     * The method search substring to string.
     *
     * @param origin - text;
     * @param sub    - substring for search;
     * @return result (true - is search; false - is not search)
     */
    public boolean contains(String origin, String sub) {
        boolean result = false;
        int count;
        if (origin.length() >= sub.length()) {
            char[] originChar = origin.toLowerCase().toCharArray();
            char[] subChar = sub.toLowerCase().toCharArray();
            for (int i = 0; i <= originChar.length - subChar.length; i++) {
                count = 0;
                for (int j = i; j < i + subChar.length; j++) {
                    if (originChar[j] == subChar[j - i]) {
                        count++;
                    }
                }
                if (count == subChar.length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}

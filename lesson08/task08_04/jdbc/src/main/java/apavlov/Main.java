package apavlov;

/**
 * The class Main - main class for start program.
 *
 * @author Pavlov Artem
 * @since 17.03.2018
 */
public class Main {
    /**
     * The nain method - point start program.
     *
     * @param args - array parameters to start program;
     */
    public static void main(String[] args) {
        new Starter(parseStringToInt(args[0])).start();
    }

    /**
     * The static method parse string to integer.
     *
     * @param number - number type string;
     * @return number type int;
     */
    private static int parseStringToInt(String number) {
        int result = -1;
        try {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }
}

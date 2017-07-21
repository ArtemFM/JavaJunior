package apavlov;

/**
 * The class Factorial for search factorial.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Factorial {

    /**
     * The method solution factoral.
     *
     * @param n - position end solution factorial;
     * @return resultat solution factorial
     */
    public int calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Value n can`t been negative!");
        } else {
            int i = 2;
            int fact = 1;
            while (i <= n) {
                fact *= i;
                i++;
            }
            return fact;
        }
    }
}

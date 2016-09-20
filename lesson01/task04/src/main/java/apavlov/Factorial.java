package apavlov;

public class Factorial {
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

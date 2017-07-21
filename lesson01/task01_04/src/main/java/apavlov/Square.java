package apavlov;

/**
 * The class Squre for solution square equation.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Square {
    /**
     * The data var a in the equation.
     */
    private float a;

    /**
     * The data var b in the equation.
     */
    private float b;

    /**
     * The data var c in the equation.
     */
    private float c;

    /**
     * The constructor class Square.
     *
     * @param a - var a in the equation;
     * @param b - var b in the equation;
     * @param c - var c in the equation;
     */
    public Square(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * The method solution square equation.
     *
     * @param x - var x in equation;
     * @return solution square equation;
     */
    public float calculate(int x) {
        return a * x * x + b * x + c;
    }

    /**
     * The method solution square equation at different x.
     *
     * @param start  - start value x;
     * @param finish - end value x;
     * @param step   - step for x;
     */
    public void show(int start, int finish, int step) {
        for (int i = start; i <= finish; i += step) {
            System.out.printf("x = %d; %.2f; %s", i, calculate(i), System.lineSeparator());
        }
    }
}

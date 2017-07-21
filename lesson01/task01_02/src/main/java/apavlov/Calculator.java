package apavlov;

/**
 * The class Calculator.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Calculator {

    /**
     * The var for save result.
     */
    private double result;

    /**
     * The getter for var result.
     *
     * @return result solution
     */
    public double getResult() {
        return result;
    }

    /**
     * The method addition two number.
     *
     * @param first - first number for solution
     * @param second - second number for solution
     */
    public void add(double first, double second) {
        result = first + second;
    }

    /**
     * The method difference two number.
     *
     * @param first - first number for solution
     * @param second - second number for solution
     */
    public void substruct(double first, double second) {
        result = first - second;
    }

    /**
     * The method division two number.
     *
     * @param first - first number for solution
     * @param second - second number for solution
     */
    public void div(double first, double second) {
        result = first / second;
    }

    /**
     * The method multiplication two number.
     *
     * @param first - first number for solution
     * @param second - second number for solution
     */
    public void multiple(double first, double second) {
        result = first * second;
    }
}

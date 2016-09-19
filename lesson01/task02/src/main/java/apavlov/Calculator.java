package apavlov;

public class Calculator {
    private double result;

    public double getResult() {
        return result;
    }

    public void add(double first, double second) {
        result = first + second;
    }

    public void substruct(double first, double second) {
        result = first - second;
    }

    public void div(double first, double second) {
        result = first / second;
    }

    public void multiple(double first, double second) {
        result = first * second;
    }
}

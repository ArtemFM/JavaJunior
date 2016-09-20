package apavlov;

public class Square {
    private float a;
    private float b;
    private float c;

    public Square(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float calculate(int x) {
        return a * x * x + b * x + c;
    }

    public void show(int start, int finish, int step) {
        for (int i = start; i <= finish; i += step) {
            System.out.println("x = " + i + "; " + String.format("%.2f", calculate(i)));
        }
    }
}

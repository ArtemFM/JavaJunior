package apavlov;

public class MaxSides {
    public double max(Figure figure) {
        double max = 0;
        for (double i : figure.longLines()) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }
}

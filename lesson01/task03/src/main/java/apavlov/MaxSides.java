package apavlov;

public class MaxSides {
    public double max(Triangle figure) {
        double[] sides = {
                figure.getA().distanceTo(figure.getB()),
                figure.getA().distanceTo(figure.getC()),
                figure.getC().distanceTo(figure.getB()),
        };
        double maxSide = 0;
        for (double i : sides) {
            if (maxSide < i) {
                maxSide = i;
            }
        }
        return maxSide;
    }
}

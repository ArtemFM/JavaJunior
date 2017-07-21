package apavlov;

/**
 * The class MaxSide for search max side shape.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class MaxSides {
    /**
     * The method for search mas side in triangle.
     *
     * @param figure - object triangle
     * @return maxSide - max side in triangle
     */
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

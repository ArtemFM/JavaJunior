package apavlov;

/**
 * The class Triangle.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Triangle {
    /**
     * The var a - position point a.
     */
    private Point a;

    /**
     * The var b - position point b.
     */
    private Point b;

    /**
     * The var c - position point c.
     */
    private Point c;

    /**
     * The getter for var a.
     *
     * @return position point a
     */
    public Point getA() {
        return a;
    }

    /**
     * The getter for var b.
     *
     * @return position point b
     */
    public Point getB() {
        return b;
    }

    /**
     * The getter for var c.
     *
     * @return position point c
     */
    public Point getC() {
        return c;
    }

    /**
     * The constructor for class Triangle.
     *
     * @param a - position point a;
     * @param b - position point b;
     * @param c - position point c;
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * The method for search area shape Triangle.
     *
     * @return return area shape Triangle
     */
    public double area() {
        double sideA = a.distanceTo(b);
        double sideB = a.distanceTo(c);
        double sideC = b.distanceTo(c);
        double s = (sideA + sideB + sideC) / 2;
        double areaTriangle = Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
        if (areaTriangle == 0) {
            throw new IllegalArgumentException("The area triangle can't be calculated");
        }
        return areaTriangle;
    }
}

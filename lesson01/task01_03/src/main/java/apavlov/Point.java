package apavlov;

/**
 * The class Point for save position point and search distance between two points.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Point {
    /**
     * The var x - position.
     */
    private double x;

    /**
     * The var y - position.
     */
    private double y;

    /**
     * The getter for var x.
     *
     * @return x - position
     */
    public double getX() {
        return x;
    }

    /**
     * The getter for var y.
     *
     * @return y - position
     */
    public double getY() {
        return y;
    }

    /**
     * The constructor for class Point.
     *
     * @param x - position x
     * @param y - position y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method search distance between two points.
     *
     * @param point - object point
     * @return distance between two points
     */
    public double distanceTo(Point point) {
        double temp = Math.pow(point.getX() - x, 2);
        temp += Math.pow(point.getY() - y, 2);
        return Math.sqrt(temp);
    }
}

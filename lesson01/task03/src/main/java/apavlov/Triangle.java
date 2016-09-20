package apavlov;

public class Triangle implements Figure {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double distanceTo(Point pointOne, Point pointTwo) {
        double temp = Math.pow(pointTwo.getX() - pointOne.getX(), 2);
        temp += Math.pow(pointTwo.getY() - pointOne.getY(), 2);
        return Math.sqrt(temp);
    }

    public double[] longLines() {
        double[] lines = new double[3];
        lines[0] = distanceTo(a, b);
        lines[1] = distanceTo(a, c);
        lines[2] = distanceTo(c, b);
        return lines;
    }

    public double area() {
        double[] distance = longLines();
        for (double i : distance) {
            if (i == 0) {
                throw new IllegalArgumentException("The area triangle can't be calculated");
            }
        }
        double s = (distance[0] + distance[1] + distance[2]) / 2;
        double areaTriangle = Math.sqrt(s * (s - distance[0]) * (s - distance[1]) * (s - distance[2]));
        if (areaTriangle == 0) {
            throw new IllegalArgumentException("The area triangle can't be calculated");
        }
        return areaTriangle;
    }
}

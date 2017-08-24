package apavlov;

/**
 * The class Paint for print figure to console.
 *
 * @author Pavlov Artem
 * @since 23.08.2017
 */
public class Paint {
    /**
     * The method print figure to console.
     *
     * @param shape - link object Shape;
     */
    public void draw(Shape shape) {
        System.out.print(shape.pic());
    }
}

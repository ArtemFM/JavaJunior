package apavlov;

/**
 * The class Triangle for get text triangle.
 *
 * @author Pavlov Artem
 * @since 23.08.2017
 */
public class Triangle implements Shape {
    /**
     * The var weight figure.
     */
    private int size;

    /**
     * The constructor for class Triangle.
     *
     * @param size - weight figure;
     */
    public Triangle(int size) {
        this.size = size;
    }

    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(String.format(String.format("%s%s%s", "%", size - i, "s"), ""));
            sb.append(String.format(String.format("%s%s%s", "%", (i << 1) + 1, "s"), "").replaceAll(" ", "*"));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}

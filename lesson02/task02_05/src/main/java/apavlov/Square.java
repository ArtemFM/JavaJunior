package apavlov;

/**
 * The class Square for get text square.
 *
 * @author Pavlov Artem
 * @since 23.08.2017
 */
public class Square implements Shape {
    /**
     * The var weight figure.
     */
    private int size;

    /**
     * The constructor for class Square.
     *
     * @param size - weight figure;
     */
    public Square(int size) {
        this.size = size;
    }

    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(String.format(String.format("%s%s%s", "%", size * 2, "s"), " ").replaceAll(" ", "*")).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

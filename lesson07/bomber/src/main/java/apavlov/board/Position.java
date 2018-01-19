package apavlov.board;

/**
 * The class Position - use for storage position.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Position {
    /**
     * The position x.
     */
    private final int x;

    /**
     * The position y.
     */
    private final int y;

    /**
     * The getter for field x.
     *
     * @return x;
     */
    public int getX() {
        return x;
    }

    /**
     * The getter for field y.
     *
     * @return y;
     */
    public int getY() {
        return y;
    }

    /**
     * The constructor for class Position.
     *
     * @param x - coordinate x;
     * @param y - coordinate y;
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method sum two positionw (this and out parameter) and return new position.
     *
     * @param position - other position;
     * @return sum two positions;
     */
    public Position getSumPosition(Position position) {
        Position result;
        if (position == null) {
            result = new Position(this.getX(), this.getY());
        } else {
            result = new Position(this.x + position.getX(), this.y + position.getY());
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}

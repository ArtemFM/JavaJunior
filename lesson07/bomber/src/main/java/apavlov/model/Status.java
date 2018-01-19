package apavlov.model;

/**
 * The enum Status - status for cell.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public enum Status {
    /**
     * The landscape to cell.
     */
    LANDSCAPE,
    /**
     * The cell is empty.
     */
    EMPTY,
    /**
     * The unit (Player) to cell.
     */
    PLAYER,
    /**
     * The unit (Monster) to cell.
     */
    MONSTER,
    /**
     * The bomb to cell.
     */
    BOMB
}

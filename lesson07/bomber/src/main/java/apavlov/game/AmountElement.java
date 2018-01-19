package apavlov.game;

/**
 * The enum  AmountElement - use for storage constant (amount creating elements and units).
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public enum AmountElement {
    /**
     * Use for creating Stone set amount.
     */
    AMOUNT_STONE(0),
    /**
     * Use for creating Brick set amount.
     */
    AMOUNT_BRICKS(40),
    /**
     * Use for creating StrongBrick set amount.
     */
    AMOUNT_STRONG_BRICKS(10),
    /**
     * Use for creating monster Onion set amount.
     */
    AMOUNT_MONSTER_ONION(4),
    /**
     * Use for creating monster Drop set amount.
     */
    AMOUNT_MONSTER_DROP(3),
    /**
     * Use for creating monster Sponge set amount.
     */
    AMOUNT_MONSTER_SPONGE(2);

    /**
     * Amount elements or units.
     */
    private int amount;

    /**
     * The getter for field amount.
     *
     * @return field amount;
     */
    public int getAmount() {
        return amount;
    }

    /**
     * The setter for field amount.
     *
     * @param amount - amount elements or units;
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * The constructor for enum AmountElement.
     *
     * @param amount - amount elements or units;
     */
    AmountElement(int amount) {
        this.amount = amount;
    }
}

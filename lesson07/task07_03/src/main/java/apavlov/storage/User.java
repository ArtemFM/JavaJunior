package apavlov.storage;

/**
 * The class User - model for storage.
 *
 * @author Pavlov Artem
 * @since 19.12.2017
 */
public class User implements Comparable<User> {
    /**
     * The constant - count digits for generate id.
     */
    private static final int COUNT_DIGITS_ID = 10;

    /**
     * id account (user).
     */
    private final Integer id;

    /**
     * Amount money to account.
     */
    private double amount;

    /**
     * The getter for var id.
     *
     * @return id account (user);
     */
    public Integer getId() {
        return id;
    }

    /**
     * The getter for var amount.
     *
     * @return amount money;
     */
    public double getAmount() {
        return amount;
    }

    /**
     * The constructor for class User.
     *
     * @param id     - id account;
     * @param amount - amount money to account;
     */
    public User(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * The constructor for class User.
     *
     * @param amount - amount money to account;
     */
    public User(double amount) {
        this.id = generateId(COUNT_DIGITS_ID);
        this.amount = amount;
    }

    /**
     * The method generate random id.
     *
     * @param countDigits - amount digits id;
     * @return random id account;
     */
    private int generateId(int countDigits) {
        int firstDigit = (int) (Math.random() * 9) + 1;
        int tenPow = (int) Math.pow(10, countDigits - 1);
        return (int) ((firstDigit + Math.random()) * tenPow);
    }

    @Override
    public String toString() {
        return String.format("ID: %d; Amount: %.2f", this.id, this.amount);
    }

    @Override
    public int compareTo(User o) {
        return o != null ? this.id.compareTo(o.getId()) : 0;
    }
}
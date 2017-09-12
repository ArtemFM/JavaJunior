package apavlov.models;

/**
 * The class Account - model with field countMoney and requisites, for save data account user.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class Account {
    /**
     * The var - count money user`s.
     */
    private double countMoney;

    /**
     * The var - number account user`s.
     */
    private long requisites;

    /**
     * The getter for var countMoney.
     *
     * @return - count money user;
     */
    public double getCountMoney() {
        return countMoney;
    }

    /**
     * The getter for var requisites.
     *
     * @return - number account;
     */
    public long getRequisites() {
        return requisites;
    }

    /**
     * The setter for var countMoney.
     *
     * @param countMoney - count money user`s;
     */
    public void setCountMoney(double countMoney) {
        this.countMoney = countMoney;
    }

    /**
     * The setter for var requisites.
     *
     * @param requisites - number account user`s;
     */
    public void setRequisites(long requisites) {
        this.requisites = requisites;
    }

    /**
     * The constructor for class Account.
     *
     * @param countMoney - count money user`s;
     * @param requisites - number account user`s;
     */
    public Account(double countMoney, long requisites) {
        this.countMoney = countMoney;
        this.requisites = requisites;
    }
}

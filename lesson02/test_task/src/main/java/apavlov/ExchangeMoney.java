package apavlov;

import java.util.Arrays;

/**
 * The class ExchangeMoney for get combinations exchange money.
 *
 * @author Pavlov Artem
 * @since 22.09.2017
 */
public class ExchangeMoney {
    /**
     * The array for save face values coins. Array not have duplicate and sort by increase.
     */
    private int[] valueCoins = new int[0];

    /**
     * The array - count everyone coin in the combination.
     */
    private int[] countCoins;

    /**
     * The StringBuilder. For forms line for add to array.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * The getter for array valueCoins.
     *
     * @return - array values coins;
     */
    public int[] getValueCoins() {
        return valueCoins;
    }

    /**
     * The constructor for class ExchangeMoney.
     *
     * @param valueCoins - array face values coins;
     */
    public ExchangeMoney(int[] valueCoins) {
        addAllValueCoins(valueCoins);
    }

    /**
     * The method add array values coins to our array coins.
     *
     * @param values - new array face values coins;
     * @return - true - is add array; false - is not add array;
     */
    public boolean addAllValueCoins(int[] values) {
        boolean result = values != null && values.length > 0;
        int oldLength = this.valueCoins.length;
        if (result) {
            for (int value : values) {
                addValueCoins(value);
            }
        }
        return result && valueCoins.length > oldLength;
    }

    /**
     * The method add new face value coin to array coins.
     *
     * @param value - face value coin;
     * @return - true - is add; false - is not add;
     */
    public boolean addValueCoins(int value) {
        boolean result = value > 0 && !contain(value);
        if (result) {
            this.valueCoins = Arrays.copyOf(this.valueCoins, this.valueCoins.length + 1);
            this.valueCoins[this.valueCoins.length - 1] = value;
            Arrays.sort(valueCoins);
            this.countCoins = new int[this.valueCoins.length];
        }
        return result;
    }

    /**
     * The method check value on the duplicate.
     *
     * @param value - value for check;
     * @return - true - value is duplicate; false - value is no duplicate;
     */
    private boolean contain(int value) {
        boolean result = false;
        if (valueCoins.length > 0) {
            for (int v : this.valueCoins) {
                if (v == value) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * The method return all combinations exchange money.
     *
     * @param sum - sum money;
     * @return - all combinations exchange;
     */
    public String[] getExchangeMoney(int sum) {
        boolean actual = this.valueCoins.length > 0 && sum > 0;
        return actual ? exchange(this.valueCoins.length - 1, sum, null) : new String[]{};
    }

    /**
     * The recursion method for search combination exchange.
     *
     * @param index - index for array values coins and array count coins.
     * @param sum   - sum money;
     * @param array - array for save combinations exchange;
     * @return - array with save new combinations;
     */
    private String[] exchange(int index, int sum, String[] array) {
        if (sum == 0) {
            array = addNewCombination(array);
        } else {
            if (sum >= this.valueCoins[index]) {
                this.countCoins[index]++;
                array = exchange(index, sum - this.valueCoins[index], array);
                this.countCoins[index]--;
            }
            if (index > 0) {
                array = exchange(index - 1, sum, array);
            }
        }
        return array;
    }

    /**
     * The method add new combination to array.
     *
     * @param array - array for add combination;
     * @return - array with add combination;
     */
    private String[] addNewCombination(String[] array) {
        sb.delete(0, sb.length());
        array = array == null ? new String[1] : Arrays.copyOf(array, array.length + 1);
        for (int i = this.countCoins.length - 1; i >= 0; i--) {
            if (this.countCoins[i] != 0) {
                sb.append(String.format("[%sx%s rub]", this.countCoins[i], this.valueCoins[i]));
            }
        }
        array[array.length - 1] = sb.toString();
        return array;
    }

    /**
     * The method print to console array combinations.
     *
     * @param combinations - array combinations;
     */
    public void printCombinations(String[] combinations) {
        System.out.printf("Face values coins: %s%s", Arrays.toString(valueCoins), System.lineSeparator());
        if (combinations != null && combinations.length > 0) {
            for (String value : combinations) {
                System.out.println(value);
            }
        }
        System.out.printf("Count combinations: %s", combinations != null ? combinations.length : 0);
    }
}

package apavlov;

import java.util.Iterator;

/**
 * The class IteratorPrimeArray - iterator get only prime number to array.
 *
 * @author Pavlov Artem
 * @since 14.09.2017
 */
public class IteratorPrimeArray implements Iterable, Iterator {
    /**
     * The array values.
     */
    private int[] values;

    /**
     * The var - position to array now.
     */
    private int cursor = getNextIndexPrimeNumber();

    /**
     * The constructor for class`s IteratorPrimeArray.
     *
     * @param values - array values for work;
     */
    public IteratorPrimeArray(int[] values) {
        this.values = values;
    }

    /**
     * The method get next index to array with prime number or -1.
     *
     * @return index array with prime number or -1;
     */
    private int getNextIndexPrimeNumber() {
        int result = -1;
        if (this.values != null && this.values.length > 0 && cursor + 1 < this.values.length) {
            for (int i = cursor + 1; i < this.values.length; i++) {
                if (checkIsPrime(this.values[i])) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * The method chek number on the prime or composite.
     *
     * @param number - number for check;
     * @return false number is composite; true - number is prime;
     */
    private boolean checkIsPrime(int number) {
        boolean result = false;
        if (number % 2 != 0 && number > 1) {
            result = true;
            for (int i = 3; i <= (int) Math.sqrt(number); i = i + 2) {
                if (number % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return cursor != -1;
    }

    @Override
    public Object next() {
        Object result = this.values[cursor];
        this.cursor = getNextIndexPrimeNumber();
        return result;
    }
}

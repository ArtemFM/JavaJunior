package apavlov;

import java.util.Iterator;

/**
 * The class IteratorEvenInt - iterator get only even number to array.
 *
 * @author Pavlov Artem
 * @since 14.09.2017
 */
public class IteratorEvenInt implements Iterable, Iterator {
    /**
     * The array values.
     */
    private int[] values;

    /**
     * The var - position cursor to array now.
     */
    private int cursor = getNextIndexEvenNumber();

    /**
     * The constructor for class IteratorEvenInt.
     *
     * @param values - array value;
     */
    public IteratorEvenInt(int[] values) {
        this.values = values;
    }

    /**
     * The method return next index array with even number or -1.
     *
     * @return -1 - number is no or index array with even number;
     */
    private int getNextIndexEvenNumber() {
        int result = -1;
        if (this.values != null && this.values.length > 0 && this.cursor + 1 < this.values.length) {
            for (int i = this.cursor + 1; i < this.values.length; i++) {
                if (this.values[i] % 2 == 0) {
                    result = i;
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
        Object result = this.values[this.cursor];
        this.cursor = getNextIndexEvenNumber();
        return result;
    }
}

package apavlov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class SimpleSet - realizes logic collection type Set. The method save is array.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 27.09.2017
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * The constant is default length array.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * The array for save values.
     */
    private Object[] array;

    /**
     * The var - count values to array.
     */
    private int size;

    /**
     * The constructor for class SimpleSet with set length array.
     *
     * @param length - user length array;
     */
    public SimpleSet(int length) {
        this.array = length >= 0 ? new Object[length] : new Object[DEFAULT_SIZE];
    }

    /**
     * The default constructor for class SimpleSet.
     */
    public SimpleSet() {
        this(DEFAULT_SIZE);
    }

    /**
     * The constructor for class SimpleSet for add other array to our collection.
     *
     * @param values - array for addition;
     */
    public SimpleSet(E[] values) {
        this();
        addAll(values);
    }

    /**
     * The method return count values to array.
     *
     * @return - count values;
     */
    public int size() {
        return this.size;
    }

    /**
     * The private method dynamic increase array size, if values equal length array.
     */
    private void increaseDynamicSize() {
        if (this.array.length == size) {
            this.array = Arrays.copyOf(this.array, this.array.length * 3 >> 1);
        }
    }

    /**
     * The private method for get index to array by value.
     *
     * @param value - value for search;
     * @return -1 - if value is not found or return index value to array;
     */
    private int getIndexElement(E value) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (value == this.array[i] || (this.array[i] != null && this.array[i].equals(value))) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * The method add new element (value) to array.
     *
     * @param value - value for add;
     */
    public void add(E value) {
        int index = getIndexElement(value);
        if (index == -1) {
            increaseDynamicSize();
            this.array[size++] = value;
        } else {
            this.array[index] = value;
        }
    }

    /**
     * The method addition array to our collection.
     *
     * @param values - array for addition;
     */
    public void addAll(E[] values) {
        if (values != null) {
            for (E value : values) {
                add(value);
            }
        }
    }

    /**
     * The method return array values type Object.
     *
     * @return - array values type Object;
     */
    public Object[] toArray() {
        return Arrays.copyOf(this.array, size);
    }

    /**
     * The method return array in the view String.
     *
     * @return - array to String;
     */
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorSet<>();
    }

    /**
     * The class IteratorSet implements Iterator.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 27.09.2017
     */
    private class IteratorSet<E> implements Iterator<E> {
        /**
         * The var - position to array.
         */
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The next element is not found...");
            }
            return (E) array[cursor++];
        }
    }
}

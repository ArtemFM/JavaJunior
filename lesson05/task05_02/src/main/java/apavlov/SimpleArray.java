package apavlov;

import java.util.Arrays;
import java.util.Iterator;

/**
 * The class SimpleArray - enrollment methods for work with array.
 *
 * @param <T> This describes my type parameter
 *
 * @author Pavlov Artem
 * @since 17.09.2017
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * The var-constant default size array.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * The var - size array user`s.
     */
    private Integer userLength;

    /**
     * The array values.
     */
    private Object[] value;

    /**
     * The var - count data (values) to array.
     */
    private int countElements;

    /**
     * The default constructor for class SimpleArray.
     */
    public SimpleArray() {
        this.value = new Object[DEFAULT_SIZE];
    }

    /**
     * The constructor with set length for class`s SimpleArray.
     *
     * @param length - size array;
     */
    public SimpleArray(int length) {
        this.userLength = length;
        this.value = new Object[length];
    }

    /**
     * The method return count values to array.
     *
     * @return - count values;
     */
    public int size() {
        return this.countElements;
    }

    /**
     * The method check array on the empty.
     *
     * @return true - if array is empty; false - is array is not empty;
     */
    public boolean isEmpty() {
        return countElements == 0;
    }

    /**
     * The method delete all values and create new array default length or user length.
     */
    public void clear() {
        this.value = new Object[userLength != null ? userLength : DEFAULT_SIZE];
        countElements = 0;
    }

    /**
     * The method return values to view array type Object.
     *
     * @return array Object;
     */
    public Object[] toArray() {
        return Arrays.copyOf(this.value, countElements);
    }

    /**
     * The method return values to view String.
     *
     * @return - values to view String;
     */
    public String toString() {
        return Arrays.toString(toArray());
    }

    /**
     * The method auto resize array.
     */
    private void getDynamicExpansion() {
        if (countElements == this.value.length) {
            this.value = Arrays.copyOf(this.value, this.value.length < DEFAULT_SIZE ? DEFAULT_SIZE : this.value.length * 3 >> 1);
        }
    }

    /**
     * The private method check index equal range array.
     *
     * @param index - index for check;
     * @return - true - index is range; false - index is not range;
     */
    private boolean checkRangeArray(int index) {
        return index >= 0 && index < countElements;
    }

    /**
     * The method add new value to array.
     *
     * @param value - value;
     */
    public void add(T value) {
        getDynamicExpansion();
        this.value[countElements++] = value;
    }

    /**
     * The method add new value by index.
     *
     * @param index - index for add new value;
     * @param value - value;
     * @return true - if add new element; false - if is not add new element;
     */
    public boolean add(int index, T value) {
        boolean result = false;
        if (checkRangeArray(index) || countElements == index) {
            getDynamicExpansion();
            System.arraycopy(this.value, index, this.value, index + 1, countElements - index);
            this.value[index] = value;
            countElements++;
            result = true;
        }
        return result;
    }

    /**
     * The private method for get index value.
     *
     * @param value - value for search;
     * @return if element is not found -1 or index element`s to array;
     */
    private int get(T value) {
        int result = -1;
        for (int i = 0; i < countElements; i++) {
            if (this.value[i].equals(value)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * The method return value by index.
     *
     * @param index - index;
     * @return - value or throw out exception;
     */
    public T get(int index) {
        Object result = null;
        if (checkRangeArray(index)) {
            result = this.value[index];
        } else {
            throw new IndexOutOfBoundsException(String.format("Index %s is not found...", index));
        }
        return (T) result;
    }

    /**
     * The method delete element by index.
     *
     * @param index - index for delete to array;
     * @return true - is delete element; false - is not delete element;
     */
    public boolean delete(int index) {
        boolean result = false;
        if (checkRangeArray(index)) {
            System.arraycopy(this.value, index + 1, this.value, index, countElements - index - 1);
            this.value[countElements--] = null;
            result = true;
        }
        return result;
    }

    /**
     * The method delete element by value.
     *
     * @param value - value;
     * @return true - element is found and deleting; false - element is not found;
     */
    public boolean delete(T value) {
        return delete(get(value));
    }

    /**
     * The method edit value on the new value by index.
     *
     * @param index - index for edit;
     * @param value - new value;
     * @return - old value or throw out exception? if index is not range array;
     */
    public T update(int index, T value) {
       Object oldValue = null;
       if (checkRangeArray(index)) {
           oldValue = this.value[index];
           this.value[index] = value;
       } else {
           throw new IndexOutOfBoundsException(String.format("Index %s is not found...", index));
       }
       return (T) oldValue;
    }

    /**
     * The method return iterator.
     *
     * @return iterator for class;
     */
    public Iterator<T> iterator() {
        return new IteratorArray<>();
    }

    /**
     * The inner class IteratorArray - iterator for class SimpleArray.
     *
     * @param <T> This describes my type parameter
     *
     * @author Pavlov Artem
     * @since 17.09.2017
     */
    private class IteratorArray<T> implements Iterator<T> {
        /**
         * The var - position iterator`s to array.
         */
        private int cursorIterator;

        @Override
        public boolean hasNext() {
            return cursorIterator < countElements;
        }

        @Override
        public T next() {
            return (T) value[cursorIterator++];
        }
    }
}

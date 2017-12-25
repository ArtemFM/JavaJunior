package apavlov.collections;

import apavlov.list.MyList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class MyArrayList - enrollment methods for work with array.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 20.12.2017
 */
@ThreadSafe
public class MyArrayList<E> implements MyList<E> {
    /**
     * The var-constant default size array.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * The var - size array user`s.
     */
    private int userLength = -1;

    /**
     * The array values.
     */
    @GuardedBy("this")
    private Object[] value;

    /**
     * The var - count data (values) to array.
     */
    @GuardedBy("this")
    private int countElements;

    /**
     * The default constructor for class MyArrayList.
     */
    public MyArrayList() {
        this.value = new Object[DEFAULT_SIZE];
    }

    /**
     * The constructor with set length for class`s MyArrayList.
     *
     * @param length - size array;
     */
    public MyArrayList(int length) {
        this.userLength = length;
        this.value = new Object[length];
    }

    @Override
    public int size() {
        synchronized (this) {
            return this.countElements;
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return countElements == 0;
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            this.value = new Object[userLength >= 0 ? userLength : DEFAULT_SIZE];
            countElements = 0;
        }
    }

    @Override
    public Object[] toArray() {
        synchronized (this) {
            return Arrays.copyOf(this.value, countElements);
        }
    }

    @Override
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

    @Override
    public void add(E value) {
        synchronized (this) {
            getDynamicExpansion();
            this.value[countElements++] = value;
        }
    }

    @Override
    public boolean add(int index, E value) {
        boolean result = false;
        synchronized (this) {
            if (checkRangeArray(index) || countElements == index) {
                getDynamicExpansion();
                System.arraycopy(this.value, index, this.value, index + 1, countElements - index);
                this.value[index] = value;
                countElements++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean addAll(E[] values) {
        boolean result = values != null && values.length != 0;
        if (result) {
            for (E value : values) {
                add(value);
            }
        }
        return result;
    }

    @Override
    public int get(E value) {
        int result = -1;
        synchronized (this) {
            for (int i = 0; i < countElements; i++) {
                if (this.value[i].equals(value)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public E get(int index) {
        E result;
        synchronized (this) {
            if (checkRangeArray(index)) {
                result = (E) this.value[index];
            } else {
                throw new IndexOutOfBoundsException(String.format("Index %s is not found...", index));
            }
        }
        return result;
    }

    @Override
    public boolean delete(int index) {
        boolean result = false;
        synchronized (this) {
            if (checkRangeArray(index)) {
                System.arraycopy(this.value, index + 1, this.value, index, countElements - index - 1);
                this.value[--countElements] = null;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(E value) {
        return delete(get(value));
    }

    @Override
    public E update(int index, E value) {
        Object oldValue;
        synchronized (this) {
            if (checkRangeArray(index)) {
                oldValue = this.value[index];
                this.value[index] = value;
            } else {
                throw new IndexOutOfBoundsException(String.format("Index %s is not found...", index));
            }
        }
        return (E) oldValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorArray<>();
    }

    /**
     * The inner class IteratorArray - iterator for class SimpleArray.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 20.12.2017
     */
    private class IteratorArray<E> implements Iterator<E> {
        /**
         * The var - position iterator`s to array.
         */
        private int cursorIterator;

        @Override
        public boolean hasNext() {
            return cursorIterator < countElements;
        }

        @Override
        public E next() {
            if (cursorIterator == countElements) {
                throw new NoSuchElementException("The element is not found...");
            }
            return (E) value[cursorIterator++];
        }
    }
}

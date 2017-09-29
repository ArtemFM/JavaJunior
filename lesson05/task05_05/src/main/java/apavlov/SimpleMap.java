package apavlov;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class SimpleMap - use for storage key and value.
 *
 * @param <T> This describes my type parameter. Type for key;
 * @param <V> This describes my type parameter. Type for value;
 * @author Pavlov Artem
 * @since 29.09.2017
 */
public class SimpleMap<T, V> implements Iterable<SimpleMap.Entry> {
    /**
     * The constant for check filling array.
     */
    private static final float MAX_FILLING = 0.8f;

    /**
     * The default length array.
     */
    private static final int DEFAULT_LENGTH = 16;

    /**
     * object StringBuilder for work to string.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * The array for storage values type Node.
     */
    private Node<T, V>[] array;

    /**
     * The count elements to array now.
     */
    private int size;

    /**
     * The default constructor for class SimpleMap.
     */
    public SimpleMap() {
        this(DEFAULT_LENGTH);
    }

    /**
     * Theconstructor for class SimpleMap.
     *
     * @param length - user length array;
     */
    public SimpleMap(int length) {
        this.array = length > 0 ? new Node[length] : new Node[this.DEFAULT_LENGTH];
    }

    /**
     * The method dynamic increase array and rewrite.
     */
    private void increaseDynamicArray() {
        if (size != 0 && (float) size / this.array.length > this.MAX_FILLING) {
            Node<T, V>[] newArray = new Node[this.array.length << 1];
            for (Node<T, V> element : this.array) {
                if (element != null) {
                    insert(element.key, element.value);
                }
            }
            this.array = newArray;
        }
    }

    /**
     * The method return number equals count elements to array.
     *
     * @return - count elements;
     */
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        this.sb.delete(0, sb.length());
        sb.append("{");
        int index = 0;
        for (Node<T, V> element : this.array) {
            if (element != null) {
                sb.append(element.toString());
                if (++index != this.size) {
                    sb.append(", ");
                }
            }
        }
        return sb.append("}").toString();
    }

    /**
     * The method get index for write element to array.
     *
     * @param key - key;
     * @return - index array;
     */
    private int getIndex(T key) {
        return key == null ? 0 : key.hashCode() % this.array.length;
    }

    /**
     * The method add new element to collection.
     *
     * @param key   - key;
     * @param value - value;
     * @return true - is add; false - is not add;
     */
    public boolean insert(T key, V value) {
        boolean result;
        int index = getIndex(key);
        if (result = this.array[index] == null) {
            increaseDynamicArray();
            this.array[index] = new Node<>(key, value);
            this.size++;
        } else if (result = !equals(key, this.array[index].key)) {
            this.array[index].value = value;
        }
        return result;
    }

    /**
     * The method check first key is equal second key.
     *
     * @param keyFirst  - first key;
     * @param keySecond - second key;
     * @return true - is equal; false - is not equal;
     */
    private boolean equals(T keyFirst, T keySecond) {
        return (keyFirst == null && keySecond == null) || (keyFirst != null && keySecond != null && (keyFirst.hashCode() == keySecond.hashCode() || keyFirst.equals(keySecond)));
    }

    /**
     * The method return value by key or if not search, return null.
     *
     * @param key key;
     * @return value;
     */
    public V get(T key) {
        int index = getIndex(key);
        return this.array[index] != null ? this.array[index].value : null;
    }

    /**
     * The method delete element to array by key.
     *
     * @param key - key;
     * @return true - is delete; false - is not delete;
     */
    public boolean delete(T key) {
        int index = getIndex(key);
        boolean result;
        if (result = this.array[index] != null) {
            this.array[index] = null;
            this.size--;
        }
        return result;
    }

    @Override
    public Iterator<SimpleMap.Entry> iterator() {
        return new IteratorMap();
    }

    /**
     * The inner class Node - model element.
     *
     * @param <T> This describes my type parameter. Type for key;
     * @param <V> This describes my type parameter. Type for value;
     * @author Pavlov Artem
     * @since 28.09.2017
     */
    private class Node<T, V> {
        /**
         * The var - key.
         */
        private T key;

        /**
         * The var - value.
         */
        private V value;

        /**
         * The constructor for inner class Node.
         *
         * @param key   - key;
         * @param value - value;
         */
        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", this.key, this.value);
        }
    }

    /**
     * The inner class Node - model element.
     *
     * @author Pavlov Artem
     * @since 29.09.2017
     */
    public class Entry {
        /**
         * The key.
         */
        private T key;

        /**
         * The value.
         */
        private V value;

        /**
         * The getter for key.
         *
         * @return key;
         */
        public T getKey() {
            return key;
        }

        /**
         * The getter for value.
         *
         * @return value;
         */
        public V getValue() {
            return value;
        }

        /**
         * The default constructor for class Entry.
         */
        public Entry() {
        }

        /**
         * The constructor for class Entry.
         *
         * @param key   - key;
         * @param value - value;
         */
        public Entry(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * The inner class IteratorMap - realizes iterator for class SimpleMap.
     *
     * @author Pavlov Artem
     * @since 29.09.2017
     */
    private class IteratorMap implements Iterator<SimpleMap.Entry> {
        /**
         * The position iterator to array.
         */
        private Integer cursor = getNextElement();

        /**
         * The method return index next element to array.
         *
         * @return - index array;
         */
        private int getNextElement() {
            int index = cursor == null ? 0 : this.cursor + 1;
            while (index < size && array[index] == null) {
                index++;
            }
            return index;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public SimpleMap.Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The element is not found...");
            }
            Entry element = new Entry(array[this.cursor].key, array[this.cursor].value);
            this.cursor = getNextElement();
            return element;
        }
    }
}

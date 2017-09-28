package apavlov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class SimpleHashSet - realizes logic collection type HashTable.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 28.09.2017
 */
public class SimpleHashSet<E> implements Iterable<E> {
    /**
     * The constant is default size array.
     */
    private static final int DEFAULT_SIZE = 16;

    /**
     * The constant is max size linked.
     */
    private static final int MAX_SIZE_LINKED = DEFAULT_SIZE >> 2;

    /**
     * The constant is text exception NoSuchElementException.
     */
    private static final String MSG_NO_SUCH = "The next element is not found...";

    /**
     * The array for storage list type linked.
     */
    private IndexLinked[] list;

    /**
     * The count values to collection.
     */
    private int size;

    /**
     * The default constructor for class SimpleHashSet.
     */
    public SimpleHashSet() {
        this(DEFAULT_SIZE);
    }

    /**
     * The constructor for class SimpleHashSet with chosen size.
     *
     * @param length - user size;
     */
    public SimpleHashSet(int length) {
        this.list = length > 0 ? new IndexLinked[length] : new IndexLinked[DEFAULT_SIZE];
    }

    /**
     * The constructor for class SimpleHashSet with opportunity addition array.
     *
     * @param collections - array for addition;
     */
    public SimpleHashSet(E[] collections) {
        this();
        addAll(collections);
    }

    /**
     * The method return count values to collection.
     *
     * @return - count values;
     */
    public int size() {
        return this.size;
    }

    /**
     * The private method for get index for save value.
     *
     * @param value - value;
     * @return - index;
     */
    private int getIndex(E value) {
        return value == null ? 0 : Math.abs(value.hashCode() % this.list.length);
    }

    /**
     * The private method for auto increase length array and rewriting values.
     *
     * @param index - index for check;
     */
    private void increaseDynamicSize(int index) {
        if (this.list[index] != null && this.list[index].countElement > this.MAX_SIZE_LINKED) {
            IndexLinked[] newList = new IndexLinked[this.list.length << 1];
            this.size = 0;
            for (E value : this) {
                add(value, newList);
            }
            this.list = newList;
        }
    }

    /**
     * The method add new value to collection.
     *
     * @param value - value;
     */
    public void add(E value) {
        increaseDynamicSize(getIndex(value));
        add(value, this.list);
    }

    /**
     * The private method add new value to any collection type IndexLinked.
     *
     * @param value - value;
     * @param collection - cillection type IndexLinked;
     */
    private void add(E value, IndexLinked[] collection) {
        int index = getIndex(value);
        if (collection[index] == null) {
            collection[index] = new IndexLinked();
        }
        collection[index].add(value);
    }

    /**
     * The method add array to collection.
     *
     * @param values - array for addition;
     */
    public void addAll(E[] values) {
        if (values != null && values.length > 0) {
            for (E value : values) {
                add(value);
            }
        }
    }

    /**
     * The method return array values collection.
     *
     * @return - array values;
     */
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        int index = 0;
        for (E value : this) {
            array[index++] = value;
        }
        return array;
    }

    /**
     * The method return values collection in the view String.
     *
     * @return - collection to String;
     */
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorHashSet<>();
    }

    /**
     * The inner class IndexLinked - realizes logic linked list.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 28.09.2017
     */
    private class IndexLinked<E> implements Iterable<E> {
        /**
         * The link to first element.
         */
        private Node<E> first;

        /**
         * The link to last element.
         */
        private Node<E> last;

        /**
         * The count elements to this linked list.
         */
        private int countElement;

        /**
         * The method return link element`s by value.
         *
         * @param value - value;
         * @return - link element;
         */
        private Node<E> getLinkElement(E value) {
            Node<E> result = null;
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.value == value || (x.value != null && x.value.equals(value))) {
                    result = x;
                    break;
                }
            }
            return result;
        }

        /**
         * The method add new value to this linkde list.
         *
         * @param value - value;
         */
        private void add(E value) {
            Node<E> element = getLinkElement(value);
            if (element != null) {
                element.value = value;
            } else {
                if (this.first == null) {
                    this.first = new Node<>(null, null, value);
                } else {
                    Node<E> prevElement = this.last == null ? this.first : this.last;
                    prevElement.next = this.last = new Node<>(prevElement, null, value);
                }
                this.countElement++;
                size++;
            }
        }

        @Override
        public Iterator<E> iterator() {
            return new IteratorLinked<>();
        }

        /**
         * The inner class IteratorLinked - realizes iterator for class IndexLinked.
         *
         * @param <E> This describes my type parameter
         * @author Pavlov Artem
         * @since 28.09.2017
         */
        private class IteratorLinked<E> implements Iterator<E> {
            /**
             * The position iterator`s.
             */
            private Node<E> cursor = (Node<E>) first;

            @Override
            public boolean hasNext() {
                return this.cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(MSG_NO_SUCH);
                }
                Node<E> element = this.cursor;
                this.cursor = cursor.next;
                return element.value;
            }
        }
    }

    /**
     * The inner class Node - model element.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 28.09.2017
     */
    private class Node<E> {
        /**
         * The link previous element.
         */
        private Node<E> prev;

        /**
         * The link next element.
         */
        private Node<E> next;

        /**
         * The value.
         */
        private E value;

        /**
         * The constructor for class Node.
         *
         * @param prev - previous link;
         * @param next - next link;
         * @param value - value;
         */
        Node(Node<E> prev, Node<E> next, E value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    /**
     * The inner class IteratorHashSet - realizes iterator for class SimpleHashSet.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 28.09.2017
     */
    private class IteratorHashSet<E> implements Iterator<E> {
        /**
         * The link to iterator class IndexLinked.
         */
        private Iterator<E> positionIterator = getNextIterator();

        /**
         * The position to array.
         */
        private int position;

        /**
         * The method for search next iterator to collection.
         *
         * @return - iterator class`s IndexLinked;
         */
        private Iterator<E> getNextIterator() {
            Iterator<E> result = null;
            while (this.position < list.length && result == null) {
                if (list[this.position] != null) {
                    result = list[this.position].iterator();
                }
                this.position++;
            }
            return result;
        }

        @Override
        public boolean hasNext() {
            return positionIterator != null && positionIterator.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(MSG_NO_SUCH);
            }
            E result = positionIterator.next();
            if (!positionIterator.hasNext()) {
                positionIterator = getNextIterator();
            }
            return result;
        }
    }
}

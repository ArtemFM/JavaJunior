package apavlov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class SimpleLinkedSet - realizes logic collection type Set. The method save in to linked list.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 27.09.2017
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    /**
     * The link to first element.
     */
    private Node<E> first;

    /**
     * The link to last element.
     */
    private Node<E> last;

    /**
     * The var - count values to array.
     */
    private int size;

    /**
     * The default constructor for class SimpleLinkedSet.
     */
    public SimpleLinkedSet() {
    }

    /**
     * The constructor for class SimpleLinkedSet for add other array to our collection.
     *
     * @param values - array for addition;
     */
    public SimpleLinkedSet(E[] values) {
        addAll(values);
    }

    /**
     * The method return count values to collection.
     *
     * @return - count values;
     */
    public int size() {
        return size;
    }

    /**
     * The method return link on the element by value or null, if link is not found.
     *
     * @param value - value for search;
     * @return - link element or null;
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
     * The method add new element (value) to collection.
     *
     * @param value - value for add;
     */
    public void add(E value) {
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
            this.size++;
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
        Object[] values = new Object[size];
        int index = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            values[index++] = x.value;
        }
        return values;
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
     * The inner class Node - object for save values user and links.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 27.09.2017
     */
    private class Node<E> {
        /**
         * The link to next element type Node.
         */
        private Node<E> next;

        /**
         * The link to previous element type Node.
         */
        private Node<E> prev;

        /**
         * The value.
         */
        private E value;

        /**
         * The constructor for inner class Node.
         *
         * @param prev - link to previous element;
         * @param next - link to next element;
         * @param value - value;
         */
        Node(Node<E> prev, Node<E> next, E value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }

    /**
     * The inner class IteratorSet implements Iterator.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 27.09.2017
     */
    private class IteratorSet<E> implements Iterator<E> {
        /**
         * The position (link) in collection.
         */
        private Node<E> cursor = (Node<E>) first;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The next element is not found...");
            }
            Node<E> element = cursor;
            cursor = cursor.next;
            return element.value;
        }
    }
}

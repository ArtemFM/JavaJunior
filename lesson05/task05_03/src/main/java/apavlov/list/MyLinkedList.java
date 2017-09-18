package apavlov.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class MyLinkedList - enrollment methods for work with link list.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 18.09.2017
 */
public class MyLinkedList<E> implements MyList<E> {
    /**
     * The var - text exception.
     */
    private String msgException = "The element is not found...";

    /**
     * The var - count elements to list.
     */
    private int size;

    /**
     * The var - link first Node.
     */
    private Node first;

    /**
     * The var - link last Node.
     */
    private Node last;

    /**
     * The default constructor for class MyLinkedList.
     */
    public MyLinkedList() {
    }

    /**
     * The constructor for class MyLinkedList. Add array to list.
     *
     * @param values - array for add;
     */
    public MyLinkedList(E[] values) {
        this.addAll(values);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[this.size];
        int index = 0;
        for (Node link = first; link != null; link = link.next) {
            resultArray[index++] = link.value;
        }
        return resultArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public void add(E value) {
        if (this.first == null) {
            first = new Node(null, null, value);
        } else {
            Node prevElement = this.last == null ? this.first : this.last;
            this.last = new Node(prevElement, null, value);
            prevElement.next = this.last;
        }
        this.size++;
    }

    /**
     * The private method check index equal range array.
     *
     * @param index - index for check;
     * @return - true - index is range; false - index is not range;
     */
    private boolean checkIndexToRange(int index) {
        return index >= 0 && index < this.size;
    }

    /**
     * The method get link Node by index.
     *
     * @param index - index for search;
     * @return - link Node;
     */
    private Node getLinkByIndex(int index) {
        Node result;
        if (this.size >> 1 >= index) {
            result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int i = this.size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }

    @Override
    public boolean add(int index, E value) {
        boolean result = true;
        if (index == this.size) {
            add(value);
        } else if (checkIndexToRange(index)) {
            Node oldElement = getLinkByIndex(index);
            Node newElement = new Node(oldElement.prev, oldElement, value);
            if (oldElement.prev == null) {
                this.first = newElement;
                this.last = oldElement;
            } else {
                oldElement.prev.next = newElement;
                oldElement.prev = newElement;
            }
            this.size++;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean addAll(E[] values) {
        boolean result = values != null && values.length > 0;
        if (result) {
            for (E value : values) {
                add(value);
            }
        }
        return result;
    }

    /**
     * The method return link Node by value.
     *
     * @param value - value;
     * @return - link Node;
     */
    private Node getLinkByValue(E value) {
        Node result = null;
        for (Node element = first; element != null; element = element.next) {
            if (element.value.equals(value)) {
                result = element;
                break;
            }
        }
        return result;
    }

    @Override
    public int get(E value) {
        int result = -1;
        int index = 0;
        for (Node node = first; node != null; node = node.next) {
            if (node.value.equals(value)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public E get(int index) {
        E result;
        if (checkIndexToRange(index)) {
            result = getLinkByIndex(index).value;
        } else {
            throw new NoSuchElementException(this.msgException);
        }
        return result;
    }

    /**
     * The method delete Node by link.
     *
     * @param node - link Node;
     * @return true - is delete; false - is not delete;
     */
    private boolean deleteByLink(Node node) {
        boolean result = node != null;
        if (result) {
            if (node.next == null && node.prev == null) {
                first = null;
                last = null;
            } else if (node.prev == null) {
                first = node.next;
                first.prev = null;
            } else if (node.next == null) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
        return result;
    }

    @Override
    public boolean delete(int index) {
        boolean result = checkIndexToRange(index);
        if (result) {
            result = deleteByLink(getLinkByIndex(index));
        }
        return result;
    }

    @Override
    public boolean delete(E value) {
        return deleteByLink(getLinkByValue(value));
    }

    @Override
    public E update(int index, E value) {
        E result;
        if (checkIndexToRange(index)) {
            Node temp = getLinkByIndex(index);
            result = temp.value;
            temp.value = value;
        } else {
            throw new NoSuchElementException(this.msgException);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorLinked();
    }

    /**
     * The inner class Node - object for save values user and links.
     *
     * @author Pavlov Artem
     * @since 18.09.2017
     */
    private class Node {
        /**
         * The var - link to previous element Node.
         */
        private Node prev;

        /**
         * The var - link to next element Node.
         */
        private Node next;

        /**
         * The var - value to list.
         */
        private E value;

        /**
         * The constructor for class Node.
         *
         * @param prev  - link previous element;
         * @param next  - link next element;
         * @param value - value;
         */
        Node(Node prev, Node next, E value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    /**
     * The inner class IteratorLinked - iterator for class MyLinkedList.
     *
     * @author Pavlov Artem
     * @since 18.09.2017
     */
    private class IteratorLinked implements Iterator<E> {
        /**
         * The var - cursor by linked list.
         */
        private Node cursor = first;

        @Override
        public boolean hasNext() {
            return this.cursor != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(msgException);
            }
            E result = cursor.value;
            this.cursor = this.cursor.next;
            return result;
        }
    }
}

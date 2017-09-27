package apavlov.list;

/**
 * The class Cycle - check linked list on the vicious circle.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 27.09.2017
 */
public class Cycle<E> {
    /**
     * The method create new linked list is cycle or is not cycle.
     *
     * @param size       - size linked list;
     * @param indexCycle - index for cycle; if index < 0 or index >= size - list is not cycle;
     * @param fillValues - value for fill list;
     * @return - link first element;
     */
    public Node<E> createLinkList(int size, int indexCycle, E fillValues) {
        Node<E> first = size > 0 ? new Node<>(fillValues) : null;
        if (first != null) {
            Node<E> last = first;
            Node<E> temp = indexCycle == 0 ? first : null;
            for (int i = 1; i < size; i++) {
                last = last.next = new Node<>(fillValues);
                if (i == indexCycle) {
                    temp = last;
                }
            }
            if (temp != null) {
                last.next = temp;
            }
        }
        return first;
    }

    /**
     * The method check is cycle list or is not cycle.
     *
     * @param first - link first element list`s;
     * @return - true - is cycle; false - is not cycle;
     */
    public boolean hasCycle(Node<E> first) {
        Node<E> x = null;
        Node<E> y = null;
        if (first != null) {
            for (x = first, y = first.next; x != null && y != null && y.next != null && !x.equals(y); x = x.next, y = y.next.next) {
            }
        }
        return x != null && y != null && x.equals(y);
    }

    /**
     * The inner class Node - use for save link to next element type Node and value.
     *
     * @param <E> This describes my type parameter
     * @author Pavlov Artem
     * @since 27.09.2017
     */
    private class Node<E> {
        /**
         * The var - link on the next element.
         */
        private Node<E> next;

        /**
         * The var - value.
         */
        private E value;

        /**
         * The constructor for inner class Node.
         *
         * @param value - value;
         */
        Node(E value) {
            this.value = value;
        }
    }
}

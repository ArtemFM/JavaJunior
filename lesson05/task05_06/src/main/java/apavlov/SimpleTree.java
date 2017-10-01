package apavlov;

/**
 * The interface SimpleTree.
 *
 * @param <E> This describes my type parameter. Type for key;
 * @author Pavlov Artem
 * @since 01.10.2017
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * The method return count values to tree.
     *
     * @return - count values to tree;
     */
    int size();

    /**
     * The method check tree is empty or is not empty.
     *
     * @return true - tree is empty; false - tree is not empty;
     */
    boolean isEmpty();

    /**
     * The method add new value (child) by parent.
     *
     * @param parent - parent;
     * @param child - child;
     * @return true - is add value to tree; false - is not add value to tree;
     */
    boolean add(E parent, E child);

    /**
     * The method return all values to array type Object.
     *
     * @return - array values;
     */
    Object[] toArray();

    /**
     * The method return all values to array type use tree.
     *
     * @param elements - type use tree;
     * @return - array values;
     */
    E[] toArray(E[] elements);
}

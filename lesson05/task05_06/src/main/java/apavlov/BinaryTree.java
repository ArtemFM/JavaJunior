package apavlov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The class Tree - use for storage value to tree.
 *
 * @param <E> This describes my type parameter. Type for key;
 * @author Pavlov Artem
 * @since 02.10.2017
 */
public class BinaryTree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * The head tree.
     */
    private Node<E> root;

    /**
     * The count value to tree.
     */
    private int size;

    /**
     * The constructor for class BinaryTree for add array values.
     *
     * @param values - array values;
     */
    public BinaryTree(E[] values) {
        addAll(values);
    }

    /**
     * The default constructor for class BinaryTree.
     */
    public BinaryTree() {
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * The method add new value to binary tree.
     *
     * @param value - value;
     * @return true - is add; false - is not add;
     */
    public boolean add(E value) {
        boolean result;
        if (result = size == 0 && value != null) {
            root = new Node<>(value);
            this.size++;
        } else {
            Node<E> node = getLinkPlaceWrite(value);
            if (result = node != null) {
                node.value = value;
                this.size++;
            }
        }
        return result;
    }

    /**
     * The method add array values to binary tree.
     *
     * @param values - array values;
     */
    public void addAll(E[] values) {
        if (values != null && values.length > 0) {
            for (E value : values) {
                add(value);
            }
        }
    }

    @Override
    public boolean add(E parent, E child) {
        throw new UnsupportedOperationException("This operation is not use!");
    }

    /**
     * The recurse method for get link Node by value.
     *
     * @param value - value for search;
     * @return - link Node or null, if is not searching;
     */
    private Node<E> getLinkPlaceWrite(E value) {
        Node<E> result = null;
        if (value != null) {
            result = root;
            while (result != null && result.value != null) {
                int intRes = result.value.compareTo(value);
                if (intRes > 0) {
                    result = result.left = result.left == null ? new Node<E>(null) : result.left;
                } else if (intRes < 0) {
                    result = result.right = result.right == null ? new Node<>(null) : result.right;
                } else {
                    result = null;
                }
            }
        }
        return result;
    }

    /**
     * The recurse method convert tree to list.
     *
     * @param head - heads tree;
     * @param list - list with value tree;
     * @return - list values;
     */
    private List<E> getListElements(Node<E> head, List<E> list) {
        if (head != null) {
            list = getListElements(head.left, list);
            list.add(head.value);
            list = getListElements(head.right, list);
        }
        return list;
    }

    @Override
    public Object[] toArray() {
        return getListElements(root, new ArrayList<>()).toArray();
    }

    @Override
    public E[] toArray(E[] elements) {
        return getListElements(root, new ArrayList<>()).toArray(elements);
    }

    @Override
    public String toString() {
        return getListElements(root, new ArrayList<>()).toString();
    }

    @Override
    public Iterator<E> iterator() {
        return getListElements(root, new ArrayList<>()).iterator();
    }

    /**
     * The inner class Node - model element.
     *
     * @param <E> This describes my type parameter. Type for key;
     * @author Pavlov Artem
     * @since 02.10.2017
     */
    private class Node<E> {
        /**
         * The var - value.
         */
        private E value;

        /**
         * The link to min element.
         */
        private Node<E> left;

        /**
         * The link to max element.
         */
        private Node<E> right;

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

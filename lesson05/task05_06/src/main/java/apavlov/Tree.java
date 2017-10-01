package apavlov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The class Tree - use for storage value to tree.
 *
 * @param <E> This describes my type parameter. Type for key;
 * @author Pavlov Artem
 * @since 01.10.2017
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * The head tree.
     */
    private Node<E> root;

    /**
     * The count value to tree.
     */
    private int size;

    /**
     * The constructor for class Tree. Create head tree with value.
     *
     * @param value - value head;
     */
    public Tree(E value) {
        this.root = new Node<>(value);
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = parent != child;
        if (result) {
            Node<E> linkChild = getLinkNode(child, root, null);
            if (result = linkChild == null) {
                Node<E> linkParent = getLinkNode(parent, root, null);
                if (result = linkParent != null) {
                    linkParent.children.add(new Node<>(child));
                    size++;
                }
            }
        }
        return result;
    }

    /**
     * The recurse method for get link Node by value.
     *
     * @param value - value for search;
     * @param head - head tree;
     * @param result - var for return link Node;
     * @return - link Node or null, if is not searching;
     */
    private Node<E> getLinkNode(E value, Node<E> head, Node<E> result) {
        if (head != null && result == null) {
            if (head.value == value || (value != null && head.value != null && head.value.compareTo(value) == 0)) {
                result = head;
            } else {
                for (Node<E> element : head.children) {
                    result = getLinkNode(value, element, result);
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
            list.add(head.value);
            for (Node<E> element : head.children) {
                list = getListElements(element, list);
            }
        }
        return list;
    }

    /**
     * The method return array children parent`s.
     *
     * @param parent - parent;
     * @return - array children;
     */
    public Object[] toArrayChildrenParent(E parent) {
        Node<E> node = getLinkNode(parent, root, null);
        Object[] result = new Object[0];
        if (node != null) {
            result = new Object[node.children.size()];
            int index = 0;
            for (Node<E> nodeList : node.children) {
                result[index++] = nodeList.value;
            }
        }
        return result;
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
     * @since 01.10.2017
     */
    private class Node<E> {
        /**
         * The var - value.
         */
        private E value;

        /**
         * The list children.
         */
        private List<Node<E>> children;

        /**
         * The constructor for inner class Node.
         *
         * @param value - value;
         */
        Node(E value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
}

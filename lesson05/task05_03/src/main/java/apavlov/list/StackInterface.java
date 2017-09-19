package apavlov.list;

/**
 * The interface StackInterface - methods for work to stacks.
 *
 * @param <E> This describes my type parameter
 *
 * @author Pavlov Artem
 * @since 19.09.2017
 */
public interface StackInterface<E> {
    /**
     * The method return element to stack and delete this element. If stack is empty, then return null.
     * If queue, then return element to begin stack.
     * If stack, then return to end stack.
     *
     * @return delete element to stack;
     */
    E poll();

    /**
     * The method add new element.
     * If queue, then add to begin stack.
     * If stack, then add to end stack.
     *
     * @param value - value for add stack;
     */

    void push(E value);

    /**
     * The method return values to view array type Object.
     *
     * @return array Object;
     */
    Object[] toArray();
}

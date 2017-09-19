package apavlov.list;

/**
 * The class MyQueue - methods for work to stacks. Use realization LIFO.
 *
 * @param <E> This describes my type parameter
 * @author Pavlov Artem
 * @since 19.09.2017
 */
public class MyQueue<E> extends MyLinkedList<E> implements StackInterface<E> {

    @Override
    public E poll() {
        E result = super.isEmpty() ? null : get(0);
        super.delete(0);
        return result;
    }

    @Override
    public void push(E value) {
        super.add(0, value);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }
}

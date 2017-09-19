package apavlov.list;

/**
 * The class MyStack - methods for work to stacks. Use realization FIFO.
 *
 * @param <E> This describes my type parameter
 *
 * @author Pavlov Artem
 * @since 19.09.2017
 */
public class MyStack<E> extends MyLinkedList<E> implements StackInterface<E> {

    @Override
    public E poll() {
        E result = super.isEmpty() ? null : get(super.size() - 1);
        super.delete(super.size() - 1);
        return result;
    }

    @Override
    public void push(E value) {
        super.add(value);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }
}

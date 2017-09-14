package apavlov;

import java.util.Iterator;


/**
 * The class IteratorDynamicArray - iterator for dynamic array..
 *
 * @author Pavlov Artem
 * @since 14.09.2017
 *
 * @param <E> - generic;
 */
public class IteratorDynamicArray<E> implements Iterable<E>, Iterator<E> {
    /**
     * The var - dynamic array.
     */
    private E[][] values;

    /**
     * The var - position to array.
     */
    private int index;

    /**
     * The constructor for class IteratorDynamicArray.
     *
     * @param values - dynamic array;
     */
    public IteratorDynamicArray(E[][] values) {
        this.values = values;
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return values != null && index < values.length * values[0].length;
    }

    @Override
    public E next() {
        return values[index / values[0].length][index++ % values[0].length];
    }
}

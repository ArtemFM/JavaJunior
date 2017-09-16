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
    private Cursor cursor = new Cursor(-1, 0);

    /**
     * The constructor for class IteratorDynamicArray.
     *
     * @param values - dynamic array;
     */
    public IteratorDynamicArray(E[][] values) {
        this.values = values;
    }

    /**
     * The method return next position element or null.
     *
     * @return next position element or null;
     */
    private Cursor getNextIndexesElement() {
        Cursor result = new Cursor(cursor.positionX + 1, cursor.positionY);
        while (this.values != null && result.positionY < this.values.length && result.positionX >= this.values[result.positionY].length) {
            result.positionY++;
            result.positionX = 0;
        }
        result = this.values != null && result.positionY < this.values.length && result.positionX < this.values[result.positionY].length
                ? result : null;
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (cursor != null && cursor.positionX == -1 && cursor.positionY == 0) {
            cursor = getNextIndexesElement();
        }
        return this.values != null && cursor != null;
    }

    @Override
    public E next() {
        E result = this.values[cursor.positionY][cursor.positionX];
        cursor = getNextIndexesElement();
        return result;
    }

    /**
     * The inner class Cursor for save position.
     */
    private class Cursor {
        /**
         * The var - position x to array.
         */
        private int positionX;

        /**
         * The var - position y to array.
         */
        private int positionY;

        /**
         * The constructor for inner class Cursor.
         *
         * @param positionX - position x to array;
         * @param positionY - position y to array;
         */
        Cursor(int positionX, int positionY) {
            this.positionX = positionX;
            this.positionY = positionY;
        }
    }
}



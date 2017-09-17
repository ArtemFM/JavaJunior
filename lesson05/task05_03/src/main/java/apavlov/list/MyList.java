package apavlov.list;

/**
 * The interface MyList for work with array.
 *
 * @param <E> This describes my type parameter
 *
 * @author Pavlov Artem
 * @since 18.09.2017
 */
public interface MyList<E> extends Iterable<E> {
    /**
     * The method return count values to array.
     *
     * @return - count values;
     */
    int size();

    /**
     * The method check array on the empty.
     *
     * @return true - if array is empty; false - is array is not empty;
     */
    boolean isEmpty();

    /**
     * The method delete all values and create new array default length or user length.
     */
    void clear();

    /**
     * The method return values to view array type Object.
     *
     * @return array Object;
     */
    Object[] toArray();

    /**
     * The method return values to view String.
     *
     * @return - values to view String;
     */
    String toString();

    /**
     * The method add new value to array.
     *
     * @param value - value;
     */
    void add(E value);

    /**
     * The method add new value by index.
     *
     * @param index - index for add new value;
     * @param value - value;
     * @return true - if add new element; false - if is not add new element;
     */
    boolean add(int index, E value);

    /**
     * The method add new array to array this class.
     *
     * @param values - array new values;
     * @return - true - add array; false - is not add array;
     */
    boolean addAll(E[] values);

    /**
     * The private method for get index value.
     *
     * @param value - value for search;
     * @return if element is not found -1 or index element`s to array;
     */
    int get(E value);

    /**
     * The method return value by index.
     *
     * @param index - index;
     * @return - value or throw out exception;
     */
    E get(int index);

    /**
     * The method delete element by index.
     *
     * @param index - index for delete to array;
     * @return true - is delete element; false - is not delete element;
     */
    boolean delete(int index);

    /**
     * The method delete element by value.
     *
     * @param value - value;
     * @return true - element is found and deleting; false - element is not found;
     */
    boolean delete(E value);

    /**
     * The method edit value on the new value by index.
     *
     * @param index - index for edit;
     * @param value - new value;
     * @return - old value or throw out exception? if index is not range array;
     */
    E update(int index, E value);

}

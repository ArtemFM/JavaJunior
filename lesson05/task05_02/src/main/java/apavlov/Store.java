package apavlov;

/**
 * The interface Store - have methods add, delete, update.
 *
 * @param <T> This describes my type parameter
 *
 * @author Pavlov Artem
 * @since 17.09.2017
 */
public interface Store<T extends Base> {
    /**
     * The method add new element to base.
     *
     * @param value - value;
     */
    void add(T value);

    /**
     * The method delete element to base.
     *
     * @param value - value;
     * @return false - if is not edit; true - if is edit;
     */
    boolean delete(T value);

    /**
     * The method edit element to base.
     *
     * @param oldElement - element for edit;
     * @param newElement - new element;
     * @return old element;
     */
    T update(T oldElement, T newElement);
}

package apavlov;

/**
 * The abstract class AbstractStore - implement for interface Store.
 *
 * @param <T> This describes my type parameter
 *
 * @author Pavlov Artem
 * @since 17.09.2017
 */
public abstract class AbstractStore<T extends Base> implements Store {
    /**
     * The list on the base array for type Base and him extends.
     */
    private SimpleArray<Base> base = new SimpleArray<>();

    @Override
    public void add(Base value) {
        base.add(value);
    }

    @Override
    public boolean delete(Base value) {
        return base.delete(value);
    }

    @Override
    public Base update(Base oldElement, Base newElement) {
        return base.update(base.get(oldElement), newElement);
    }

    /**
     * The method return array type Object.
     *
     * @return array type Object;
     */
    public Object[] toArray() {
        return base.toArray();
    }
}

package apavlov.nonblock;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The class NonBlockingCache. Use for work to collection ConcurrentHashMap.
 *
 * @param <K> - this describes my type parameter (key);
 * @param <V> - this describes my type parameter (value extends Model)
 * @author Pavlov Artem
 * @since 26.12.2017
 */
public class NonBlockingCache<K, V extends Model> {
    /**
     * The collection type ConcurrentHashMap.
     */
    private final ConcurrentHashMap<K, V> map;

    /**
     * The default constructor for class NonBlockingCache.
     */
    public NonBlockingCache() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * The method return amount elements to collection.
     *
     * @return amount elements;
     */
    public int size() {
        return this.map.size();
    }

    /**
     * The method add new key and element to collection.
     * If collection contains key, then is not add.
     *
     * @param key - key;
     * @param value - value;
     * @return true - is add or false;
     */
    public boolean add(K key, V value) {
        return this.map.putIfAbsent(key, value) == null;
    }

    /**
     * The method delete key. If key is not found, then return null.
     *
     * @param key - key for delete;
     * @return true - is delete or false;
     */
    public boolean delete(K key) {
        return this.map.remove(key) != null;
    }

    /**
     * The method update values by key.
     *
     * @param key - key;
     * @param value - value;
     * @return value;
     */
    public V update(K key, V value) {
        return this.map.computeIfPresent(key, (k, v) -> {
            if (value != null && value.getVersion() == v.getVersion()) {
                this.map.replace(k, value);
                value.iterateVersion();
            } else {
                throw new OptimisticException("Value is not found.");
            }
            return value;
        });
    }

    /**
     * The method return values to collections.
     *
     * @param array - result type array;.
     * @return array;
     */
    public V[] toArray(V[] array) {
        return this.map.values().toArray(array);
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}

/**
 * The class Model - use for example work ConcurrentHashMap.
 *
 * @author Pavlov Artem
 * @since 26.12.2017
 */
class Model {
    /**
     * The version object.
     */
    private int version;

    /**
     * The name object.
     */
    private String name;

    /**
     * The getter for version.
     *
     * @return version;
     */
    public int getVersion() {
        return version;
    }

    /**
     * The getter for name.
     *
     * @return name;
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for name.
     *
     * @param name - name object;
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The constructor for class Model.
     *
     * @param name - name object;
     */
    Model(String name) {
        this.version = new Object().hashCode();
        this.name = name;
    }

    /**
     * The method iterate version to 1.
     */
    public void iterateVersion() {
        this.version++;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

/**
 * The class OptimisticException extends RuntimeException.
 *
 * @author Pavlov Artem
 * @since 26.12.2017
 */
class OptimisticException extends RuntimeException {
    /**
     * The constructor for class OptimisticException.
     *
     * @param message - message for out;
     */
    OptimisticException(String message) {
        super(message);
    }
}
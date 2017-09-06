package apavlov;

import java.util.Collection;

/**
 * The class Perfomance for test time work collections.
 *
 * @author Pavlov Artem
 * @since 06.09.2017
 */
public class Perfomance {
    /**
     * The var save milliseconds.
     */
    private long timeMS;

    /**
     * The method add elements to collection.
     *
     * @param collection - link collection;
     * @param amount     - count elements for add;
     * @return - time work;
     */
    public long add(Collection<String> collection, int amount) {
        start();
        for (int i = 0; i < amount; i++) {
            collection.add(String.format("Element %s;", i));
        }
        return end();
    }

    /**
     * The method delete elements to collection.
     *
     * @param collection - link collection;
     * @param n          - count elements for delete;
     * @return - time work;
     */
    public long delete(Collection<String> collection, int n) {
        start();
        for (int i = 0; i < n; i++) {
            collection.remove(String.format("Element %s;", i));
        }
        return end();
    }

    /**
     * The method get start time for test.
     */
    public void start() {
        this.timeMS = System.currentTimeMillis();
    }

    /**
     * The method return full time work collection.
     *
     * @return - time work collection;
     */
    public long end() {
        return System.currentTimeMillis() - timeMS;
    }

    /**
     * The method is convert ms to seconds.
     *
     * @param ms - milliseconds;
     * @return - seconds to string;
     */
    public String convertMsToSec(long ms) {
        return String.format("%s sec %s ms", ms / 1000, ms % 1000);
    }
}

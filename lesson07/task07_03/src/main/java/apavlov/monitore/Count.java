package apavlov.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * The class Count - have synchronized method.
 *
 * @author Pavlov Artem
 * @since 19.12.2017
 */
@ThreadSafe
public final class Count {
    /**
     * The value type int.
     */
    @GuardedBy("this")
    private int counter;

    /**
     * Increment local var value on the one.
     *
     * @return value + 1;
     */
    public int increment() {
        synchronized (this) {
            return ++this.counter;
        }
    }
}

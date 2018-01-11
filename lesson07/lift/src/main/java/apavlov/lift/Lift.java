package apavlov.lift;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The class Lift - for storage deque floors for lift.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
@ThreadSafe
public class Lift {
    /**
     * The deque storage next floors.
     */
    @GuardedBy("itself")
    private final Deque<Integer> deque;

    /**
     * Where press button.
     */
    public enum Press {
        /**
         * The press button to in lift.
         */
        IN,
        /**
         * The press button to out lift.
         */
        OUT
    }

    /**
     * The default constructor for class Lift.
     */
    public Lift() {
        this.deque = new LinkedBlockingDeque<>();
    }

    /**
     * The method add new floor to deque.
     *
     * @param press - where press button;
     * @param value - floor for add;
     */
    public void add(Press press, int value) {
        synchronized (this.deque) {
            if (press == Press.IN) {
                this.deque.addFirst(value);
            } else if (press == Press.OUT) {
                this.deque.addLast(value);
            }
            this.deque.notify();
        }
    }

    /**
     * The method return head deque.
     *
     * @return head deque;
     * @throws InterruptedException - if call interrupt;
     */
    public int get() throws InterruptedException {
        synchronized (this.deque) {
            while (this.deque.isEmpty()) {
                this.deque.wait();
            }
            return this.deque.getFirst();
        }
    }

    /**
     * The method remove floor by value to deque.
     *
     * @param value - value for deleting;
     * @throws InterruptedException - if call interrupt;
     */
    public void remove(int value) throws InterruptedException {
        synchronized (this.deque) {
            while (this.deque.isEmpty()) {
                this.deque.wait();
            }
            this.deque.remove(value);
        }
    }
}

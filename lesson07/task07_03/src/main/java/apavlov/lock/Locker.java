package apavlov.lock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * The class Locker - have methods blocking (replace synchronized).
 *
 * @author Pavlov Artem
 * @since 25.12.2017
 */
@ThreadSafe
public class Locker implements Lock {
    /**
     * The var - use for blocking threads.
     */
    @GuardedBy("itself")
    private final Object obj = new Object();

    /**
     * The var - true - is block; false - is unblock.
     */
    private boolean isLock;

    @Override
    public void lock() {
        synchronized (this.obj) {
            while (isLock && !Thread.currentThread().isInterrupted()) {
                try {
                    this.obj.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!Thread.currentThread().isInterrupted()) {
                this.isLock = true;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        lock();
    }

    @Override
    public boolean tryLock() {
        boolean result;
        if (result = !isLock) {
            lock();
        }
        return result;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        boolean result = tryLock();
        if (!result) {
           long end = System.currentTimeMillis() + unit.toMillis(time);
           synchronized (this.obj) {
              while (isLock && !Thread.currentThread().isInterrupted() && end - System.currentTimeMillis() >= 0) {
                  this.obj.wait(unit.toMillis(time));
              }
              result = tryLock();
           }
        }
        return result;
    }

    @Override
    public void unlock() {
        synchronized (this.obj) {
            this.isLock = false;
            this.obj.notify();
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
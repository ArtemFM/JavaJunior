package apavlov.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class ThreadPool - work with queue, use threads.
 *
 * @param <E> - this describes my type parameter. Type for value;
 * @author Pavlov Artem
 * @since 25.12.2017
 */
@ThreadSafe
public class ThreadPool<E> {
    /**
     * The constant - amount processors to computer.
     */
    private static final int AMOUNT_CPU = Runtime.getRuntime().availableProcessors();

    /**
     * Threads group.
     */
    private final ThreadGroup group = new ThreadGroup("ThreadGroup");

    /**
     * The collection queue for work threads.
     */
    @GuardedBy("itself")
    private final Queue<E> queue = new LinkedBlockingQueue<>();

    /**
     * The method start threads, equals amount cpu to computer.
     */
    public void start() {
        for (int i = 0; i < AMOUNT_CPU; i++) {
            createThread(this.group, String.format("Thread-%d", i + 1));
        }
    }

    /**
     * The method stopped group threads.
     */
    public void stop() {
        this.group.interrupt();
    }

    /**
     * The method add new element to collection queue.
     *
     * @param value - element for add;
     */
    public void add(E value) {
        synchronized (this.queue) {
            this.queue.add(value);
            System.out.printf("Add element: %s;%s", value, System.lineSeparator());
            this.queue.notify();
        }
    }

    /**
     * The method create new thread to group.
     *
     * @param group - threads group;
     * @param nameThread - name new thread;
     */
    private void createThread(ThreadGroup group, String nameThread) {
        new Thread(group, () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (this.queue) {
                        while (this.queue.isEmpty()) {
                            System.out.printf("%s: waiting...%s", Thread.currentThread().getName(), System.lineSeparator());
                            this.queue.wait();
                        }
                        System.out.printf("%s: delete %s;%s", Thread.currentThread().getName(), this.queue.poll(), System.lineSeparator());
                    }
                }
            } catch (InterruptedException e) {
                //empty block
            }
            System.out.printf("%s: stop;%s", Thread.currentThread().getName(), System.lineSeparator());
        }, nameThread).start();
    }
}

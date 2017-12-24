package apavlov.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class ProducerConsumer - work with queue, use threads.
 *
 * @author Pavlov Artem
 * @since 24.12.2017
 */
@ThreadSafe
public class ProducerConsumer {
    /**
     * The constant - max amount elements to queue.
     */
    private static final int MAX_SIZE_QUEUE = 10;

    /**
     * The collection queue.
     */
    @GuardedBy("itself")
    private final Queue<Integer> queue;

    /**
     * The var for stopping threads, if this true.
     */
    private boolean isFinish;

    /**
     * The thread producer. Use lambda.
     */
    private Thread producer = new Thread(() -> {
        try {
            while (!isFinish) {
                int value = (int) (Math.random() * 100);
                System.out.printf("[%s]: Add element [%d];%s", Thread.currentThread().getName(), value, System.lineSeparator());
                addElement(value);
            }
            System.out.printf("[%s]: is end work.%s", Thread.currentThread().getName(), System.lineSeparator());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }, "Producer");

    /**
     * The thread consumer. Use lambda.
     */
    private Thread consumer = new Thread(() -> {
        try {
            while (!isFinish) {
                System.out.printf("[%s]: Delete element [%d];%s", Thread.currentThread().getName(), deleteElement(), System.lineSeparator());
            }
            System.out.printf("[%s]: is end work.%s", Thread.currentThread().getName(), System.lineSeparator());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }, "Consumer");

    /**
     * The getter for thread producer.
     *
     * @return link thread producer;
     */
    public Thread getProducer() {
        return producer;
    }

    /**
     * The getter for thread consumer.
     *
     * @return link thread consumer;
     */
    public Thread getConsumer() {
        return consumer;
    }

    /**
     * The setter for var isFinish.
     *
     * @param finish - true or false;
     */
    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    /**
     * The default constructor for class ProducerConsumer.
     */
    public ProducerConsumer() {
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * The method for add new element to queue.
     *
     * @param value - value for add;
     * @throws InterruptedException - exception for thread;
     */
    private void addElement(int value) throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.size() == MAX_SIZE_QUEUE && !this.isFinish) {
                System.out.println("The queue is full: wait...");
                this.queue.wait();
            }
            this.queue.add(value);
            this.queue.notify();
        }
    }

    /**
     *  The method for delete first element to queue.
     *
     * @return delete element;
     * @throws InterruptedException - exception for thread;
     */
    private int deleteElement() throws InterruptedException {
        int result;
        synchronized (this.queue) {
            while (this.queue.isEmpty() && !this.isFinish) {
                System.out.println("The queue is empty: wait...");
                this.queue.wait();
            }
            result = this.queue.poll();
            this.queue.notify();
        }
        return result;
    }
}

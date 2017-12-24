package apavlov.blocking;

import org.junit.Test;

/**
 * The test class ProducerConsumerTest for class`s ProducerConsumer use junit4.
 *
 * @author Pavlov Artem
 * @since 24.12.2017
 */
public class ProducerConsumerTest {
    /**
     * The method for check work threads with queue.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkTwoThreads() throws Exception {
        int cycle = 10;
        ProducerConsumer pc = new ProducerConsumer();

        pc.getProducer().start();
        pc.getConsumer().start();
        while (cycle-- != 0) {
            Thread.sleep(100);
            if (cycle == 0) {
                pc.setFinish(true);
            }
        }
        pc.getProducer().join();
        pc.getConsumer().join();
    }
}
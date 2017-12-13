package apavlov;

/**
 * The class CountSymbols - for print to console count symbols, use two threads for work.
 *
 * @author Pavlov Artem
 * @since 13.12.2017
 */
public class CountSymbols {
    /**
     * The text for work.
     */
    private String offer;

    /**
     * The time for wait to milliseconds.
     */
    private long ms;

    /**
     * The constructor for class CountSymbols.
     *
     * @param offer - text for work;
     * @param ms - time for wait to milliseconds;
     */
    public CountSymbols(String offer, long ms) {
        this.offer = offer;
        this.ms = ms;
    }

    /**
     * The method start two thread (first - thread-worker; second - thread time wait).
     * Output to console count symbols in text.
     */
    public void start() {
        System.out.println("Start program...");
        System.out.printf("Text:%s%s%s", System.lineSeparator(), this.offer, System.lineSeparator());
        Thread countChar = new Thread(new CountChar(offer));
        Thread time = new Thread(new Time(ms, countChar));
        countChar.start();
        time.start();
        try {
            countChar.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stop program...");
    }


}

/**
 * The class Time - implements Runnable. Wait time. If time is over, stopped other thread.
 *
 * @author Pavlov Artem
 * @since 13.12.2017
 */
class Time implements Runnable {
    /**
     * Time for wait and work this runnable.
     */
    private long ms;

    /**
     * The link to thread-work.
     */
    private Thread thread;

    /**
     * The constructor for class Time.
     *
     * @param ms     - time wait to milliseconds;
     * @param thread - thread for stopped, if time is over;
     */
    Time(long ms, Thread thread) {
        this.thread = thread;
        this.ms = ms;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (thread.isAlive()) {
            if (System.currentTimeMillis() - start >= this.ms) {
                System.out.println("The time is over. Stopping thread...");
                thread.interrupt();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * The class CountChar - implements Runnable. Counting symbols to text and output to console.
 *
 * @author Pavlov Artem
 * @since 13.12.2017
 */
class CountChar implements Runnable {
    /**
     * The text for counting symbols.
     */
    private String offer;

    /**
     * The constructor for class CountChar.
     *
     * @param offer - text for work (counting symbols);
     */
    CountChar(String offer) {
        this.offer = offer;
    }

    @Override
    public void run() {
        if (this.offer != null) {
            int index = offer.length() - 1;
            int countSymbols = 0;
            while (index != -1) {
                if (!Thread.currentThread().isInterrupted()) {
                    if (offer.charAt(index) != ' ' && offer.charAt(index) != '\n') {
                        countSymbols++;
                    }
                } else {
                    System.out.println("The thread is stopped.");
                    break;
                }
                if (index == 0) {
                    System.out.printf("The text have %d symbols.%s", countSymbols, System.lineSeparator());
                }
                index--;
            }
        }
    }
}

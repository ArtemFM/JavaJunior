package apavlov;

/**
 * The class ParametersOffer - for print to console count spaces and words to offer.
 *
 * @author Pavlov Artem
 * @since 29.11.2017
 */
public class ParametersOffer {
    /**
     * Offer for work.
     */
    private String offer;

    /**
     * The constructor for class ParametersOffer.
     *
     * @param offer - offer for work;
     */
    public ParametersOffer(String offer) {
        this.offer = offer;
    }

    /**
     * The method start threads work to offer.
     *
     * @param time - max time in ms for work threads;
     */
    public void start(long time) {
        System.out.println("Start program");
        long ms = System.currentTimeMillis();
        Thread threadFirst = new WorkerString().getCountSpacesToOffer(this.offer);
        Thread threadSecond = new WorkerString().getCountWordsToOffer(this.offer);
        threadFirst.start();
        threadSecond.start();
        while (!threadFirst.isInterrupted() || !threadSecond.isInterrupted()) {
           if (System.currentTimeMillis() - ms >= time) {
               stop(threadFirst);
               stop(threadSecond);
               break;
           }
        }
        System.out.println("End program");
    }

    /**
     * If thread is not stop, then stop thread.
     *
     * @param thread - thread for stop;
     */
    public void stop(Thread thread) {
        if (!thread.isInterrupted()) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



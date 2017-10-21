package apavlov;

/**
 * The class ParametersOffer - for print to console count spaces and words to offer.
 *
 * @author Pavlov Artem
 * @since 21.10.2017
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
     * @param ms - max time in ms for work threads;
     */
    public void start(long ms) {
        System.out.printf("Start program %s;%s", getClass().getName(), System.lineSeparator());
        Thread threadFirst = new WorkerString().getCountSpacesToOffer(this.offer);
        Thread threadSecond = new WorkerString().getCountWordsToOffer(this.offer);
        threadFirst.start();
        threadSecond.start();
        try {
            threadFirst.join(ms);
            threadSecond.join(ms);
            if (threadFirst.isAlive()) {
                threadFirst.interrupt();
                threadFirst.join();
            }
            if (threadSecond.isAlive()) {
                threadSecond.interrupt();
                threadSecond.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("End program %s;%s", getClass().getName(), System.lineSeparator());
    }
}


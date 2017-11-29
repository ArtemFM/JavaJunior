package apavlov;

/**
 * The class WorkerString - for work to string (output count spaces or words).
 *
 * @author Pavlov Artem
 * @since 21.10.2017
 */
public class WorkerString {
    /**
     * The method output count spaces to string.
     *
     * @param offer - string for work;
     * @return thread;
     */
    public Thread getCountSpacesToOffer(String offer) {
        return new ThreadCountSpaces(offer, "ThreadCountSpaces");
    }


    /**
     * The method output count words to string.
     *
     * @param offer - string for work;
     * @return thread;
     */
    public Thread getCountWordsToOffer(final String offer) {
        return new ThreadCountWords(offer, "ThreadCountWords");
    }
}

/**
 * The inner class ThreadCountSpaces - thread for count spaces to offer.
 *
 * @author Pavlov Artem
 * @since 29.11.2017
 */
class ThreadCountSpaces extends Thread {
    /**
     * Offer for work thread.
     */
    private String offer;

    /**
     * The constructor for class ThreadCountSpaces.
     *
     * @param offer - offer for work;
     * @param name - name thread;
     */
    ThreadCountSpaces(String offer, String name) {
        super(name);
        this.offer = offer;
    }

    @Override
    public void run() {
        int index = offer != null ? offer.length() - 1 : -1;
        int countSpaces = 0;
        try {
            while (index >= 0) {
                if (!isInterrupted()) {
                    if (offer.charAt(index) == ' ') {
                        countSpaces++;
                    }
                } else {
                   throw new InterruptedException();
                }
                index--;
            }
            System.out.printf("Count spaces: %d;%sThe thread [%s] is end work.%s", countSpaces, System.lineSeparator(), getName(), System.lineSeparator());
        } catch (InterruptedException e) {
            System.out.printf("The thread [%s] is interrupted.%s", getName(), System.lineSeparator());
        }
    }
}

/**
 * The inner class ThreadCountWords - thread for count words to offer.
 *
 * @author Pavlov Artem
 * @since 29.11.2017
 */
class ThreadCountWords extends Thread {
    /**
     * Offer for work thread.
     */
    private String offer;

    /**
     * The constructor for class ThreadCountSpaces.
     *
     * @param offer - offer for work;
     * @param name - name thread;
     */
    ThreadCountWords(String offer, String name) {
        super(name);
        this.offer = offer;
    }

    @Override
    public void run() {
        int index = offer != null ? offer.length() - 1 : -1;
        int countSymbols = 0;
        int countWords = 0;
        try {
            while (index >= 0) {
                if (!isInterrupted()) {
                    if (offer.charAt(index) == ' ' || offer.charAt(index) == ',' || offer.charAt(index) == '.' || offer.charAt(index) == '!' || offer.charAt(index) == '?') {
                        countSymbols++;
                    } else {
                        if (countSymbols != 0) {
                            countWords++;
                            countSymbols = 0;
                        }
                    }
                } else {
                    throw new InterruptedException();
                }
                index--;
            }
            if (countSymbols != 0) {
                countWords++;
            }
            System.out.printf("Count words: %d;%sThe thread [%s] is end work.%s", countWords, System.lineSeparator(), getName(), System.lineSeparator());
        } catch (InterruptedException e) {
            System.out.printf("The thread [%s] is interrupted.%s", getName(), System.lineSeparator());
        }
    }
}

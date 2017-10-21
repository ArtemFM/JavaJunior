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
        return new Thread(() -> {
            try {
                Thread.sleep(10);
                System.out.printf("Count spaces: %s%s",
                        offer.length() - offer.replaceAll("[ ]*", "").length(), System.lineSeparator());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread stop!");
            }
        });
    }

    /**
     * The method output count words to string.
     *
     * @param offer - string for work;
     * @return thread;
     */
    public Thread getCountWordsToOffer(String offer) {
        return new Thread(() -> {
            try {
                Thread.sleep(10);
                System.out.printf("Count words: %s%s",
                        offer.replaceAll("\\p{Punct}", " ").split("\\s+").length, System.lineSeparator());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread stop!");
            }
        });
    }
}

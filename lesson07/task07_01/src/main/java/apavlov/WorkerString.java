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
        return new Thread(() ->
                System.out.printf("Count spaces: %s", offer.length() - offer.replaceAll("[ ]*", "").length())
        );
    }

    /**
     * The method output count words to string.
     *
     * @param offer - string for work;
     * @return thread;
     */
    public Thread getCountWordsToOffer(String offer) {
        return new Thread(() ->
            System.out.printf("Count words: %s", offer.replaceAll("\\p{Punct}", " ").split("\\s+").length)
        );
    }
}

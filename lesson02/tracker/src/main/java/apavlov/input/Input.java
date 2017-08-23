package apavlov.input;

/**
 * The interface input for work with console.
 *
 * @author Pavlov Artem
 * @since 22.08.2017
 */
public interface Input {

    /**
     * The method for work to console.
     *
     * @param question - question for user in console;
     * @return value inpeted with console;
     */
    String ask(String question);

    /**
     * The method for work to console.
     *
     * @param question - question for user in console;
     * @param startRange - min possible number;
     * @param endRange - max possible number;
     * @return value inpeted with console;
     */
    int ask(String question, int startRange, int endRange);
}

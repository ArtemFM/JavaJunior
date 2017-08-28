package apavlov.input;

import java.util.Arrays;

/**
 * The class StubInput implement`s interface Input.
 *
 * @author Pavlov Artem
 * @since 23.08.2017
 */
public class StubInput implements Input {
    /**
     * The var is position to array.
     */
    private int position;

    /**
     * The array save combinations menu.
     */
    private String[] answers;

    /**
     * The constructor class`s StubInput.
     *
     * @param answers - fill array combinations;
     */
    public StubInput(String[] answers) {
        this.answers = Arrays.copyOf(answers, answers.length);
    }

    @Override
    public String ask(String question) {
        return answers[position++];
    }

    @Override
    public String ask(String question, int startRange, int endRange) {
        int key = Integer.parseInt(answers[position++]);
        if (key < startRange || key > endRange) {
            throw new ArrayIndexOutOfBoundsException("Inccoret range...");
        }
        return String.valueOf(key);
    }
}

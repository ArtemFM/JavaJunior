package apavlov.input;

import java.util.List;

/**
 * The class StubInput implement`s interface Input.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class StubInput implements Input {
    /**
     * The var is position to array.
     */
    private int position;

    /**
     * The array save combinations menu.
     */
    private List<String> answers;

    /**
     * The constructor class`s StubInput.
     *
     * @param answers - fill array combinations;
     */
    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return answers.get(position++);
    }

    @Override
    public int ask(String question, int startRange, int endRange) {
        int key = Integer.parseInt(answers.get(position++));
        if (key < startRange || key > endRange) {
            throw new ArrayIndexOutOfBoundsException("Inccoret range...");
        }
        return key;
    }
}

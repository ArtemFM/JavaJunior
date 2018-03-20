package apavlov.input;

import apavlov.error.MenuOutException;

/**
 * The class ValidateInput extends class ConsoleInput.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class ValidateInput extends ConsoleInput {
    /**
     * The method check input data on the errors.
     *
     * @param question - question to console user`s;
     * @param startRange - start range;
     * @param endRange - end range;
     * @return - -1 if error value or number in range;
     */
    public int ask(String question, int startRange, int endRange) {
        int answer = -1;
        try {
            answer = super.ask(question, startRange, endRange);
        } catch (NumberFormatException e) {
            System.out.printf("The number is incorrect. Try again...%s", System.lineSeparator());
        } catch (MenuOutException e) {
            System.out.printf("Incorrect value. Try again...%s", System.lineSeparator());
        }
        return answer;
    }
}

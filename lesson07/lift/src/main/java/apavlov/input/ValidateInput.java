package apavlov.input;

import apavlov.error.IncorrectFloorException;

/**
 * The class ValidateInput - extends Input. Check exception.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class ValidateInput extends Input {
    /**
     * The method read console and return value type int.
     *
     * @param text - text for user;
     * @param min - min range;
     * @param max - max range;
     * @return value type int;
     */
    public int readConsole(String text, int min, int max) {
        int result = -1;
        try {
            result = super.readConsole(text, min, max);
        } catch (NumberFormatException | IncorrectFloorException e) {
            System.out.println("Error: The incorrect number. Try again...");
        }
        return result;
    }
}

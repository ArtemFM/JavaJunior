package apavlov.input;

import apavlov.error.IncorrectFloorException;

import java.util.Scanner;

/**
 * The class Input - for work with console.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class Input {
    /**
     * The object work twith console.
     */
    private Scanner read = new Scanner(System.in);

    /**
     * The method read console and return value type int.
     *
     * @param text - text for user;
     * @param min - min range;
     * @param max - max range;
     * @return - value type int;
     * @throws NumberFormatException - if input not number;
     */
    public int readConsole(String text, int min, int max) throws NumberFormatException {
        System.out.print(text);
        int result = Integer.parseInt(this.read.nextLine().trim());
        if (result < min || result > max) {
            throw new IncorrectFloorException("Error: Incorrect range");
        }
        return result;
    }
}

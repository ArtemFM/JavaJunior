package apavlov.input;

import apavlov.error.MenuOutException;

import java.util.Scanner;

/**
 * The class ConsoleInput implement`s interface Input.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class ConsoleInput implements Input {
    /**
     * The var for read input data user.
     */
    private Scanner readConsole = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return readConsole.nextLine();
    }

    @Override
    public int ask(String question, int startRange, int endRange) {
        System.out.print(question);
        int key = Integer.parseInt(readConsole.nextLine());
        if (key < startRange || key > endRange) {
            throw new MenuOutException("Error: Input number incorrect!");
        }
        return key;
    }
}

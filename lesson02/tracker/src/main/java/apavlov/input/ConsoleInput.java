package apavlov.input;

import java.util.Scanner;

/**
 * The class ConsoleInput implement`s interface Input.
 *
 * @author Pavlov Artem
 * @since 22.08.2017
 */
public class ConsoleInput implements Input {
    /**
     * The var class`s Scanner for work with console.
     */
    private Scanner readConsole = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return readConsole.nextLine();
    }

    @Override
    public String ask(String question, int startRange, int endRange) {
        System.out.print(question);
        return readConsole.nextLine();
    }
}

package apavlov;

import apavlov.database.Tracker;
import apavlov.input.Input;
import apavlov.input.ValidateInput;
import apavlov.menu.MenuTracker;

/**
 * The class StartUi for work with Tracker.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class StartUI {
    /**
     * The constant for lineSeparator.
     */
    private static final String LS = System.lineSeparator();

    /**
     * The var for work with console.
     */
    private Input input;

    /**
     * The var for work with tracker.
     */
    private Tracker tracker;

    /**
     * The array name menu to program.
     */
    private String[] menu;

    /**
     * The constructor class StartUI.
     *
     * @param input   - link class Input;
     * @param tracker - link class Tracker;
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * The method work full program.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillMenu("Welcome to program Tracker...");
        menuTracker.showMenuToConsole();
        int key = 0;
        do {
            key = input.ask(String.format("%sPlease, input number menu and press Enter: ", LS), 1, menuTracker.getSizeMenu());
            if (key != -1) {
                menuTracker.select(key);
                if (!menuTracker.isExit(key)) {
                    menuTracker.showMenuToConsole();
                }
            }
        } while (!menuTracker.isExit(key));
    }

    /**
     * The method is point enter program.
     *
     * @param args - data for console;
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}

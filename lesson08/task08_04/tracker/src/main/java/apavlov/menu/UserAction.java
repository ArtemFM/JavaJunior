package apavlov.menu;

import apavlov.database.Tracker;
import apavlov.input.Input;

/**
 * The interface UserAction for work with menu.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public interface UserAction {
    /**
     * The constant var line separator.
     */
    String LS = System.lineSeparator();

    /**
     * The method for get index (key) menu.
     *
     * @return index menu (key);
     */
    int key();

    /**
     * The method work menu.
     *
     * @param tracker - link object Tracker;
     * @param input - link object Input;
     */
    void execute(Tracker tracker, Input input);

    /**
     * The method name menu for console.
     *
     * @return name menu;
     */
    String info();
}

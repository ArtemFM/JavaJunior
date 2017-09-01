package apavlov.menu;

import apavlov.database.Tracker;
import apavlov.input.Input;

/**
 * The abstract class BaseAction implements interface UserAction.
 *
 * @author Pavlov Artem
 * @since 01.09.2017
 */
public abstract class BaseAction implements UserAction {

    /**
     * The var for save name menu.
     */
    private String name;

    /**
     * The var for save number menu.
     */
    private int key;

    /**
     * The constructor for abstract class BaseAction.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * The method return number menu.
     *
     * @return number menu;
     */
    public int key() {
        return this.key;
    }

    /**
     * The abstract method for execuite menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public abstract void execuite(Tracker tracker, Input input);

    /**
     * The method return name menu.
     *
     * @return name menu;
     */
    public String info() {
        return this.name;
    }
}

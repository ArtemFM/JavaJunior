package apavlov;

import apavlov.database.BaseSQL;
import apavlov.database.Tracker;
import apavlov.input.Input;
import apavlov.input.ValidateInput;
import apavlov.menu.MenuTracker;
import apavlov.util.NameConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class StartUi for work with Tracker.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class StartUI {
    /**
     * The constant for lineSeparator.
     */
    private static final String LS = System.lineSeparator();

    /**
     * The constant - system logger (log4j 2).
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * The var for work with console.
     */
    private final Input input;

    /**
     * The var for work with tracker.
     */
    private final Tracker tracker;

    /**
     * The link object job to base SQL.
     */
    private final BaseSQL sql;

    /**
     * The constructor for class StartUI.
     *
     * @param input - object read console;
     * @param tracker - object work menu;
     * @param sql - object job to base SQL;
     */
    private StartUI(Input input, Tracker tracker, BaseSQL sql) {
        this.input = input;
        this.tracker = tracker;
        this.sql = sql;
    }

    /**
     * The method work full program.
     */
    private void init() {
        if (this.sql.connect()) {
            MenuTracker menuTracker = new MenuTracker(input, tracker);
            menuTracker.fillMenu(NameConstant.WELCOME.toString());
            menuTracker.showMenuToConsole();
            int key;
            do {
                key = input.ask(String.format("%sPlease, input number menu and press Enter: ", LS), 1, menuTracker.getSizeMenu());
                if (key != -1) {
                    menuTracker.select(key);
                    if (!menuTracker.isExit(key)) {
                        menuTracker.showMenuToConsole();
                    }
                }
            } while (!menuTracker.isExit(key));
            if (this.sql.isConnect()) {
                this.sql.disconnect();
            }
        }
    }

    /**
     * The method is point enter program.
     *
     * @param args - data for console;
     */
    public static void main(String[] args) {
        BaseSQL base = new BaseSQL(LOG);
        new StartUI(new ValidateInput(), new Tracker(base), base).init();
    }
}

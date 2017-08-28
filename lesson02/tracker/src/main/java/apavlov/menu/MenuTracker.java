package apavlov.menu;

import apavlov.database.Tracker;
import apavlov.input.Input;
import apavlov.models.Comment;
import apavlov.models.Item;

import java.util.Arrays;

/**
 * The class StartUi for work with Tracker.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
public class MenuTracker {

    /**
     * The var object UserAction for exit program.
     */
    private UserAction exit = new UserAction() {
        @Override
        public int key() {
            return 12;
        }

        @Override
        public void execuite(Tracker tracker, Input input) {
            System.out.printf("%sProgramm is exit...", LS);
        }

        @Override
        public String info() {
            return "Exit program";
        }
    };

    /**
     * The constant var line separator.
     */
    public static final String LS = System.lineSeparator();

    /**
     * The var for work with console.
     */
    private Input input;

    /**
     * The var work to base items.
     */
    private Tracker tracker;

    /**
     * The array for save all menu program.
     */
    private UserAction[] menuArray = new UserAction[0];

    /**
     * The constructor for class MenuTracker.
     *
     * @param input   - link object Input;
     * @param tracker - link object Tracker;
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * The method return count menu to array.
     *
     * @return count menu;
     */
    public int getSizeMenu() {
        return menuArray.length;
    }

    /**
     * The method add menu to array.
     *
     * @param menu - ling object type UserAction;
     * @return false ot true (is add or is not add);
     */
    private boolean addMenu(UserAction menu) {
        boolean result = true;
        menuArray = Arrays.copyOf(menuArray, menuArray.length + 1);
        menuArray[menuArray.length - 1] = menu;
        return result;
    }

    /**
     * The method fill menu program.
     *
     * @param welcome text welcome for program;
     */
    public void fillMenu(String welcome) {
        System.out.printf("%s%s%s", welcome, LS, LS);
        addMenu(new ShowItems());
        addMenu(new SortById());
        addMenu(new SortByName());
        addMenu(new AddItem());
        addMenu(new SearchByName());
        addMenu(new DeleteItem());
        addMenu(new EditItem());
        addMenu(new ShowCommentsItem());
        addMenu(new AddCommentItem());
        addMenu(new DeleteCommentItem());
        addMenu(new ClearCommentsItem());
        addMenu(exit);
    }

    /**
     * The method out menu to console.
     */
    public void showMenuToConsole() {
        for (UserAction menu : menuArray) {
            System.out.printf("%2s. %s;%s", menu.key(), menu.info(), LS);
        }
    }

    /**
     * The method return true or false (true - exit program).
     *
     * @param key = key for check;
     * @return false or true;
     */
    public boolean isExit(int key) {
        return key == exit.key();
    }

    /**
     * The method execute menu by key.
     *
     * @param key - key for execute;
     */
    public void select(int key) {
        for (UserAction menu : menuArray) {
            if (menu.key() == key) {
                menu.execuite(this.tracker, this.input);
                break;
            }
        }
    }
}

/**
 * The inner class ShowItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class ShowItems implements UserAction {

    @Override
    public int key() {
        return 1;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String line = String.format("%95s", " ").replace(" ", "-");
        System.out.printf("%s%-5s%-30s%-50s%-11s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS);
        System.out.println(line);
        if (tracker.getAllItems().length != 0) {
            for (Item item : tracker.getAllItems()) {
                System.out.printf("%-5.4s%-30.29s%-50.49s%-11.10s%s", item.getIdItem(), item.getName(), item.getDescription(), item.getDateCreate(), LS);
            }
        } else {
            System.out.printf("%sBase items is empty...%s%s", LS, LS, LS);
        }
        System.out.printf("%s%s%s", line, LS, LS);
    }

    @Override
    public String info() {
        return "Show all items";
    }
}

/**
 * The inner class SortById implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class SortById implements UserAction {

    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        if (tracker.getAllItems().length != 0) {
            tracker.sortItemsToId();
            System.out.printf("%sThe items is sort by id...%s%s", LS, LS, LS);
        } else {
            System.out.printf("%sThe list items is empty...%s%s", LS, LS, LS);
        }
    }

    @Override
    public String info() {
        return "Sort items by id";
    }
}

/**
 * The inner class SortByName implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class SortByName implements UserAction {

    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        if (tracker.getAllItems().length != 0) {
            tracker.sortItemsToName();
            System.out.printf("%sThe items is sort by name...%s%s", LS, LS, LS);
        } else {
            System.out.printf("%sThe list items is empty...%s%s", LS, LS, LS);
        }
    }

    @Override
    public String info() {
        return "Sort items by name";
    }
}

/**
 * The inner class AddItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class AddItem implements UserAction {

    @Override
    public int key() {
        return 4;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String result = "";
        String name = input.ask("Please, input name for item and press Enter: ");
        String description = input.ask("Please, input description for item and press Enter: ");
        name = name.trim();
        description = description.trim();
        if (name.length() != 0 && description.length() != 0) {
            tracker.addItem(new Item(name, description));
            result = "The item is be create...";
        } else {
            result = "Incorrect input value. Try again...";
        }
        System.out.printf("%s%s%s%s", LS, result, LS, LS);
    }

    @Override
    public String info() {
        return "Add new item";
    }
}

/**
 * The inner class SearchByName implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class SearchByName implements UserAction {

    @Override
    public int key() {
        return 5;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String word = input.ask("Please, input word for search items by name and press Enter: ");
        word = word.trim();
        if (word.length() != 0) {
            Item[] items = tracker.findByName(word);
            String line = String.format("%95s", " ").replace(" ", "-");
            System.out.printf("%s%-5s%-30s%-50s%-11s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS);
            System.out.println(line);
            if (items.length != 0) {
                for (Item item : items) {
                    System.out.printf("%-5.4s%-30.29s%-50.49s%-11.10s%s", item.getIdItem(), item.getName(), item.getDescription(), item.getDateCreate(), LS);
                }
            } else {
                System.out.printf("%sBase items is empty...%s%s", LS, LS, LS);
            }
            System.out.printf("%s%s%s", line, LS, LS);

        } else {
            System.out.printf("%s%s%s%s", LS, "Incorrect input value. Try again...", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Search items by name";
    }
}

/**
 * The inner class DeleteItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class DeleteItem implements UserAction {

    @Override
    public int key() {
        return 6;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for delete and press Enter: ");
        if (tracker.deleteItem(tracker.findById(id))) {
            System.out.printf("%s%s%s%s", LS, "The item is delete!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search. Delete is fail!", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Delete item by id";
    }
}

/**
 * The inner class EditItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        return 7;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for edit and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            String name = input.ask("Please, input name for edit item and press Enter: ");
            String description = input.ask("Please, input description for edit item and press Enter: ");
            name = name.trim();
            description = description.trim();
            if (name.length() != 0 && description.length() != 0) {
                tracker.editItem(new Item(name, description, id));
                System.out.printf("%s%s%s%s", LS, "The item is edit!", LS, LS);
            } else {
                System.out.printf("%s%s%s%s", LS, "Incorrect input value. Item is not edit. Try again...", LS, LS);
            }
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Edit item by id";
    }
}

/**
 * The inner class ShowCommentsItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class ShowCommentsItem implements UserAction {

    @Override
    public int key() {
        return 8;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for show comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            Comment[] comments = item.getComments();
            String line = String.format("%80s", " ").replace(" ", "-");
            System.out.printf("%s%-7s%-13s%-11s%-50s%s", LS, "INDEX", "DATE", "TIME", "COMMENT", LS);
            System.out.println(line);
            if (comments.length != 0) {
                for (int i = 0; i < comments.length; i++) {
                    String[] dateAndTime = comments[i].getDateAndTimeCreate().split(" ");
                    System.out.printf("%-7s%-13s%-11s%-50s%s", i + 1, dateAndTime[0], dateAndTime[1], comments[i].getComment(), LS);
                }
            } else {
                System.out.printf("%sList comments is empty...%s%s", LS, LS, LS);
            }
            System.out.printf("%s%s%s", line, LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Show all comments item`s by id";
    }
}

/**
 * The inner class AddCommentItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class AddCommentItem implements UserAction {

    @Override
    public int key() {
        return 9;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for add comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            String comment = input.ask("Please, input comment and press Enter: ");
            comment = comment.trim();
            if (comment.length() != 0) {
                tracker.addCommentToItem(item, comment);
                System.out.printf("%s%s%s%s", LS, "The comment is add!", LS, LS);
            } else {
                System.out.printf("%s%s%s%s", LS, "Incorrect input value. Comment is not add. Try again...", LS, LS);
            }
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Add comment to item by id";
    }
}

/**
 * The inner class DeleteCommentItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class DeleteCommentItem implements UserAction {

    @Override
    public int key() {
        return 10;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String result = "";
        String id = input.ask("Please, input id item for delete comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            String indexStr = input.ask("Please, input index comment for delete and press Enter: ");
            try {
                int index = Integer.parseInt(indexStr) - 1;
                result = tracker.deleteCommentToItem(item, index) ? "The comment is delete!" : "The comment is not delete!";
            } catch (NumberFormatException e) {
                result = "The comment is not delete!";
            }
            System.out.printf("%s%s%s%s", LS, result, LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Delete comment by index to item by id";
    }
}

/**
 * The inner class ClearCommentsItem implements UserAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class ClearCommentsItem implements UserAction {

    @Override
    public int key() {
        return 11;
    }

    @Override
    public void execuite(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for show comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            tracker.clearAllComments(item);
            System.out.printf("%s%s%s%s", LS, "The comments is clear!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }

    @Override
    public String info() {
        return "Clear cooments to item by id";
    }
}


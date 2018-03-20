package apavlov.menu;

import apavlov.database.Tracker;
import apavlov.input.Input;
import apavlov.models.Comment;
import apavlov.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The class StartUi for work with Tracker.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
public class MenuTracker {
    /**
     * The var object UserAction for exit program.
     */
    private UserAction exit = new BaseAction("Exit program", 11) {
        @Override
        public void execute(Tracker tracker, Input input) {
            System.out.printf("%sProgram is exit...", LS);
        }
    };

    /**
     * The constant var line separator.
     */
    private static final String LS = System.lineSeparator();

    /**
     * The var for work with console.
     */
    private final Input input;

    /**
     * The var work to base items.
     */
    private final Tracker tracker;

    /**
     * The array for save all menu program.
     */
    private List<UserAction> menuList = new ArrayList<>();

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
        return menuList.size();
    }

    /**
     * The method fill menu program.
     *
     * @param welcome text welcome for program;
     */
    public void fillMenu(String welcome) {
        System.out.printf("%s%s%s%s", LS, welcome, LS, LS);
        menuList.add(new ShowItems("Show all items", 1));
        menuList.add(new SortById("Sort items by id", 2));
        menuList.add(new SortByName("Sort items by name", 3));
        menuList.add(new AddItem("Add new item", 4));
        menuList.add(new SearchByName("Search items by name", 5));
        menuList.add(new DeleteItem("Delete item by id", 6));
        menuList.add(new EditItem("Edit item by id", 7));
        menuList.add(new ShowCommentsItem("Show all comments item`s by id", 8));
        menuList.add(new AddCommentItem("Add comment to item by id", 9));
        menuList.add(new ClearCommentsItem("Clear comments to item by id", 10));
        menuList.add(exit);
    }

    /**
     * The method out menu to console.
     */
    public void showMenuToConsole() {
        for (UserAction menu : menuList) {
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
        for (UserAction menu : menuList) {
            if (menu.key() == key) {
                menu.execute(this.tracker, this.input);
                break;
            }
        }
    }
}

/**
 * The inner class ShowItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class ShowItems extends BaseAction {
    /**
     * The constructor for class ShowItems.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    ShowItems(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String line = String.format("%107s", " ").replace(" ", "-");
        System.out.printf("%s%-8s%-30s%-50s%-20s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS);
        System.out.println(line);
        List<Item> list = tracker.getAllItems();
        if (!list.isEmpty()) {
            for (Item item : list) {
                System.out.printf("%-8.7s%-30.29s%-50.49s%-20.19s%s", item.getId(), item.getName(), item.getDescription(), item.getStringDate(), LS);
            }
        } else {
            System.out.printf("%sBase items is empty...%s%s", LS, LS, LS);
        }
        System.out.printf("%s%s%s", line, LS, LS);
    }
}

/**
 * The inner class SortById extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class SortById extends BaseAction {
    /**
     * The constructor for class SortById.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    SortById(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        tracker.sortItemsToId();
        System.out.printf("%sThe sort items by id is enable..%s%s", LS, LS, LS);
    }
}

/**
 * The inner class SortByName extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class SortByName extends BaseAction {
    /**
     * The constructor for class SortByName.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    SortByName(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        tracker.sortItemsToName();
        System.out.printf("%sThe sort items by name is enable..%s%s", LS, LS, LS);
    }
}

/**
 * The inner class AddItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class AddItem extends BaseAction {
    /**
     * The constructor for class AddItem.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    AddItem(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String result;
        String name = input.ask("Please, input name for item and press Enter: ");
        String description = input.ask("Please, input description for item and press Enter: ");
        name = name.trim();
        description = description.trim();
        if (name.length() != 0 && description.length() != 0) {
            result = tracker.addItem(new Item(name, description)) ? "The item is be create..." : "The item is not create.";
        } else {
            result = "Incorrect input value. Try again...";
        }
        System.out.printf("%s%s%s%s", LS, result, LS, LS);
    }
}

/**
 * The inner class SearchByName extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class SearchByName extends BaseAction {
    /**
     * The constructor for class SearchByName.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    SearchByName(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String word = input.ask("Please, input word for search items by name and press Enter: ");
        word = word.trim();
        if (word.length() != 0) {
            List<Item> items = tracker.findByName(word);
            String line = String.format("%107s", " ").replace(" ", "-");
            System.out.printf("%s%-8s%-30s%-50s%-20s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS);
            System.out.println(line);
            if (!items.isEmpty()) {
                for (Item item : items) {
                    System.out.printf("%-8.7s%-30.29s%-50.49s%-20.19s%s", item.getId(), item.getName(), item.getDescription(), item.getStringDate(), LS);
                }
            } else {
                System.out.printf("%sBase items is empty...%s%s", LS, LS, LS);
            }
            System.out.printf("%s%s%s", line, LS, LS);

        } else {
            System.out.printf("%s%s%s%s", LS, "Incorrect input value. Try again...", LS, LS);
        }
    }
}

/**
 * The inner class DeleteItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class DeleteItem extends BaseAction {
    /**
     * The constructor for class DeleteItem.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    DeleteItem(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for delete and press Enter: ").toUpperCase();
        if (tracker.deleteItem(id)) {
            System.out.printf("%s%s%s%s", LS, "The item is delete!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search. Delete is fail!", LS, LS);
        }
    }
}

/**
 * The inner class EditItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class EditItem extends BaseAction {
    /**
     * The constructor for class EditItem.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    EditItem(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for edit and press Enter: ").toUpperCase();
        List<Item> list = tracker.findById(id);
        if (!list.isEmpty()) {
            String name = input.ask("Please, input name for edit item and press Enter: ");
            String description = input.ask("Please, input description for edit item and press Enter: ");
            name = name.trim();
            description = description.trim();
            if (name.length() != 0 && description.length() != 0 && tracker.editItem(new Item(id, name, description))) {
                System.out.printf("%s%s%s%s", LS, "The item is edit!", LS, LS);
            } else {
                System.out.printf("%s%s%s%s", LS, "Incorrect input value. Item is not edit. Try again...", LS, LS);
            }
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }
}

/**
 * The inner class ShowCommentsItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class ShowCommentsItem extends BaseAction {
    /**
     * The constructor for class ShowCommentsItem.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    ShowCommentsItem(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for show comment and press Enter: ").toUpperCase();
        List<Item> list = tracker.findById(id);
        if (!list.isEmpty()) {
            List<Comment> comments = tracker.getAllComments(id);
            String line = String.format("%80s", " ").replace(" ", "-");
            System.out.printf("%s%-25s%-60s%s", LS, "DATE", "COMMENT", LS);
            System.out.println(line);
            if (!comments.isEmpty()) {
                for (Comment comment : comments) {
                    System.out.printf("%-25s%-60s%s", comment.getStringDate(), comment.getComment(), LS);
                }
            } else {
                System.out.printf("%sList comments is empty...%s%s", LS, LS, LS);
            }
            System.out.printf("%s%s%s", line, LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }
}

/**
 * The inner class AddCommentItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class AddCommentItem extends BaseAction {
    /**
     * The constructor for class AddCommentItem.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    AddCommentItem(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for add comment and press Enter: ").toUpperCase();
        List<Item> list = tracker.findById(id);
        if (!list.isEmpty()) {
            String comment = input.ask("Please, input comment and press Enter: ").trim();
            if (comment.length() != 0 && tracker.addCommentToItem(new Comment(comment), id)) {
                System.out.printf("%s%s%s%s", LS, "The comment is add!", LS, LS);
            } else {
                System.out.printf("%s%s%s%s", LS, "Incorrect input value. Comment is not add. Try again...", LS, LS);
            }
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }
}

/**
 * The inner class ClearCommentsItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
class ClearCommentsItem extends BaseAction {

    /**
     * The constructor for class ClearCommentsItem.
     *
     * @param name - name menu;
     * @param key  - number menu;
     */
    ClearCommentsItem(String name, int key) {
        super(name, key);
    }

    /**
     * The method for run work menu.
     *
     * @param tracker - link object Tracker;
     * @param input   - link object Input;
     */
    public void execute(Tracker tracker, Input input) {
        String id = input.ask("Please, input id item for show comment and press Enter: ").toUpperCase();
        List<Item> list = tracker.findById(id);
        if (!list.isEmpty()) {
            tracker.clearAllComments(id);
            System.out.printf("%s%s%s%s", LS, "The comments is clear!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }
}


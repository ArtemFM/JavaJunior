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
 * @since 12.09.2017
 */
public class MenuTracker {

    /**
     * The var object UserAction for exit program.
     */
    private UserAction exit = new BaseAction("Exit program", 12) {
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
    private Input input;

    /**
     * The var work to base items.
     */
    private Tracker tracker;

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
        System.out.printf("%s%s%s", welcome, LS, LS);
        menuList.add(new ShowItems("Show all items", 1));
        menuList.add(new SortById("Sort items by id", 2));
        menuList.add(new SortByName("Sort items by name", 3));
        menuList.add(new AddItem("Add new item", 4));
        menuList.add(new SearchByName("Search items by name", 5));
        menuList.add(new DeleteItem("Delete item by id", 6));
        menuList.add(new EditItem("Edit item by id", 7));
        menuList.add(new ShowCommentsItem("Show all comments item`s by id", 8));
        menuList.add(new AddCommentItem("Add comment to item by id", 9));
        menuList.add(new DeleteCommentItem("Delete comment by index to item by id", 10));
        menuList.add(new ClearCommentsItem("Clear comments to item by id", 11));
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
 * @since 28.08.2017
 */
class ShowItems extends BaseAction {

    /**
     * The constructor for class ShowItems.
     *
     * @param name - name menu;
     * @param key - number menu;
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
        String line = String.format("%95s", " ").replace(" ", "-");
        System.out.printf("%s%-5s%-30s%-50s%-11s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS);
        System.out.println(line);
        if (tracker.getAllItems().size() != 0) {
            for (Item item : tracker.getAllItems()) {
                System.out.printf("%-5.4s%-30.29s%-50.49s%-11.10s%s", item.getIdItem(), item.getName(), item.getDescription(), item.getDateCreate(), LS);
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
 * @since 28.08.2017
 */
class SortById extends BaseAction {

    /**
     * The constructor for class SortById.
     *
     * @param name - name menu;
     * @param key - number menu;
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
        if (tracker.getAllItems().size() != 0) {
            tracker.sortItemsToId();
            System.out.printf("%sThe items is sort by id...%s%s", LS, LS, LS);
        } else {
            System.out.printf("%sThe list items is empty...%s%s", LS, LS, LS);
        }
    }
}

/**
 * The inner class SortByName extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class SortByName extends BaseAction {

    /**
     * The constructor for class SortByName.
     *
     * @param name - name menu;
     * @param key - number menu;
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
        if (tracker.getAllItems().size() != 0) {
            tracker.sortItemsToName();
            System.out.printf("%sThe items is sort by name...%s%s", LS, LS, LS);
        } else {
            System.out.printf("%sThe list items is empty...%s%s", LS, LS, LS);
        }
    }
}

/**
 * The inner class AddItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class AddItem extends BaseAction {

    /**
     * The constructor for class AddItem.
     *
     * @param name - name menu;
     * @param key - number menu;
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
            tracker.addItem(new Item(name, description));
            result = "The item is be create...";
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
 * @since 28.08.2017
 */
class SearchByName extends BaseAction {

    /**
     * The constructor for class SearchByName.
     *
     * @param name - name menu;
     * @param key - number menu;
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
            String line = String.format("%95s", " ").replace(" ", "-");
            System.out.printf("%s%-5s%-30s%-50s%-11s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS);
            System.out.println(line);
            if (items.size() != 0) {
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
}

/**
 * The inner class DeleteItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class DeleteItem extends BaseAction {

    /**
     * The constructor for class DeleteItem.
     *
     * @param name - name menu;
     * @param key - number menu;
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
        String id = input.ask("Please, input id item for delete and press Enter: ");
        if (tracker.deleteItem(tracker.findById(id))) {
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
 * @since 28.08.2017
 */
class EditItem extends BaseAction {

    /**
     * The constructor for class EditItem.
     *
     * @param name - name menu;
     * @param key - number menu;
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
}

/**
 * The inner class ShowCommentsItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class ShowCommentsItem extends BaseAction {

    /**
     * The constructor for class ShowCommentsItem.
     *
     * @param name - name menu;
     * @param key - number menu;
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
        String id = input.ask("Please, input id item for show comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            List<Comment> comments = item.getComments();
            String line = String.format("%80s", " ").replace(" ", "-");
            System.out.printf("%s%-7s%-13s%-11s%-50s%s", LS, "INDEX", "DATE", "TIME", "COMMENT", LS);
            System.out.println(line);
            if (comments.size() != 0) {
                for (int i = 0; i < comments.size(); i++) {
                    String[] dateAndTime = comments.get(i).getDateAndTimeCreate().split(" ");
                    System.out.printf("%-7s%-13s%-11s%-50s%s", i + 1, dateAndTime[0], dateAndTime[1], comments.get(i).getComment(), LS);
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
 * @since 28.08.2017
 */
class AddCommentItem extends BaseAction {

    /**
     * The constructor for class AddCommentItem.
     *
     * @param name - name menu;
     * @param key - number menu;
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
}

/**
 * The inner class DeleteCommentItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class DeleteCommentItem extends BaseAction {

    /**
     * The constructor for class DeleteCommentItem.
     *
     * @param name - name menu;
     * @param key - number menu;
     */
    DeleteCommentItem(String name, int key) {
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
}

/**
 * The inner class ClearCommentsItem extends BaseAction.
 *
 * @author Pavlov Artem
 * @since 28.08.2017
 */
class ClearCommentsItem extends BaseAction {

    /**
     * The constructor for class ClearCommentsItem.
     *
     * @param name - name menu;
     * @param key - number menu;
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
        String id = input.ask("Please, input id item for show comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            tracker.clearAllComments(item);
            System.out.printf("%s%s%s%s", LS, "The comments is clear!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }
}


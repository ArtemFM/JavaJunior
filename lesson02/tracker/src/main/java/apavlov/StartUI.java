package apavlov;

import apavlov.database.Tracker;
import apavlov.input.ConsoleInput;
import apavlov.input.Input;
import apavlov.models.Comment;
import apavlov.models.Item;

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
    private void init() {
        String key = "";
        System.out.printf("Welcome to program Tracker...%s%s", LS, LS);
        fillActions();
        do {
            showMenu();
            key = input.ask(String.format("%sPlease, input number menu and press Enter: ", LS));
            switch (key) {
                case "1":
                    menuShowAllItems(tracker.getAllItems());
                    break;
                case "2":
                    menuSortById();
                    break;
                case "3":
                    menuSortByName();
                    break;
                case "4":
                    menuAddItem();
                    break;
                case "5":
                    menuSearchByName();
                    break;
                case "6":
                    menuDeleteItemById();
                    break;
                case "7":
                    menuEditItemById();
                    break;
                case "8":
                    menuShowCommentsByIdItem();
                    break;
                case "9":
                    menuAddCommentByIdItem();
                    break;
                case "10":
                    menuDeleteCommentByIndex();
                    break;
                case "11":
                    menuClearCommentsItemById();
                    break;
                case "12":
                    System.out.printf("%sProgramm is exit...", LS);
                    break;
                default:
                    System.out.printf("%sERROR: Incorrect data. Try again...%s%s", LS, LS, LS);
                    break;
            }
        } while (!key.equals("12"));
    }

    /**
     * The method print all items to console.
     *
     * @param items - array items;
     */
    public void menuShowAllItems(Item[] items) {
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
    }

    /**
     * The method sort all items by id.
     */
    public void menuSortById() {
        if (tracker.getAllItems().length != 0) {
            tracker.sortItemsToId();
            System.out.printf("%sThe items is sort by id...%s%s", LS, LS, LS);
        } else {
            System.out.printf("%sThe list items is empty...%s%s", LS, LS, LS);
        }
    }

    /**
     * The method sort all items by name.
     */
    public void menuSortByName() {
        if (tracker.getAllItems().length != 0) {
            tracker.sortItemsToName();
            System.out.printf("%sThe items is sort by name...%s%s", LS, LS, LS);
        } else {
            System.out.printf("%sThe list items is empty...%s%s", LS, LS, LS);
        }
    }

    /**
     * The method add new item.
     */
    public void menuAddItem() {
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

    /**
     * The method search items by string to name.
     */
    public void menuSearchByName() {
        String word = input.ask("Please, input word for search items by name and press Enter: ");
        word = word.trim();
        if (word.length() != 0) {
            Item[] items = tracker.findByName(word);
            menuShowAllItems(items);
        } else {
            System.out.printf("%s%s%s%s", LS, "Incorrect input value. Try again...", LS, LS);
        }
    }

    /**
     * The method delete item by id.
     */
    public void menuDeleteItemById() {
        String id = input.ask("Please, input id item for delete and press Enter: ");
        if (tracker.deleteItem(tracker.findById(id))) {
            System.out.printf("%s%s%s%s", LS, "The item is delete!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search. Delete is fail!", LS, LS);
        }
    }

    /**
     * The method for edit item.
     */
    public void menuEditItemById() {
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

    /**
     * The method add comment to item by id.
     */
    public void menuAddCommentByIdItem() {
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

    /**
     * The method print to console all comments item by id.
     */
    public void menuShowCommentsByIdItem() {
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

    /**
     * The method delete comment item`s by index.
     *
     * @throws NumberFormatException - parse string to int;
     */
    public void menuDeleteCommentByIndex() throws NumberFormatException {
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

    /**
     * The method delete all comments item by id.
     */
    public void menuClearCommentsItemById() {
        String id = input.ask("Please, input id item for show comment and press Enter: ");
        Item item = tracker.findById(id);
        if (item != null) {
            tracker.clearAllComments(item);
            System.out.printf("%s%s%s%s", LS, "The comments is clear!", LS, LS);
        } else {
            System.out.printf("%s%s%s%s", LS, "The item is not search!", LS, LS);
        }
    }

    /**
     * The method fill array info menu.
     */
    public void fillActions() {
        menu = new String[12];
        menu[0] = "Show all items;";
        menu[1] = "Sort items by id;";
        menu[2] = "Sort items by name;";
        menu[3] = "Add new item;";
        menu[4] = "Search items by name;";
        menu[5] = "Delete item by id;";
        menu[6] = "Edit item by id;";
        menu[7] = "Show all comments item`s by id;";
        menu[8] = "Add comment to item by id;";
        menu[9] = "Delete comment by index to item by id;";
        menu[10] = "Clear cooments to item by id;";
        menu[11] = "Exit program.";
    }

    /**
     * The method print menu to console.
     */
    public void showMenu() {
        for (int i = 0; i < menu.length; i++) {
            System.out.printf("%2s. %s%s", i + 1, menu[i], System.lineSeparator());
        }
    }

    /**
     * The method is point enter program.
     *
     * @param args - data for console;
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}

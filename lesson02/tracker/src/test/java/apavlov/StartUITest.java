package apavlov;

import apavlov.database.Tracker;
import apavlov.input.StubInput;
import apavlov.models.Item;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class StartUI.
 *
 * @author Pavlov Artem
 * @since 23.08.2017
 */
public class StartUITest {

    /**
     * The var for get string.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * The var is lineseperetor (new line).
     */
    private static final String LS = System.lineSeparator();

    /**
     * The war is text welcome program.
     */
    private String welcomeText = String.format("Welcome to program Tracker...%s%s", LS, LS);

    /**
     * The var is text full menu.
     */
    private String menuText = String.format("%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s%2s. %s%s",
            1, "Show all items;", LS,
            2, "Sort items by id;", LS,
            3, "Sort items by name;", LS,
            4, "Add new item;", LS,
            5, "Search items by name;", LS,
            6, "Delete item by id;", LS,
            7, "Edit item by id;", LS,
            8, "Show all comments item`s by id;", LS,
            9, "Add comment to item by id;", LS,
            10, "Delete comment by index to item by id;", LS,
            11, "Clear cooments to item by id;", LS,
            12, "Exit program.", LS);

    /**
     * The var is text exit program.
     */
    private String exitText = String.format("%sProgramm is exit...", LS);

    /**
     * The test print all items to console.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenShowAllItems() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item item = new Item("Artem", "student", "001");
        tracker.addItem(item);
        new StartUI(new StubInput(new String[]{"1", "12"}), tracker).init();

        String line = String.format("%95s", " ").replace(" ", "-");
        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%s%-5s%-30s%-50s%-11s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS));
        sb.append(String.format("%s%s", line, LS));
        sb.append(String.format("%-5.4s%-30.29s%-50.49s%-11.10s%s", item.getIdItem(), item.getName(), item.getDescription(), item.getDateCreate(), LS));
        sb.append(String.format("%s%s%s", line, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check sort by id.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenSortById() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item oneItem = new Item("Artem", "student", "002");
        Item twoItem = new Item("Sergey", "student", "001");
        tracker.addItem(oneItem);
        tracker.addItem(twoItem);
        new StartUI(new StubInput(new String[]{"2", "12"}), tracker).init();
        assertThat(twoItem.getName(), is(tracker.getAllItems()[0].getName()));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe items is sort by id...%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check sort by name.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenSortByName() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item oneItem = new Item("Timur", "student", "001");
        Item twoItem = new Item("Sergey", "student", "002");
        tracker.addItem(oneItem);
        tracker.addItem(twoItem);
        new StartUI(new StubInput(new String[]{"3", "12"}), tracker).init();
        assertThat(twoItem.getName(), is(tracker.getAllItems()[0].getName()));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe items is sort by name...%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check menu add new item.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenMenuAddItem() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        new StartUI(new StubInput(new String[]{"4", "Artem", "student", "12"}), tracker).init();
        assertThat("Artem", is(tracker.getAllItems()[0].getName()));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe item is be create...%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check search items by name.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenSearchByName() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item oneItem = new Item("Alexey", "student", "001");
        Item twoItem = new Item("Alexandr", "student", "002");
        Item threeItem = new Item("Anton", "student", "003");
        tracker.addItem(oneItem);
        tracker.addItem(twoItem);
        tracker.addItem(threeItem);
        new StartUI(new StubInput(new String[]{"5", "alex", "12"}), tracker).init();

        String line = String.format("%95s", " ").replace(" ", "-");
        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%s%-5s%-30s%-50s%-11s%s", LS, "ID", "NAME", "DESCRIPTION", "DATE", LS));
        sb.append(String.format("%s%s", line, LS));
        sb.append(String.format("%-5.4s%-30.29s%-50.49s%-11.10s%s", oneItem.getIdItem(), oneItem.getName(), oneItem.getDescription(), oneItem.getDateCreate(), LS));
        sb.append(String.format("%-5.4s%-30.29s%-50.49s%-11.10s%s", twoItem.getIdItem(), twoItem.getName(), twoItem.getDescription(), twoItem.getDateCreate(), LS));
        sb.append(String.format("%s%s%s", line, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check delete item with base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteItem() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        tracker.addItem(new Item("Artem", "student", "001"));
        tracker.addItem(new Item("Pasha", "student", "002"));
        new StartUI(new StubInput(new String[]{"6", "001", "12"}), tracker).init();
        assertThat(1, is(tracker.getAllItems().length));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe item is delete!%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check edit item by id.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenEditItemById() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        tracker.addItem(new Item("Artem", "student", "001"));
        new StartUI(new StubInput(new String[]{"7", "001", "Anton", "student", "12"}), tracker).init();
        assertThat("Anton", is(tracker.getAllItems()[0].getName()));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe item is edit!%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check add comment by id item`s.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddCommentByItemId() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        tracker.addItem(new Item("Artem", "student", "001"));
        new StartUI(new StubInput(new String[]{"9", "001", "make java", "12"}), tracker).init();
        assertThat("make java", is(tracker.getAllItems()[0].getComments()[0].getComment()));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe comment is add!%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check print all comments item`s by id.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenShowAllCommentsItemById() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item item = new Item("Artem", "student", "001");
        tracker.addItem(item);
        tracker.addCommentToItem(item, "make java");
        new StartUI(new StubInput(new String[]{"8", "001", "12"}), tracker).init();

        String line = String.format("%80s", " ").replace(" ", "-");
        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%s%-7s%-13s%-11s%-50s%s", LS, "INDEX", "DATE", "TIME", "COMMENT", LS));
        sb.append(String.format("%s%s", line, LS));
        String[] dateAndTime = item.getComments()[0].getDateAndTimeCreate().split(" ");
        sb.append(String.format("%-7s%-13s%-11s%-50s%s", 1, dateAndTime[0], dateAndTime[1], item.getComments()[0].getComment(), LS));
        sb.append(String.format("%s%s%s", line, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check delete comment with item by index.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteCommentByIndex() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item item = new Item("Artem", "student", "001");
        tracker.addItem(item);
        tracker.addCommentToItem(item, "make java");
        new StartUI(new StubInput(new String[]{"10", "001", "1", "12"}), tracker).init();
        assertThat(0, is(tracker.getSizeComments(item)));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe comment is delete!%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check delete all comments with item by id.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenClearAllCommentsItemById() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item item = new Item("Artem", "student", "001");
        tracker.addItem(item);
        tracker.addCommentToItem(item, "make java");
        tracker.addCommentToItem(item, "play soccer");
        new StartUI(new StubInput(new String[]{"11", "001", "12"}), tracker).init();
        assertThat(0, is(tracker.getSizeComments(item)));

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(String.format("%sThe comments is clear!%s%s", LS, LS, LS));
        sb.append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * The test check exit for program.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenExitProgram() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new StartUI(new StubInput(new String[]{"12"}), new Tracker()).init();

        sb.delete(0, sb.length());
        sb.append(welcomeText).append(menuText);
        sb.append(exitText);
        assertThat(out.toString(), is(sb.toString()));
    }
}

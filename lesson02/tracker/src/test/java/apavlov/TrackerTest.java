package apavlov;

import apavlov.database.Tracker;
import apavlov.models.Item;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Tracker.
 *
 * @author Pavlov Artem
 * @since 25.07.2017
 */
public class TrackerTest {
    /**
     * The test check add item to tracker.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenAddNewItemInEmptyTracker() throws Exception {
        Tracker base = new Tracker();
        Item item = new Item("artem", "student");
        base.addItem(item);

        assertThat(base.getAllItems()[0], is(item));
    }

    /**
     * The test check search item to key (id item).
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenFindById() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("test1", "testing", "3EA"));
        base.addItem(new Item("test2", "testing", "3E8"));
        base.addItem(new Item("test3", "testing", "3EC"));
        assertThat(base.findById("3ec"), is(base.getAllItems()[2]));
    }

    /**
     * The test check search item to name.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenFindByName() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("artem", "testing", "3EA"));
        base.addItem(new Item("anton", "testing", "3E8"));
        base.addItem(new Item("sergey", "testing", "3EC"));
        Item[] item = new Item[1];
        item[0] = base.getAllItems()[0];
        assertThat(base.findByName("ART"), is(item));
    }

    /**
     * The test check return`s all data.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenGetAllItems() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("artem", "testing", "3EA"));
        base.addItem(new Item("anton", "testing", "3E8"));
        base.addItem(new Item("sergey", "testing", "3EC"));
        assertThat(base.getAllItems().length, is(3));
    }

    /**
     * The test search item to index.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenGetItemToIndex() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("artem", "testing", "3EA"));
        base.addItem(new Item("anton", "testing", "3E8"));
        base.addItem(new Item("sergey", "testing", "3EC"));
        Item item = base.getAllItems()[2];
        assertThat(base.getItemToIndex(2), is(item));
    }

    /**
     * The test for check delete item to array.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenDeleteItem() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("artem", "testing", "3EA"));
        base.addItem(new Item("anton", "testing", "3E8"));
        base.addItem(new Item("sergey", "testing", "3EC"));
        Item[] item = {base.getAllItems()[0], base.getAllItems()[2]};
        base.deleteItem(base.getAllItems()[1]);
        assertThat(base.getAllItems(), is(item));
    }

    /**
     * The test for check sort to name item.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenSortItemsToName() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("sergey", "testing", "3EA"));
        base.addItem(new Item("pasha", "testing", "3E8"));
        base.addItem(new Item("artem", "testing", "3EC"));
        Item[] item = {base.getAllItems()[2], base.getAllItems()[1], base.getAllItems()[0]};
        base.sortItemsToName();
        assertThat(base.getAllItems(), is(item));
    }

    /**
     * The test for check sort item to id.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenSortItemsToId() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("sergey", "testing", "3E7"));
        base.addItem(new Item("pasha", "testing", "3E5"));
        base.addItem(new Item("artem", "testing", "3E6"));
        Item[] item = {base.getAllItems()[1], base.getAllItems()[2], base.getAllItems()[0]};
        base.sortItemsToId();
        assertThat(base.getAllItems(), is(item));
    }

    /**
     * The test for check edit item to link.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenEditItem() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("sergey", "testing", "3E7"));
        base.addItem(new Item("pasha", "testing", "3E5"));
        base.addItem(new Item("artem", "testing", "3E6"));
        Item item = new Item("ivan", "testing", "3e5");
        base.editItem(item);
        assertThat(base.getAllItems()[1], is(item));
    }

    /**
     * The test for check add comments to item.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenAddCommentToItem() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("sergey", "testing", "3E7"));
        String text = "vualya";
        base.addCommentToItem(base.getAllItems()[0], text);
        assertThat(base.getAllItems()[0].getComments()[0].getComment(), is(text));
    }

    /**
     * The test for check delete comments to item.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenDeleteCommentToItem() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("sergey", "testing", "3E7"));
        base.addCommentToItem(base.getAllItems()[0], "001");
        base.addCommentToItem(base.getAllItems()[0], "002");
        base.addCommentToItem(base.getAllItems()[0], "003");
        base.deleteCommentToItem(base.getAllItems()[0], 1);
        assertThat(base.getAllItems()[0].getComments().length, is(2));
    }

    /**
     * The test for check clear all comment to item.
     *
     * @throws Exception - check any error;
     */
    @Test
    public void whenClearAllComments() throws Exception {
        Tracker base = new Tracker();
        base.addItem(new Item("sergey", "testing", "3E7"));
        base.addCommentToItem(base.getAllItems()[0], "001");
        base.addCommentToItem(base.getAllItems()[0], "002");
        base.addCommentToItem(base.getAllItems()[0], "003");
        assertThat(base.getAllItems()[0].getComments().length, is(3));
        base.clearAllComments(base.getAllItems()[0]);
        assertThat(base.getAllItems()[0].getComments().length, is(0));
    }
}

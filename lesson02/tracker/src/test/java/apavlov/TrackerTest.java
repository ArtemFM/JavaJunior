package apavlov;

import apavlov.database.Tracker;
import apavlov.models.Item;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Tracker.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
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
        assertThat(base.getAllItems().get(0), is(item));
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
        assertThat(base.findById("3ec"), is(base.getAllItems().get(2)));
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
        List<Item> resultList = Arrays.asList(new Item[]{base.getAllItems().get(0), base.getAllItems().get(1)});
        assertThat(base.findByName("A"), is(resultList));
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
        assertThat(base.getAllItems().size(), is(3));
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
        List<Item> listResult = Arrays.asList(new Item[]{base.getAllItems().get(0), base.getAllItems().get(2)});
        base.deleteItem(base.getAllItems().get(1));
        assertThat(base.getAllItems(), is(listResult));
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
        List<Item> resultList = Arrays.asList(new Item[]{base.getAllItems().get(2), base.getAllItems().get(1), base.getAllItems().get(0)});
        base.sortItemsToName();
        assertThat(base.getAllItems(), is(resultList));
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
        List<Item> resultList = Arrays.asList(new Item[]{base.getAllItems().get(1), base.getAllItems().get(2), base.getAllItems().get(0)});
        base.sortItemsToId();
        assertThat(base.getAllItems(), is(resultList));
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
        Item item = new Item("ivan", "testing", "3E5");
        base.editItem(item);
        assertThat(base.getAllItems().get(1), is(item));
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
        base.addCommentToItem(base.getAllItems().get(0), text);
        assertThat(base.getAllItems().get(0).getComments().get(0).getComment(), is(text));
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
        base.addCommentToItem(base.getAllItems().get(0), "001");
        base.addCommentToItem(base.getAllItems().get(0), "002");
        base.addCommentToItem(base.getAllItems().get(0), "003");
        base.deleteCommentToItem(base.getAllItems().get(0), 1);
        assertThat(base.getAllItems().get(0).getComments().size(), is(2));
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
        base.addCommentToItem(base.getAllItems().get(0), "001");
        base.addCommentToItem(base.getAllItems().get(0), "002");
        base.addCommentToItem(base.getAllItems().get(0), "003");
        assertThat(base.getAllItems().get(0).getComments().size(), is(3));
        base.clearAllComments(base.getAllItems().get(0));
        assertThat(base.getAllItems().get(0).getComments().size(), is(0));
    }
}

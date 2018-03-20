package apavlov.database;

import apavlov.models.Comment;
import apavlov.models.Item;
import apavlov.util.NameConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Tracker for work items.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
public class Tracker {
    /**
     * The link object work to base SQL.
     */
    private final BaseSQL base;

    /**
     * The var - name row for sort.
     */
    private NameConstant sortByRow;

    /**
     * The constructor for class Tracker.
     *
     * @param base - link object work to base SQL;
     */
    public Tracker(BaseSQL base) {
        this.base = base;
        this.sortByRow = NameConstant.ITEM_ROW_PK;
    }

    /**
     * The method add new item to tracker.
     *
     * @param item - item for add;
     * @return treu, if is add or false;
     */
    public boolean addItem(Item item) {
        boolean result;
        if (result = item != null && this.base != null) {
            result = this.base.addItemToSQL(item);
        }
        return result;
    }

    /**
     * The method find items by id.
     *
     * @param key - id item;
     * @return list finding items;
     */
    public List<Item> findById(String key) {
        List<Item> result = new ArrayList<>();
        if (key != null && !key.equals("")) {
            result = this.base.findBy(key, NameConstant.ITEM_ROW_PK.toString(), this.sortByRow.toString());
        }
        return result;
    }

    /**
     * The method find items by name.
     *
     * @param text - text for search;
     * @return list finding items;
     */
    public List<Item> findByName(String text) {
        List<Item> returnItems = new ArrayList<>();
        if (text != null && !text.equals("")) {
            returnItems = this.base.findBy(text, NameConstant.ITEM_ROW_NAME.toString(), this.sortByRow.toString());
        }
        return returnItems;
    }

    /**
     * The method return all items.
     *
     * @return list all items to system;
     */
    public List<Item> getAllItems() {
        return this.base.getFullItems(this.sortByRow.toString());
    }

    /**
     * The method delete item by id.
     *
     * @param id - id item;
     * @return true, if is delete or false;
     */
    public boolean deleteItem(String id) {
        boolean result;
        List<Item> list = this.findById(id);
        if (result = !list.isEmpty()) {
            result = this.base.removeLineToSQL(NameConstant.TABLE_ITEM.toString(), NameConstant.ITEM_ROW_PK.toString(), id);
            if (result) {
                this.base.removeLineToSQL(NameConstant.TABLE_COMMENTS.toString(), NameConstant.COMMENT_ROW_FK.toString(), id);
            }
        }
        return result;
    }

    /**
     * The method set flag (name row = name) for sort.
     */
    public void sortItemsToName() {
        if (this.sortByRow != NameConstant.ITEM_ROW_NAME) {
            this.sortByRow = NameConstant.ITEM_ROW_NAME;
        }
    }

    /**
     * The method set flag (name row = id_item) for sort.
     */
    public void sortItemsToId() {
        if (this.sortByRow != NameConstant.ITEM_ROW_PK) {
            this.sortByRow = NameConstant.ITEM_ROW_PK;
        }
    }

    /**
     * The method edit (update) item by id.
     *
     * @param item - item for set;
     * @return true, if item is found and update or false;
     */
    public boolean editItem(Item item) {
        boolean result;
        if (result = item != null) {
            result = this.base.editItemToSQL(NameConstant.TABLE_ITEM.toString(), item);
        }
        return result;
    }

    /**
     * The method return all comments by id item.
     *
     * @param idItem - id item;
     * @return list all comments item`s;
     */
    public List<Comment> getAllComments(String idItem) {
        return this.base.getFullComments(idItem);
    }

    /**
     * The method add new comment to item by id item.
     *
     * @param comment - comment;
     * @param idItem  - id item;
     * @return true, if item is found and comment is add or false;
     */
    public boolean addCommentToItem(Comment comment, String idItem) {
        return this.base.addCommentToItemSQL(comment, idItem);
    }

    /**
     * The method remove all comments item.
     *
     * @param idItem - id item;
     */
    public void clearAllComments(String idItem) {
        this.base.removeLineToSQL(NameConstant.TABLE_COMMENTS.toString(), NameConstant.COMMENT_ROW_FK.toString(), idItem);
    }
}

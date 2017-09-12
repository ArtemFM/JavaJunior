package apavlov.database;

import apavlov.models.Comment;
import apavlov.models.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Tracker for work items.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class Tracker {
    /**
     * The count add items.
     */
    private int countAdd;

    /**
     * The array save items.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * The method generate unique id.
     *
     * @return return unique id to String;
     */
    private String generateId() {
        String[] code = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuilder result = new StringBuilder();
        int number = 1000 + countAdd;
        while (number != 0) {
            result.append(code[number % 16]);
            number /= 16;
        }
        return result.reverse().toString();
    }

    /**
     * The method add new item to array.
     *
     * @param item - item;
     * @return return item;
     */
    public Item addItem(Item item) {
        if (item != null) {
            if (item.getIdItem() == null) {
                item.setIdItem(generateId());
            }
            this.items.add(item);
            countAdd++;
        }
        return item;
    }

    /**
     * The method search item to id (key).
     *
     * @param key - id item for search;
     * @return return searching item or null;
     */
    public Item findById(String key) {
        Item result = null;
        if (key != null && !key.equals("")) {
            for (Item item : items) {
                if (item.getIdItem().toLowerCase().equals(key.toLowerCase())) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * The method search items to name or substring.
     *
     * @param text = offer for search;
     * @return return list searching items;
     */
    public List<Item> findByName(String text) {
        List<Item> returnItems = new ArrayList<>();
        if (text != null && !text.equals("")) {
            for (Item item : items) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    returnItems.add(item);
                }
            }
        }
        return returnItems;
    }

    /**
     * The method return list items.
     *
     * @return return list items;
     */
    public List<Item> getAllItems() {
        return this.items;
    }

    /**
     * This method delete item for base.
     *
     * @param item - object type Item;
     * @return boolean: true - is delete; false - is not delete;
     */
    public boolean deleteItem(Item item) {
        return this.items.remove(item);
    }

    /**
     * The method for sort object to name.
     */
    public void sortItemsToName() {
        if (this.items.size() > 1) {
            this.items.sort(items.get(0).getSortName());
        }
    }

    /**
     * The method for sort object to id.
     */
    public void sortItemsToId() {
        if (this.items.size() > 1) {
            this.items.sort(items.get(0).getSortId());
        }
    }

    /**
     * The method edit item to link.
     *
     * @param item - link object;
     * @return boolean: true - is edit item; false - is not item;
     */
    public boolean editItem(Item item) {
        boolean result = false;
        if (item != null) {
            item.setIdItem(item.getIdItem().toUpperCase());
            for (int i = 0; i < this.items.size(); i++) {
                if (this.items.get(i).getIdItem().equals(item.getIdItem())) {
                    this.items.set(i, item);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * The method for sort comments to date and time.
     *
     * @param item - object type Item;
     * @return false - is not sort; true - is sort;
     */
    private boolean sortCommentsToDate(Item item) {
        boolean result = false;
        if (item != null && item.getComments().size() > 1) {
            item.getComments().sort(item.getComments().get(0).getSortDate());
            result = true;
        }
        return result;
    }

    /**
     * The method add comment to Item.
     *
     * @param item    - link to object;
     * @param comment - text comment`s;
     * @return return boolean: true - is add; false = is not add;
     */
    public boolean addCommentToItem(Item item, String comment) {
        return item != null && item.getComments().add(new Comment(comment)) && sortCommentsToDate(item);
    }

    /**
     * The method delete comment in item to index and link.
     *
     * @param item  - object type item;
     * @param index - index comment to array;
     * @return boolean: true - is delete; false - is not delete;
     */
    public boolean deleteCommentToItem(Item item, int index) {
        boolean result = false;
        if (item != null && index >= 0 && item.getComments().size() > index) {
            item.getComments().remove(index);
            result = true;
        }
        return result;
    }

    /**
     * The method clear all comments in to item.
     *
     * @param item - link on the object;
     */
    public void clearAllComments(Item item) {
        item.getComments().clear();
    }

    /**
     * This method return count comments to item.
     *
     * @param item - object type Item;
     * @return return lenght array comments;
     */
    public int getSizeComments(Item item) {
        return item.getComments().size();
    }
}

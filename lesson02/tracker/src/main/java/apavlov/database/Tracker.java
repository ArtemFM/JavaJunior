package apavlov.database;

import apavlov.models.Comment;
import apavlov.models.Item;

import java.util.Arrays;

/**
 * The class Tracker for work items.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class Tracker {
    /**
     * The count add items.
     */
    private int countAdd;

    /**
     * The array save items.
     */
    private Item[] items;

    /**
     * The var size base Tracker.
     */
    private int size;

    /**
     * The default constructor for class Tracker with length array 100.
     */
    public Tracker() {
        this.items = new Item[10];
    }

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
     * The method dynamic increase array.
     */
    private void increaseLength() {
        if (size == items.length) {
            items = Arrays.copyOf(items, (int) ((double) (items.length) * 1.5));
        }
    }

    /**
     * The method dynamic reduce array.
     */
    private void reduceLength() {
        if ((double) (items.length) / (double) size >= 1.6) {
            items = Arrays.copyOf(items, (int) ((double) items.length / 1.5));
        }
    }

    /**
     * The method add new item to array.
     *
     * @param item - item;
     * @return return item;
     */
    public Item addItem(Item item) {
        if (item != null) {
            increaseLength();
            if (item.getIdItem() == null) {
                item.setIdItem(generateId());
            }
            this.items[size++] = item;
            countAdd++;
        }
        return item;
    }

    /**
     * The method search item to id (key).
     *
     * @param key - id titem for search;
     * @return return searching item or null;
     */
    public Item findById(String key) {
        Item result = null;
        if (!key.equals("") && key != null) {
            for (int index = 0; index < size; index++) {
                if (items[index].getIdItem().toLowerCase().equals(key.toLowerCase())) {
                    result = items[index];
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
     * @return return array serching items;
     */
    public Item[] findByName(String text) {
        Item[] returnItems = new Item[this.size];
        int position = 0;
        if (!text.equals("") && text != null) {
            for (int index = 0; index < size; index++) {
                if (items[index].getName().toLowerCase().contains(text.toLowerCase())) {
                    returnItems[position++] = items[index];
                }
            }
        }
        return Arrays.copyOf(returnItems, position);
    }

    /**
     * The method return copy array data.
     *
     * @return return acoping array;
     */
    public Item[] getAllItems() {
        return Arrays.copyOf(items, size);
    }

    /**
     * The method return item to index for array.
     *
     * @param index = index array;
     * @return return item;
     */
    public Item getItemToIndex(int index) {
        Item item = null;
        if (index < size) {
            item = this.items[index];
        }
        return item;
    }

    /**
     * The method get index to item.
     *
     * @param item = object item;
     * @return return index to array items;
     */
    private int getIndexToItems(Item item) {
        int result = -1;
        if (item != null) {
            for (int index = 0; index < size; index++) {
                if (item.getIdItem().toUpperCase().equals(items[index].getIdItem().toUpperCase())) {
                    result = index;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method delete item for base.
     *
     * @param item - object type Item;
     * @return boolean: true - is delete; false - is not delete;
     */
    public boolean deleteItem(Item item) {
        boolean result = false;
        int index = getIndexToItems(item);
        if (index != -1 && index < size) {
            items[index] = null;
            System.arraycopy(items, index + 1, items, index, size - 1);
            size--;
            reduceLength();
            result = true;
        }
        return result;
    }

    /**
     * The method for sort object to name.
     */
    public void sortItemsToName() {
        Arrays.sort(items, items[0].getSortName());
    }

    /**
     * The method for sort object to id.
     */
    public void sortItemsToId() {
        Arrays.sort(items, items[0].getSortId());
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
            int index = getIndexToItems(item);
            if (index != -1 && index < size) {
                item.setIdItem(item.getIdItem().toUpperCase());
                items[index] = item;
                result = true;
            }
        }
        return result;
    }

    /**
     * The method for sort comments to date and time.
     *
     * @param item - object type Item;
     */
    private void sortCommentsToDate(Item item) {
        if (item.getComments().length > 1) {
            Comment[] comments = item.getComments();
            Arrays.sort(item.getComments(), comments[0].getSortDate());
        }
    }

    /**
     * The method add comment to Item.
     *
     * @param item    - link to object;
     * @param comment - text comment`s;
     * @return return boolean: true - is add; false = is not add;
     */
    public boolean addCommentToItem(Item item, String comment) {
        boolean result = false;
        if (item != null) {
            Comment[] workArray = item.getComments();
            workArray = Arrays.copyOf(workArray, workArray.length + 1);
            workArray[workArray.length - 1] = new Comment(comment);
            sortCommentsToDate(item);
            item.setComments(workArray);
            result = true;
        }
        return result;
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
        Comment[] workArray = item.getComments();
        if (item != null && index != -1 && index < workArray.length) {
            workArray[index] = null;
            System.arraycopy(workArray, index + 1, workArray, index, workArray.length - index - 1);
            workArray = Arrays.copyOf(workArray, workArray.length - 1);
            item.setComments(workArray);
            result = true;
        }
        return result;
    }

    /**
     * The method clear all comments in to item.
     *
     * @param item - link on the object;
     * @return boolean: false = is not clear; true - is clear;
     */
    public boolean clearAllComments(Item item) {
        boolean result = false;
        if (item != null && item.getComments().length != 0) {
            item.setComments(new Comment[0]);
            result = true;
        }
        return result;
    }

    /**
     * This method return count comments to item.
     *
     * @param item - object type Item;
     * @return return lenght array comments;
     */
    public int getSizeComments(Item item) {
        return item.getComments().length;
    }
}

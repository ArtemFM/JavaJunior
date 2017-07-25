package apavlov.models;

import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * The class Comment - wrapper for DataBase.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class Item {
    /**
     *  The comparator for sort to name.
     */
    private Comparator<Item> sortName = new Comparator<Item>() {
        @Override
        public int compare(Item itemFirst, Item itemSecond) {
            return (itemSecond == null || itemFirst == null) ? 0 : itemFirst.getName().compareTo(itemSecond.getName());
        }
    };

    /**
     *  The comparator for sort to id.
     */
    private Comparator<Item> sortId = new Comparator<Item>() {
        @Override
        public int compare(Item itemFirst, Item itemSecond) {
            return (itemSecond == null || itemFirst == null) ? 0 : itemFirst.getIdItem().compareTo(itemSecond.getIdItem());
        }
    };

    /**
     * The var for name item.
     */
    private String name;

    /**
     * The var for description item.
     */
    private String description;

    /**
     * The var for save date create item.
     */
    private String dateCreate;

    /**
     * The array class`s Comment for save comments.
     */
    private Comment[] comments;

    /**
     * The var for unique key item.
     */
    private String idItem;

    /**
     * The getter for get comparator sort to name.
     *
     * @return return link comparator;
     */
    public Comparator<Item> getSortName() {
        return sortName;
    }

    /**
     * The getter for get comparator sort to id.
     *
     * @return return link comparator;
     */
    public Comparator<Item> getSortId() {
        return sortId;
    }

    /**
     * The getter for get name item.
     *
     * @return return name item;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for get description item.
     *
     * @return return description item;
     */
    public String getDescription() {
        return description;
    }

    /**
     * The getter for get date create item.
     *
     * @return return date create item;
     */
    public String getDateCreate() {
        return dateCreate;
    }

    /**
     * The getter for get array comments item.
     *
     * @return return array comments item;
     */
    public Comment[] getComments() {
        return comments;
    }

    /**
     * The getter for get unique key item.
     *
     * @return return unique key item;
     */
    public String getIdItem() {
        return idItem;
    }

    /**
     * The setter for rename name.
     *
     * @param name - text for rename name;
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setter for rename description.
     *
     * @param description - text for rename description;
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The setter for change array comments.
     *
     * @param comments - array comments item;
     */
    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    /**
     * The setter for change id item.
     *
     * @param idItem - unique key item;
     */
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    /**
     * The constructor for class Item.
     *
     * @param name        - name item;
     * @param description - desription item;
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.dateCreate = parseDate();
        this.comments = new Comment[0];
    }

    /**
     * The constructor for class Item.
     *
     * @param name        - name item;
     * @param description - desription item;
     * @param idItem      - unique key item;
     */
    public Item(String name, String description, String idItem) {
        this(name, description);
        this.idItem = idItem;
    }

    /**
     * The method parse current date to string.
     *
     * @return return date to string format;
     */
    private String parseDate() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("ru", "RU"));
        return dateFormat.format(new Date());
    }
}

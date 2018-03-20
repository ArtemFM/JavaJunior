package apavlov.models;

import apavlov.util.NameConstant;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * The class Comment - wrapper for DataBase.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class Item {
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
    private Timestamp date;

    /**
     * The var for unique key item.
     */
    private String id;

    /**
     * The getter for var name.
     *
     * @return var name;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for var description.
     *
     * @return var description;
     */
    public String getDescription() {
        return description;
    }

    /**
     * The getter for var date.
     *
     * @return var date type Timestamp;
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * The getter for var id.
     *
     * @return var id;
     */
    public String getId() {
        return id;
    }

    /**
     * The constructor for class Item.
     *
     * @param id          - id item;
     * @param name        - name item;
     * @param description - description item;
     */
    public Item(String id, String name, String description) {
        this(id, name, description, new Timestamp(System.currentTimeMillis()));
    }

    /**
     * The constructor for class Item.
     *
     * @param name        - name item;
     * @param description - description item;
     */
    public Item(String name, String description) {
        this(null, name, description, new Timestamp(System.currentTimeMillis()));
        this.id = this.generateId();
    }

    /**
     * The constructor for class Item.
     *
     * @param id          - id item;
     * @param name        - name item;
     * @param description - description item;
     * @param date        - date create or update item;
     */
    public Item(String id, String name, String description, Timestamp date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * The method generate unique id for item.
     *
     * @return unique id type string;
     */
    private String generateId() {
        String[] code = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuilder result = new StringBuilder();
        int number = new Random().nextInt(90_000) + 10_000;
        while (number != 0) {
            result.append(code[number % 16]);
            number /= 16;
        }
        return result.reverse().toString();
    }

    /**
     * The method parse type Timestamp to String.
     *
     * @return date type string;
     */
    public String getStringDate() {
        return new SimpleDateFormat(NameConstant.FORMAT_DATE.toString()).format(new Date(this.date.getTime()));
    }
}

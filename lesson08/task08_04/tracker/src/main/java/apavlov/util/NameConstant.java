package apavlov.util;

/**
 * The enum NameConstant - enum for storage constants program.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public enum NameConstant {
    /**
     * Name file properties (connect database SQL).
     */
    FILE_PROPERTIES("base.properties"),

    /**
     * Name table for storage items.
     */
    TABLE_ITEM("item"),

    /**
     * Name table for storage comments.
     */
    TABLE_COMMENTS("comment"),

    /**
     * Name row for table "item".
     */
    ITEM_ROW_PK("id_item"),

    /**
     * Name row for table "item".
     */
    ITEM_ROW_NAME("name"),

    /**
     * Name row for table "item".
     */
    ITEM_ROW_DESC("description"),

    /**
     * Name row for table "item".
     */
    ITEM_ROW_DATE("date"),

    /**
     * Name row for table "comment".
     */
    COMMENT_ROW_PK("id_comment"),

    /**
     * Name row for table "comment".
     */
    COMMENT_ROW_NAME("comment"),

    /**
     * Name row for table "comment".
     */
    COMMENT_ROW_DATE("date"),

    /**
     * Name row for table "comment" (link with table "item" one-to-many).
     */
    COMMENT_ROW_FK("id_item"),

    /**
     * Format print date to console.
     */
    FORMAT_DATE("dd.MM.yyyy hh:mm:ss"),

    /**
     * Start text welcome.
     */
    WELCOME("Welcome to program 'Tracker'.");

    /**
     * The var - value constant.
     */
    private String description;

    /**
     * Private constructor for enum NameConstant.
     *
     * @param description - value constant;
     */
    NameConstant(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

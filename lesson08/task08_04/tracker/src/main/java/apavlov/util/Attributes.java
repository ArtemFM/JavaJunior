package apavlov.util;

/**
 * The enum Attributes - enum for storage settings (properties) connect base SQL.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public enum Attributes {
    /**
     * The port for connection to base SQL.
     */
    PORT,

    /**
     * The IP address for connection to base SQL.
     */
    IP,

    /**
     * The type base SQL (postgresql, mysql, ...).
     */
    TYPE,

    /**
     * The database to base SQL.
     */
    DATABASE,

    /**
     * The login for connection to base SQL.
     */
    LOGIN,

    /**
     * The password for connection to base SQL.
     */
    PASSWORD;

    /**
     * Attribute properties file.
     */
    private String attribute;

    /**
     * The setter for var attribute.
     *
     * @param attribute - attribute for set;
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * The getter for var attribute.
     *
     * @return string attribute;
     */
    public String getAttribute() {
        return attribute;
    }
}

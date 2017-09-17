package apavlov;

/**
 * The abstract class Base for save id.
 *
 * @author Pavlov Artem
 * @since 17.09.2017
 */
public abstract class Base {
    /**
     * The var - id.
     */
    private String id;

    /**
     * The getter for var id.
     *
     * @return - id;
     */
    public String getId() {
        return id;
    }

    /**
     * The setter for var id.
     *
     * @param id - id;
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The constructor for class Base.
     *
     * @param id - id;
     */
    Base(String id) {
        this.id = id;
    }
}

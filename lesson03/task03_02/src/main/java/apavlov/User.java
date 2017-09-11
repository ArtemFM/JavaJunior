package apavlov;

/**
 * The class User for save data about user.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class User {
    /**
     * The var - id user (key).
     */
    private int id;

    /**
     * The var - name user`s.
     */
    private String name;

    /**
     * The var - from city user`s.
     */
    private String city;

    /**
     * The getter for var id.
     *
     * @return - id user (key);
     */
    public int getId() {
        return id;
    }

    /**
     * The getter for var name.
     *
     * @return - name user`s;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for var city user`s.
     *
     * @return - city user`s;
     */
    public String getCity() {
        return city;
    }

    /**
     * The setter for var id.
     *
     * @param id - id user`s;
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The setter for var name.
     *
     * @param name - name user`s;
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setter for var city.
     *
     * @param city - city user`s;
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * The constructor for class User.
     *
     * @param id - id user`s;
     * @param name - name user`s;
     * @param city - city user`s;
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * The constructor for class User.
     *
     * @param id - id user`s;
     * @param name - name user`s;
     */
    public User(int id, String name) {
        this(id, name, "");
    }
}

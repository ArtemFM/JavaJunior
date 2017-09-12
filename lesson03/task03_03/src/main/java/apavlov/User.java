package apavlov;

/**
 * The class User for save data about user. Class implements interface Comparable.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class User implements Comparable<User> {
    /**
     * The var - name user`s.
     */
    private String name;

    /**
     * The var - age user`s.
     */
    private int age;

    /**
     * The getter for var name.
     *
     * @return - name user`s;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for var age.
     *
     * @return - age user`s;
     */
    public int getAge() {
        return age;
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
     * The setter for var age.
     *
     * @param age - age user`s;
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * The constructor for class`s User.
     *
     * @param name - name user`s;
     * @param age - age user`s;
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.getAge();
    }

    @Override
    public String toString() {
        return String.format("[name - %s; age - %s]", this.name, this.age);
    }
}

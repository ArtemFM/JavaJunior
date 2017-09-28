package apavlov;

import java.util.Calendar;

/**
 * The class User - model for storage.
 *
 * @author Pavlov Artem
 * @since 28.09.2017
 */
public class User {
    /**
     * The name user`s.
     */
    private String name;

    /**
     * The count children.
     */
    private int children;

    /**
     * The data BirthDay.
     */
    private Calendar birthday;

    /**
     * The getter for var name.
     *
     * @return - name user`s;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for var children.
     *
     * @return count children;
     */
    public int getChildren() {
        return children;
    }

    /**
     * The getter for var birthday.
     *
     * @return date birthday;
     */
    public Calendar getBirthday() {
        return birthday;
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
     * The setter for var children.
     *
     * @param children - count children;
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * The setter for var birthday.
     *
     * @param birthday - date birthday;
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    /**
     * The constructor for class User.
     *
     * @param name - name user;
     * @param children - count children;
     * @param birthday - date birthday;
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        Integer child = children;
        return this.name.hashCode() ^ child.hashCode() ^ birthday.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = obj != null;
        if (result) {
            result = !(obj != this && getClass() == obj.getClass());
            if (!result) {
                User user = (User) obj;
                result = user.getName().equals(this.name) && user.getChildren() == this.children && user.getBirthday().equals(this.birthday);
            }
        }
        return result;
    }
}

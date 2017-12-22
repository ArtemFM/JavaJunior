package apavlov.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * The class UserStorage - use for storage users (accounts) to collections.
 *
 * @author Pavlov Artem
 * @since 19.12.2017
 */
@ThreadSafe
public class UserStorage implements Iterable<User> {
    /**
     * Collection for storage users.
     */
    @GuardedBy("itself")
    private final Map<Integer, User> base;

    /**
     * The default constructor for class UserStorage.
     */
    public UserStorage() {
        this.base = new TreeMap<>();
    }

    /**
     * The constructor for class UserStorage.
     *
     * @param users - array users for adding to collections;
     */
    public UserStorage(User[] users) {
        this();
        addAll(users);
    }

    /**
     * The method return amount users to collection.
     *
     * @return amount users;
     */
    public int size() {
        return this.base.size();
    }

    /**
     * The method add array to collection.
     *
     * @param users - array users;
     */
    private void addAll(User[] users) {
        synchronized (this.base) {
            Stream.of(users).forEach(this::add);
        }
    }

    /**
     * The method transfer money between two users (accounts).
     *
     * @param fromId - first user (from get money);
     * @param toId - second user (to put money);
     * @param amount - amount money for transfer;
     * @return true, if money is transfer or false;
     */
    public boolean transfer(int fromId, int toId, double amount) {
        boolean result;
        User from = this.base.get(fromId);
        User to = this.base.get(toId);
        if (result = from != null && to != null) {
            if (result = amount > 0 && from.getAmount() >= amount) {
                synchronized (this.base) {
                    this.base.put(fromId, new User(fromId, from.getAmount() - amount));
                    this.base.put(toId, new User(toId, to.getAmount() + amount));
                }
            }
        }
        return result;
    }

    /**
     * The method add new user to collection.
     *
     * @param user - user for adding;
     * @return true, if user is add or false;
     */
    public boolean add(User user) {
        boolean result;
        synchronized (this.base) {
            if (result = user != null) {
                this.base.put(user.getId(), user);
            }
        }
        return result;
    }

    /**
     * The method remove user to collection.
     *
     * @param user - user for remove;
     * @return true. if user search and delete or false;
     */
    public boolean delete(User user) {
        boolean result;
        if (result = user != null) {
            synchronized (this.base) {
                int oldSize = this.size();
                this.base.remove(user.getId());
                result = oldSize != this.size();
            }
        }
        return result;
    }

    /**
     * The method edit (update) user to collection.
     *
     * @param user - user for edit (update);
     * @return true. if user search and update (edit) or false;
     */
    public boolean update(User user) {
        boolean result;
        synchronized (this.base) {
            if (result = user != null && this.base.containsKey(user.getId())) {
                this.add(user);
            }
        }
        return result;
    }

    /**
     * The method write collection to array.
     *
     * @return array users;
     */
    public User[] toArray() {
        return this.base.values().toArray(new User[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.base.values().forEach(o -> sb.append(o).append(System.lineSeparator()));
        return sb.toString();
    }

    @Override
    public Iterator<User> iterator() {
        return this.base.values().iterator();
    }
}
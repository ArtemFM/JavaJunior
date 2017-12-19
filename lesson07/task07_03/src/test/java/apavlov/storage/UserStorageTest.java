package apavlov.storage;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class UserStorageTest for class`s UserStorage use junit4.
 *
 * @author Pavlov Artem
 * @since 19.12.2017
 */
public class UserStorageTest {
    /**
     * The method check work method add.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewUserToCollection() throws Exception {
        User one = new User(1000, 100.00);
        User two = new User(1001, 100.00);
        User[] users = {one, two};
        UserStorage base = new UserStorage();

        assertThat(base.add(two), is(true));
        assertThat(base.add(one), is(true));
        assertThat(base.size(), is(users.length));
        assertThat(base.toArray(), is(users));
    }

    /**
     * The method check adding array to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddArrayUsersToCollection() throws Exception {
        User one = new User(1000, 100.00);
        User two = new User(1001, 100.00);
        User[] users = {one, two};
        UserStorage base = new UserStorage(users);

        assertThat(base.size(), is(users.length));
        assertThat(base.toArray(), is(users));
    }

    /**
     * The method check delete user to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteUserToCollection() throws Exception {
        User one = new User(1000, 100.00);
        User two = new User(1001, 100.00);
        User[] users = {one, two};
        UserStorage base = new UserStorage(users);
        users = Arrays.copyOf(users, users.length - 1);

        assertThat(base.delete(two), is(true));
        assertThat(base.delete(new User(1003, 100.00)), is(false));
        assertThat(base.size(), is(users.length));
        assertThat(base.toArray(), is(users));
    }

    /**
     * The method check edit (update) user to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenUpdateUserToCollection() throws Exception {
        User one = new User(1000, 100.00);
        User two = new User(1001, 100.00);
        User three = new User(1001, 200.00);
        User[] users = {one, two};
        UserStorage base = new UserStorage(users);
        users[1] = three;

        assertThat(base.update(three), is(true));
        assertThat(base.update(new User(1002, 100.00)), is(false));
        assertThat(base.size(), is(users.length));
        assertThat(base.toArray(), is(users));
    }

    /**
     * The method check correct transfer money between users.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenTransferMoneyBetweenUsers() throws Exception {
        User one = new User(1000, 100.00);
        User two = new User(1001, 100.00);
        UserStorage base = new UserStorage(new User[]{one, two});

        assertThat(base.transfer(1002, 1001, 500.00), is(false));
        assertThat(base.transfer(1000, 1001, 200.00), is(false));
        assertThat(base.transfer(1000, 1001, 100.00), is(true));
        assertThat(base.toArray()[0].getAmount(), is(0.00));
        assertThat(base.toArray()[1].getAmount(), is(200.00));
    }

    /**
     * The method check correct work forEach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckWorkForEach() throws Exception {
        User one = new User(1003, 100.00);
        User two = new User(1005, 200.00);
        User three = new User(1001, 300.00);
        User[] result = {three, one, two};
        UserStorage base = new UserStorage(new User[]{one, two, three});
        int index = 0;
        for (User user : base) {
            assertThat(user, is(result[index++]));
        }
    }
}
package apavlov;

import apavlov.base.BaseUser;
import apavlov.models.Account;
import apavlov.models.User;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class BaseUserTest for class`s BaseUser.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class BaseUserTest {
    /**
     * The test method for check add new user.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewUser() throws Exception {
        BaseUser base = new BaseUser();
        base.addUser(new User("1", "2"));
        assertThat(1, is(base.getSize()));
    }

    /**
     * The test method for check delete user.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteUser() throws Exception {
        BaseUser base = new BaseUser();
        base.addUser(new User("1", "2"));
        base.addUser(new User("2", "2"));
        assertThat(2, is(base.getSize()));
        base.deleteUser(new User("1", "2"));
        assertThat(1, is(base.getSize()));
    }

    /**
     * The test method for check add new account to user.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddAccountToUser() throws Exception {
        BaseUser base = new BaseUser();
        User user = new User("1", "2");
        base.addUser(user);
        base.addAccountToUser(user, new Account(1000.0, 3412));
        base.addAccountToUser(user, new Account(2000.0, 3412));
        assertThat(1, is(base.getAccountSize(user)));
        base.addAccountToUser(user, new Account(2000.0, 3413));
        assertThat(2, is(base.getAccountSize(user)));
    }

    /**
     * The test method for check delete account user`s.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteAccountFromUser() throws Exception {
        BaseUser base = new BaseUser();
        User user = new User("1", "2");
        Account accountFirst = new Account(1000.0, 3412);
        Account accountSecond = new Account(3000.0, 3413);
        base.addUser(user);
        base.addAccountToUser(user, accountFirst);
        base.addAccountToUser(user, accountSecond);
        assertThat(2, is(base.getAccountSize(user)));
        base.deleteAccountFromUser(user, accountFirst);
        assertThat(1, is(base.getAccountSize(user)));
    }

    /**
     * The test method for check transfer money userOne.account to userTwo.account is true.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenIsTransferMoney() throws Exception {
        BaseUser base = new BaseUser();
        User user = new User("1", "2");
        Account accountFirst = new Account(1000.0, 3412);
        Account accountSecond = new Account(3000.0, 3413);
        base.addUser(user);
        base.addAccountToUser(user, accountFirst);
        base.addAccountToUser(user, accountSecond);
        assertThat(true, is(base.transferMoney(user, accountFirst, user, accountSecond, 500.0)));
        assertThat(3500.0, is(base.getUserAccounts(user).get(1).getCountMoney()));
        assertThat(500.0, is(base.getUserAccounts(user).get(0).getCountMoney()));
    }

    /**
     * The test method for check transfer money userOne.account to userTwo.account is false.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenIsNotTransferMoney() throws Exception {
        BaseUser base = new BaseUser();
        User user = new User("1", "2");
        Account accountFirst = new Account(1000.0, 3412);
        Account accountSecond = new Account(3000.0, 3413);
        base.addUser(user);
        base.addAccountToUser(user, accountFirst);
        base.addAccountToUser(user, accountSecond);
        assertThat(false, is(base.transferMoney(user, accountFirst, user, accountSecond, 1500.0)));
    }
}
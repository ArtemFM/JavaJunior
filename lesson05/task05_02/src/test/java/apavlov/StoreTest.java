package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class StoreTest for class`s Store.
 *
 * @author Pavlov Artem
 * @since 17.09.2017
 */
public class StoreTest {
    /**
     * The test method check add new user to base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewUser() throws Exception {
        AbstractStore userBase = new UserStore();
        Base userOne = new User("1001");
        Base userTwo = new User("1002");
        userBase.add(userOne);
        userBase.add(userTwo);
        Object[] result = {userOne, userTwo};
        assertThat(userBase.toArray(), is(result));
    }

    /**
     * The test method check add new role to base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddNewRole() throws Exception {
        AbstractStore roleBase = new RoleStore();
        Base roleOne = new Role("1001");
        Base roleTwo = new Role("1002");
        roleBase.add(roleOne);
        roleBase.add(roleTwo);
        Object[] result = {roleOne, roleTwo};
        assertThat(roleBase.toArray(), is(result));
    }

    /**
     * The test method check delete user to base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteUser() throws Exception {
        AbstractStore userBase = new UserStore();
        Base userOne = new User("1001");
        Base userTwo = new User("1002");
        Base userThree = new User("1003");
        userBase.add(userOne);
        userBase.add(userTwo);
        userBase.add(userThree);
        Object[] result = {userOne, userThree};
        userBase.delete(userTwo);
        assertThat(userBase.toArray(), is(result));
    }

    /**
     * The test method check delete role to base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteRole() throws Exception {
        AbstractStore roleBase = new RoleStore();
        Base roleOne = new Role("1001");
        Base roleTwo = new Role("1002");
        Base roleThree = new Role("1003");
        roleBase.add(roleOne);
        roleBase.add(roleTwo);
        roleBase.add(roleThree);
        Object[] result = {roleOne, roleThree};
        roleBase.delete(roleTwo);
        assertThat(roleBase.toArray(), is(result));
    }

    /**
     * The test method check edit user to base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenUpdateUser() throws Exception {
        AbstractStore userBase = new UserStore();
        Base userOne = new User("1001");
        Base userTwo = new User("1002");
        Base userThree = new User("1003");
        Base userNew = new User("1004");
        userBase.add(userOne);
        userBase.add(userTwo);
        userBase.add(userThree);
        Object[] result = {userOne, userNew, userThree};
        userBase.update(userTwo, userNew);
        assertThat(userBase.toArray(), is(result));
    }

    /**
     * The test method check edit role to base.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenUpdateRole() throws Exception {
        AbstractStore roleBase = new RoleStore();
        Base roleOne = new Role("1001");
        Base roleTwo = new Role("1002");
        Base roleThree = new Role("1003");
        Base roleNew = new Role("1005");
        roleBase.add(roleOne);
        roleBase.add(roleTwo);
        roleBase.add(roleThree);
        Object[] result = {roleOne, roleNew, roleThree};
        roleBase.update(roleTwo, roleNew);
        assertThat(roleBase.toArray(), is(result));
    }
}
package apavlov;

import apavlov.models.User;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class UserTest for class`s User.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class UserTest {
    /**
     * The test method for check when two classes is equals.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenClassesIsEquals() throws Exception {
        User userOne = new User("1", "2");
        User userTwo = new User("1", "2");
        assertThat(true, is(userOne.equals(userTwo)));
    }

    /**
     * The test method for check when two classes is not equals.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenClassesIsNotEquals() throws Exception {
        User userOne = new User("1", "2");
        User userTwo = new User("2", "1");
        assertThat(false, is(userOne.equals(userTwo)));
    }

    /**
     * The test method for check when hash codes two classes is equals.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenHashCodesIsEquals() throws Exception {
        User userOne = new User("1", "2");
        User userTwo = new User("1", "2");
        assertThat(true, is(userTwo.hashCode() == userOne.hashCode()));
    }

    /**
     * The test method for check when hash codes two classes is not equals.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenHashCodesIsNotEquals() throws Exception {
        User userOne = new User("1", "2");
        User userTwo = new User("2", "1");
        assertThat(false, is(userTwo.hashCode() == userOne.hashCode()));
    }
}
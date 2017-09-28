package apavlov;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class UserTest for class User.
 *
 * @author Pavlov Artem
 * @since 28.09.2017
 */
public class UserTest {
    /**
     * The name user.
     */
    private String name = "Artem";

    /**
     * The count children.
     */
    private int children = 0;

    /**
     * The data birthday.
     */
    private Calendar birthday = new GregorianCalendar(1984, 7, 20);

    /**
     * The test method for check result output.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckOutputResult() throws Exception {
        User userFirst = new User(name, children, birthday);
        User userSecond = new User(name, children, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, "one");
        map.put(userSecond, "two");
        System.out.print(map);
        assertThat(userFirst.getName().equals(userSecond.getName()), is(true));
        assertThat(userFirst.getChildren() == userSecond.getChildren(), is(true));
        assertThat(userFirst.getBirthday().equals(userSecond.getBirthday()), is(true));
    }
}
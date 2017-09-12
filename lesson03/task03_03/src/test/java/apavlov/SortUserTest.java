package apavlov;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class SortUserTest for class`s SortUser.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class SortUserTest {
    /**
     * The test method for check sort users by age.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenSortByAgeAndListToTreeSet() throws Exception {
        SortUser sortUser = new SortUser();
        List<User> list = Arrays.asList(new User[]{new User("1", 20), new User("2", 15), new User("3", 25)});
        String result = "[[name - 2; age - 15], [name - 1; age - 20], [name - 3; age - 25]]";
        assertThat(result, is(sortUser.sort(list).toString()));
    }

    /**
     * The test method for check sort users by length name.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenSortByLengthName() throws Exception {
        SortUser sortUser = new SortUser();
        List<User> list = Arrays.asList(new User[]{new User("Alexander", 20), new User("Ivan", 15), new User("Sergey", 25)});
        String result = "[[name - Ivan; age - 15], [name - Sergey; age - 25], [name - Alexander; age - 20]]";
        assertThat(result, is(sortUser.sortNameLength(list).toString()));
    }

    /**
     * The test method for check sort users by name or age.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenSortByNameOrAge() throws Exception {
        SortUser sortUser = new SortUser();
        List<User> list = Arrays.asList(new User[]{new User("Ivan", 15), new User("Ivan", 10), new User("Alexander", 20)});
        String result = "[[name - Alexander; age - 20], [name - Ivan; age - 10], [name - Ivan; age - 15]]";
        assertThat(result, is(sortUser.sortByAllFields(list).toString()));
    }
}
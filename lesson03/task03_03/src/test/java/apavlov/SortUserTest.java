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
}
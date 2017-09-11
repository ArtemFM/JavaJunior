package apavlov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class UserConvertTest for classes`s UserConvert.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class UserConvertTest {
    /**
     *  The test method for check convert list type User to HashMap.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenConvertListUsersToHashMap() throws Exception {
        User userOne = new User(1, "Artem");
        User userTwo = new User(1, "Anton");
        User userThree = new User(2, "Sergey");

        HashMap<Integer, User> resultMap = new HashMap<>();
        resultMap.put(userTwo.getId(), userTwo);
        resultMap.put(userThree.getId(), userThree);

        List<User> listTest = new ArrayList<>();
        listTest.add(userOne);
        listTest.add(userTwo);
        listTest.add(null);
        listTest.add(userThree);

        UserConvert uc = new UserConvert();
        assertThat(resultMap.toString(), is(uc.process(listTest).toString()));
    }
}
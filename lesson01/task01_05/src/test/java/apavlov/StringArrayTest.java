package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class StringArray.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class StringArrayTest {

    /**
     * The test check delete duplicate in array.
     */
    @Test
    public void whenDeleteDuplicate() {
        StringArray stringArray = new StringArray();
        String[] startArray = {"a", "a", "a", "b", "a", "b", "c", "c", "a"};
        String[] cheked = {"a", "b", "c"};

        String[] result = stringArray.deleteDuplicate(startArray);

        assertThat(cheked, is(result));
    }
}
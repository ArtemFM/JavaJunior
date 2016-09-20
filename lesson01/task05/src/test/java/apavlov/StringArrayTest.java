package apavlov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringArrayTest {

    @Test
    public void whenDeleteDuplicate() throws Exception {
        StringArray stringArray = new StringArray();
        String[] startArray = {"a", "a", "a", "b", "a", "b", "c", "c", "a"};
        String[] cheked = {"a", "b", "c"};

        String[] result = stringArray.deleteDuplicate(startArray);

        assertThat(cheked, is(result));
    }
}
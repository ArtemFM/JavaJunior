package apavlov;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class ConverterTest for class`s Converter.
 *
 * @author Pavlov Artem
 * @since 15.09.2017
 */
public class ConverterTest {
    /**
     * The test method for check work class, if iterator iterators is null.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenParametrIsNull() throws Exception {
        Iterator<Integer> iterator = new Converter().convert(null);
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * The test method for check work class with three filling collections.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAllCollectionsIsFill() throws Exception {
        List<Integer> oneList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> twoList = Arrays.asList(11, 12, 13, 14, 15);
        List<Integer> threeList = Arrays.asList(21, 22, 23, 24, 25);
        Iterator<Integer> iterator = new Converter().convert(Arrays.asList(oneList.iterator(), twoList.iterator(), threeList.iterator()).iterator());

        Integer[] result = {1, 2, 3, 4, 5, 11, 12, 13, 14, 15, 21, 22, 23, 24, 25};
        int index = 0;

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(result[index++]));
        }
    }

    /**
     * The test method for check work class, if one collection is empty.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenOneCollectionIsEmpty() throws Exception {
        List<Integer> oneList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> twoList = new ArrayList<>();
        List<Integer> threeList = Arrays.asList(21, 22, 23, 24, 25);
        Iterator<Integer> iterator = new Converter().convert(Arrays.asList(oneList.iterator(), twoList.iterator(), threeList.iterator()).iterator());

        Integer[] result = {1, 2, 3, 4, 5, 21, 22, 23, 24, 25};
        int index = 0;

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(result[index++]));
        }
    }
}
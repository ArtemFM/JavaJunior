package apavlov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class ConvertListTest for classe`s ConvertList.
 *
 * @author Pavlov Artem
 * @since 06.09.2017
 */
public class ConvertListTest {
    /**
     * The test method for check convert array to list.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenConvertArrayToList() throws Exception {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5}};
        String result = "[1, 2, 3, 4, 5]";
        ConvertList cl = new ConvertList();
        assertThat(result, is(cl.toList(array).toString()));
    }

    /**
     * The test method for check convert list to array.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenConvertListToArray() throws Exception {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, new Integer[]{1, null, 3, 4, null});
        int[][] result = new int[][]{{1, 3}, {4, 0}};
        ConvertList cl = new ConvertList();
        assertThat(result, is(cl.toArray(list)));
    }

    /**
     * The test method for check convert list ints to list type Integer.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenListIntsToInteger() throws Exception {
        List<Integer> resultList = new ArrayList<>();
        Collections.addAll(resultList, new Integer[]{1, 2, 3, 4, 5});
        List<int[]> testList = new ArrayList<>();
        testList.add(new int[]{1, 2, 3});
        testList.add(null);
        testList.add(new int[]{4, 5});
        ConvertList cl = new ConvertList();
        assertThat(resultList, is(cl.convert(testList)));
    }
}
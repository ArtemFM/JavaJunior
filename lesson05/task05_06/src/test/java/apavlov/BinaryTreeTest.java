package apavlov;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class BinaryTreeTest for class`s BinaryTree use junit4.
 *
 * @author Pavlov Artem
 * @since 02.10.2017
 */
public class BinaryTreeTest {
    /**
     * The test method check correct work method isEmpty.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkMethodIsEmpty() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] testArray = {1, 1, 2, null, 3, 4, 3};
        assertThat(tree.isEmpty(), is(true));
        tree.addAll(testArray);
        assertThat(tree.isEmpty(), is(false));
    }

    /**
     * The test method check correct out size tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectOutputSizeTree() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] testArray = {1, 1, 2, null, 3, 4, 3};
        assertThat(tree.size(), is(0));
        tree.addAll(testArray);
        assertThat(tree.size(), is(4));
    }

    /**
     * The test method check correct add new value to binary tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkAdd() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] resultArray = {1, 3, 4, 7};
        tree.add(7);
        tree.add(1);
        tree.add(null);
        tree.add(7);
        tree.add(3);
        tree.add(4);
        tree.add(1);
        tree.add(3);
        assertThat(tree.size(), is(resultArray.length));
        assertThat(tree.toArray(new Integer[0]), is(resultArray));
    }

    /**
     * The test method check correct add array values to binary tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkMethodAddAll() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>(new Integer[]{1, 1, 5, null, 3, 4, 3, 10, 7, 2});
        Object[] resultArray = {1, 2, 3, 4, 5, 7, 10};
        assertThat(tree.size(), is(resultArray.length));
        assertThat(tree.toArray(), is(resultArray));
    }

    /**
     * The test method check throws exception is use close method add with two parameters.
     *
     * @throws Exception - throws UnsupportedOperationException if use method add with two parameters;;
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenCheckThrowsExceptionWhenUseCloseMethodAddWithTwoParameters() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(1, 2);
    }

    /**
     * The test method check correct return line string.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkMethodToString() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>(new Integer[]{1, 1, 5, null, 3, 4, 3, 10, 7, 2});
        Integer[] resultArray = {1, 2, 3, 4, 5, 7, 10};
        assertThat(tree.toString(), is(Arrays.toString(resultArray)));
    }

    /**
     * The test method check correct work foreach.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkForeach() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>(new Integer[]{1, 1, 5, null, 3, 4, 3, 10, 7, 2});
        Integer[] resultArray = {1, 2, 3, 4, 5, 7, 10};
        int index = 0;
        for (Integer value : tree) {
            assertThat(value, is(resultArray[index++]));
        }
    }

    /**
     * The test method check correct work iterator for binary tree.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectWorkIteratorForTree() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>(new Integer[]{1, 1, 5, null, 3, 4, 3, 10, 7, 2});
        Integer[] resultArray = {1, 2, 3, 4, 5, 7, 10};
        int index = 0;
        Iterator<Integer> iterator = tree.iterator();
        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(resultArray[index++]));
        }
    }

    /**
     * The test method check throws exception is not correct work iterator.
     *
     * @throws Exception - throws NoSuchElementException if elements the end;
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCheckIsNotCorrectWorkIteratorForTree() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Iterator<Integer> iterator = tree.iterator();
        iterator.next();
    }
}
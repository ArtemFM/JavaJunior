package apavlov.nonblock;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class NonBlockingCacheTest for class`s NonBlockingCache use junit4.
 *
 * @author Pavlov Artem
 * @since 26.12.2017
 */
public class NonBlockingCacheTest {
    /**
     * The method for check work add new element by key and value to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddElementsToCollections() throws Exception {
        NonBlockingCache<Integer, Model> base = new NonBlockingCache<>();

        assertThat(base.add(1, new Model("1")), is(true));
        assertThat(base.add(1, new Model("2")), is(false));
        assertThat(base.add(2, new Model("2")), is(true));

        assertThat(base.size(), is(2));
    }

    /**
     * The method for check work delete element by key to collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteElementsToCollections() throws Exception {
        NonBlockingCache<Integer, Model> base = new NonBlockingCache<>();

        base.add(1, new Model("1"));
        base.add(2, new Model("2"));
        base.add(3, new Model("3"));

        assertThat(base.delete(1), is(true));
        assertThat(base.delete(2), is(true));
        assertThat(base.delete(4), is(false));

        assertThat(base.size(), is(1));
    }

    /**
     * The method for check correct work method`s update.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCorrectUpdateElementToCollections() throws Exception {
        NonBlockingCache<Integer, Model> base = new NonBlockingCache<>();
        Model first = new Model("first");
        Model middle = new Model("middle");
        Model last = new Model("last");
        base.add(1, first);
        base.add(2, middle);
        base.add(3, last);
        Model[] result = new Model[]{first, middle, last};

        middle.setName("second");
        base.update(2, middle);

        assertThat(base.size(), is(3));
        assertThat(base.toArray(new Model[0]), is(result));
    }

    /**
     * The method for check un correct work method`s update.
     *
     * @throws Exception - check any errors;
     */
    @Test (expected = OptimisticException.class)
    public void whenUnCorrectUpdateElementToCollections() throws Exception {
        NonBlockingCache<Integer, Model> base = new NonBlockingCache<>();
        Model first = new Model("first");
        base.add(1, first);
        base.update(1, new Model("first"));
    }
}
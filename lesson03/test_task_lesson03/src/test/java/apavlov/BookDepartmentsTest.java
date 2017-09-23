package apavlov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class BookDepartmentsTest for class`s BookDepartments.
 *
 * @author Pavlov Artem
 * @since 23.09.2017
 */
public class BookDepartmentsTest {
    /**
     * The var - object type BookDepartments.
     */
    private BookDepartments book = new BookDepartments("/");

    /**
     * The test array for check work testing class`s.
     */
    private String[] testArray = {
            "K1/SK1",
            "K1/SK2",
            "K1/SK1/SSK1",
            "K1/SK1/SSK2",
            "K2",
            "K2/SK1/SSK1",
            "K2/SK1/SSK2",
    };

    /**
     * The test method check correct add new department and new departments.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenCheckCorrectAddDepartment() throws Exception {
        assertThat(book.addDepartment(null), is(false));
        assertThat(book.addDepartment(""), is(false));
        assertThat(book.addAllDepartments(this.testArray), is(true));
    }

    /**
     * The method check correct return array necessary sort.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetOutputSortOrder() throws Exception {
        String[] result = {
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        };
        book.addAllDepartments(this.testArray);
        assertThat(book.toArray(false), is(result));
    }

    /**
     * The method check correct return array necessary sort.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenGetOutputReverseSortOrder() throws Exception {
        String[] result = {
                "K2",
                "K2/SK1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1",
                "K1",
                "K1/SK2",
                "K1/SK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1",
        };
        book.addAllDepartments(this.testArray);
        assertThat(book.toArray(true), is(result));
    }
}
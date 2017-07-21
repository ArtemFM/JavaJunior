package apavlov;

import org.junit.Test;
import java.text.SimpleDateFormat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Engineer.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class EngineerTest {
    /**
     * The test check return string.
     *
     * @throws Exception - error parse date;
     */
    @Test
    public void whenRepairGadget() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Engineer engineer = new Engineer("Sergey", 33, "engineer", dateFormat.parse("2012-06-13"));
        Profession client = new MedicalDoctor("Pasha", 25, "medicaldoctor", dateFormat.parse("2013-08-08"));

        String result = String.format("The engineer Sergey repair gadget for medicaldoctor`s Pasha.");

        assertThat(result, is(engineer.repairGadget(client)));
    }

    /**
     * The test ckeck return string.
     *
     * @throws Exception - error parse date or other error;
     */
    @Test
    public void whenMakeProject() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Engineer engineer = new Engineer("Sergey", 33, "engineer", dateFormat.parse("2012-06-13"));
        Profession client = new Teacher("Alena", 24, "teacher", dateFormat.parse("2014-11-23"));

        String result = String.format("The engineer Sergey make project for teacher`s Alena");

        assertThat(result, is(engineer.makeProject(client)));
    }

    /**
     * The test ckeck return string.
     *
     * @throws Exception - error parse date or other error;
     */
    @Test
    public void whenCelebrateDiplom() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Engineer engineer = new Engineer("Sergey", 33, "engineer", dateFormat.parse("2012-06-13"), "document Engineer`s university");

        String result = String.format("The engineer Sergey celebrate his document Engineer`s university.");

        assertThat(result, is(engineer.celebrateDiplom()));
    }
}

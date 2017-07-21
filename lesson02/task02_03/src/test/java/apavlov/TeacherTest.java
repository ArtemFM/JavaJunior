package apavlov;

import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class Teacher.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class TeacherTest {
    /**
     * The test check string.
     *
     * @throws ParseException - error parse date;
     */
    @Test
    public void whenTeachStudent() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Teacher teacher = new Teacher("Marina", 27, "teacher", dateFormat.parse("2005-03-27"));
        Profession student = new Engineer("Anton", 22, "engineer", dateFormat.parse("2016-07-20"));

        String result = String.format("The teacher Marina is teach engineer`s Anton.");

        assertThat(result, is(teacher.teachStudent(student)));
    }

    /**
     * The test check string.
     *
     * @throws ParseException - error parse date;
     */
    @Test
    public void whenGiveHomework() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Teacher teacher = new Teacher("Marina", 27, "teacher", dateFormat.parse("2005-03-27"));
        Profession student = new MedicalDoctor("Alexander", 25, "medicaldoctor", dateFormat.parse("2017-02-03"));

        String result = String.format("The teacher Marina give homework medicaldoctor`s Alexander.");

        assertThat(result, is(teacher.giveHomework(student)));
    }

    /**
     * The test check string.
     *
     * @throws ParseException - error parse date;
     */
    @Test
    public void whenCountAge() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Teacher teacher = new Teacher("Marina", 27, "teacher", dateFormat.parse("2005-03-27"));

        String result = String.format("The teacher Marina in his 27 years work with %s.", dateFormat.parse("2005-03-27"));

        assertThat(result, is(teacher.countAge()));
    }
}

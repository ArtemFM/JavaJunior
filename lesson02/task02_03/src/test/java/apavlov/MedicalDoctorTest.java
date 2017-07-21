package apavlov;

import org.junit.Test;
import java.text.SimpleDateFormat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test class for class MedicalDoctor.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class MedicalDoctorTest {
    /**
     * The test ckeck return string.
     *
     * @throws Exception - error parse date or other error;
     */
    @Test
    public void whenDiagnosePatient() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        MedicalDoctor engineer = new MedicalDoctor("Ivan", 41, "medicaldoctor", dateFormat.parse("2009-02-25"));
        Profession patient = new Engineer("Alexey", 27, "engineer", dateFormat.parse("2015-05-12"));

        String result = String.format("The medicaldoctor Ivan diagnose engineer`s Alexey.");

        assertThat(result, is(engineer.diagnosePatient(patient)));
    }

    /**
     * The test ckeck return string.
     *
     * @throws Exception - error parse date or other error;
     */
    @Test
    public void whenHealPatient() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        MedicalDoctor medicaldoctor = new MedicalDoctor("Ivan", 41, "medicaldoctor", dateFormat.parse("2009-02-25"));
        Profession patient = new Teacher("Kseniya", 24, "teacher", dateFormat.parse("2016-07-23"));

        String result = String.format("The medicaldoctor Ivan diagnose teacher`s Kseniya.");

        assertThat(result, is(medicaldoctor.diagnosePatient(patient)));
    }

    /**
     * The test ckeck return string.
     *
     * @throws Exception - error parse date or other error;
     */
    @Test
    public void whenLecturingStudents() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        MedicalDoctor medicaldoctor = new MedicalDoctor("Ivan", 41, "medicaldoctor", dateFormat.parse("2009-02-25"));
        Profession studentFirst = new Teacher("Kseniya", 24, "teacher", dateFormat.parse("2016-07-23"));
        Profession studenSecond = new Engineer("Alexey", 27, "engineer", dateFormat.parse("2015-05-12"));

        String result = String.format("The medicaldoctor Ivan lecturing for teacher`s Kseniya and engineer`s Alexey.");

        assertThat(result, is(medicaldoctor.lecturingStudents(studentFirst, studenSecond)));
    }
}

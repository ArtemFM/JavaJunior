package apavlov;

import java.util.Date;

/**
 * The class MedicalDoctor extended Profession.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class MedicalDoctor extends Profession {
    /**
     * The constructor class MedicalDoctor.
     *
     * @param name           - name specialist`s;
     * @param age            - age specialist`s;
     * @param profession     - profession;
     * @param dateEmployment - date employment specialist`s;
     */
    public MedicalDoctor(String name, int age, String profession, Date dateEmployment) {
        super(name, age, profession, dateEmployment);
    }

    /**
     * The constructor class MedicalDoctor.
     *
     * @param name           - name specialist`s;
     * @param age            - age specialist`s;
     * @param profession     - profession;
     * @param dateEmployment - date employment specialist`s;
     * @param diplom         - document education;
     */
    public MedicalDoctor(String name, int age, String profession, Date dateEmployment, String diplom) {
        super(name, age, profession, dateEmployment, diplom);
    }

    /**
     * The method diagnose patient.
     *
     * @param patient - object Profession;
     * @return result to String;
     */
    public String diagnosePatient(Profession patient) {
        return String.format("The %s %s diagnose %s`s %s.", getProfession(), getName(), patient.getProfession(),
                patient.getName());
    }

    /**
     * The method heal patient.
     *
     * @param patient - object Profession;
     * @return result to String;
     */
    public String healPatient(Profession patient) {
        return String.format("The %s %s is heal %s`s %s", getProfession(), getName(), patient.getProfession(),
                patient.getName());
    }

    /**
     * The method lecturing for students.
     *
     * @param studentFirst - object Profession;
     * @param studentSecond - object Profession;
     * @return result to String;
     */
    public String lecturingStudents(Profession studentFirst, Profession studentSecond) {
       return String.format("The %s %s lecturing for %s`s %s and %s`s %s.", getProfession(), getName(),
               studentFirst.getProfession(), studentFirst.getName(), studentSecond.getProfession(),
               studentSecond.getName());
    }
}

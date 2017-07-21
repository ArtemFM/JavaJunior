package apavlov;

import java.util.Date;

/**
 * The class Teacher extended Profession.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Teacher extends Profession {

    /**
     * The constructor class Teacher.
     *
     * @param name - name specialist`s;
     * @param age - age specialist`s;
     * @param profession - profession;
     * @param dateEmployment - date employment specialist`s;
     */
    public Teacher(String name, int age, String profession, Date dateEmployment) {
        super(name, age, profession, dateEmployment);
    }

    /**
     * The constructor class Teacher.
     *
     * @param name - name specialist`s;
     * @param age - age specialist`s;
     * @param profession - profession;
     * @param dateEmployment - date employment specialist`s;
     * @param diplom - document education;
     */
    public Teacher(String name, int age, String profession, Date dateEmployment, String diplom) {
        this(name, age, profession, dateEmployment);
        super.setDiplom(diplom);
    }

    /**
     * The method return teach student.
     *
     * @param student - object Profession;
     * @return string;
     */
    public String teachStudent(Profession student) {
        return String.format("The %s %s is teach %s`s %s.", getProfession(), getName(), student.getProfession(),
                student.getName());
    }

    /**
     * The method return count years work teacher.
     *
     * @return string;
     */
    public String countAge() {
        return String.format("The %s %s in his %s years work with %s.", getProfession(), getName(),
                getAge(), getDateEmployment());
    }

    /**
     * The method give homework.
     *
     * @param student - object Profession;
     * @return result to string;
     */
    public String giveHomework(Profession student) {
        return String.format("The %s %s give homework %s`s %s.", getProfession(), getName(), student.getProfession(),
                student.getName());
    }
}

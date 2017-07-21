package apavlov;

import java.util.Date;

/**
 * The class Profession.
 *
 * @author Pavlov Artem
 * @since 20.07.2017
 */
public class Profession {

    /**
     * The name specialist`s.
     */
    private String name;

    /**
     * The age specialist`s.
     */
    private int age;

    /**
     * The profession specialist`s.
     */
    private String profession;

    /**
     * The date employment.
     */
    private Date dateEmployment;

    /**
     * The education document.
     */
    private String diplom;

    /**
     * The getter for var name.
     *
     * @return name specialist`s;
     */
    public String getName() {
        return name;
    }

    /**
     * The getter for var age.
     *
     * @return  age specialist`s;
     */
    public int getAge() {
        return age;
    }

    /**
     * The getter for var profession.
     *
     * @return  profession specialist`s;
     */
    public String getProfession() {
        return profession;
    }

    /**
     * The getter for var dateEmployment.
     *
     * @return  date employment specialist`s;
     */
    public Date getDateEmployment() {
        return dateEmployment;
    }

    /**
     * The getter for var diplom.
     *
     * @return  name document;
     */
    public String getDiplom() {
        return diplom;
    }

    /**
     * The setter for var name.
     *
     * @param  name - name specialist`s;
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setter for var age.
     *
     * @param age - age specialist`s;
     */
    public void setAge(int age) {
        if (age >= 0 && age < 60) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("The age is incorrect");
        }
    }

    /**
     * The setter for var profession.
     *
     * @param  profession - name specialist`s;
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * The setter for var dateEmployment.
     *
     * @param dateEmployment - date employment specialist`s;
     */
    public void setDateEmployment(Date dateEmployment) {
        this.dateEmployment = dateEmployment;
    }

    /**
     * The setter for var diplom.
     *
     * @param diplom  - name document;
     */
    public void setDiplom(String diplom) {
        this.diplom = diplom;
    }

    /**
     * Constructor for class Profession.
     *
     * @param name           - the name specialist`s;
     * @param age            - the age specialist`s;
     * @param profession     - the profession;
     * @param dateEmployment - the date employment specialist`s;
     */
    public Profession(String name, int age,  String profession, Date dateEmployment) {
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.dateEmployment = dateEmployment;
    }

    /**
     * Constructor for class Profession.
     *
     * @param name           - the name specialist`s;
     * @param age            - the age specialist`s;
     * @param profession     - the profession;
     * @param dateEmployment - the date employment specialist`s;
     * @param diplom         - the name education document;
     */
    public Profession(String name, int age, String profession, Date dateEmployment, String diplom) {
        this(name, age, profession, dateEmployment);
        this.diplom = diplom;
    }
}

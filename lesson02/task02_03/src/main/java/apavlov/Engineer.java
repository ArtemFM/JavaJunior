package apavlov;

import java.util.Date;

/**
 * The class Engineer extended Profession.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class Engineer extends Profession {
    /**
     * The constructor class Engineer.
     *
     * @param name           - name specialist`s;
     * @param age            - age specialist`s;
     * @param profession     - profession;
     * @param dateEmployment - date employment specialist`s;
     */
    public Engineer(String name, int age, String profession, Date dateEmployment) {
        super(name, age, profession, dateEmployment);
    }

    /**
     * The constructor class Engineer.
     *
     * @param name           - name specialist`s;
     * @param age            - age specialist`s;
     * @param profession     - profession;
     * @param dateEmployment - date employment specialist`s;
     * @param diplom         - document education;
     */
    public Engineer(String name, int age, String profession, Date dateEmployment, String diplom) {
        super(name, age, profession, dateEmployment, diplom);
    }

    /**
     * The method repair gadget.
     *
     * @param client - object Profession;
     * @return result to string;
     */
    public String repairGadget(Profession client) {
        return String.format("The %s %s repair gadget for %s`s %s.", getProfession(),
                getName(), client.getProfession(), client.getName());
    }

    /**
     * The method make project.
     *
     * @param client - object Profession;
     * @return result to string;
     */
    public String makeProject(Profession client) {
        return String.format("The %s %s make project for %s`s %s", getProfession(), getName(),
                client.getProfession(), client.getName());
    }

    /**
     * The method celebrate diplom.
     *
     * @return result to string;
     */
    public String celebrateDiplom() {
        return String.format("The %s %s celebrate his %s.", getProfession(), getName(), getDiplom());
    }
}

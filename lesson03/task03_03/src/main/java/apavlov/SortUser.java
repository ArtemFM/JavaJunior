package apavlov;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The class SortUser for sort list Users by age.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class SortUser {
    /**
     * The method sort and convert List to TreeSet.
     *
     * @param list - list users;
     * @return - TreeSet users;
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * The method sort list by length name.
     *
     * @param list - list users;
     * @return - sort list by length name;
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return list;
    }

    /**
     * The method sort list by name or if name equals, sort by age.
     *
     * @param list - list users;
     * @return - sort list by name or if names equals, sort by age;
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int name = o1.getName().compareTo(o2.getName());
                return name == 0 ? o1.getAge() - o2.getAge() : name;
            }
        });
        return list;
    }
}

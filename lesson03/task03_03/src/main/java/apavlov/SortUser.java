package apavlov;

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
}

package apavlov;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * The class UserConvert for convert list typy user to HashMap.
 *
 * @author Pavlov Artem
 * @since 12.09.2017
 */
public class UserConvert {
    /**
     * Yhe method convert list type User to HashMap.
     *
     * @param list - list type User;
     * @return - HashMap;
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> listMap = new HashMap<>();
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User temp = iterator.next();
            if (temp != null) {
                listMap.put(temp.getId(), temp);
            }
        }
        return listMap;
    }
}

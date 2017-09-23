package apavlov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * The class BookDepartments for storage structure departments.
 *
 * @author Pavlov Artem
 * @since 23.09.2017
 */
public class BookDepartments {
    /**
     * The var - head book departments.
     */
    private Department department;

    /**
     * The var - key div departments.
     */
    private String prefix;

    /**
     * The constructor for class BookDepartments.
     *
     * @param prefix - key div departments;
     */
    public BookDepartments(String prefix) {
        this.prefix = prefix;
        this.department = new Department("");
    }

    /**
     * The constructor for class BookDepartments.
     *
     * @param prefix - key div departments;
     * @param departments - array departments;
     */
    public BookDepartments(String prefix, String[] departments) {
        this(prefix);
        addAllDepartments(departments);
    }

    /**
     * The method add new department to book (base).
     *
     * @param lineDepartment - full way departments;
     * @return true - is add; false - is no add;
     */
    public boolean addDepartment(String lineDepartment) {
        boolean result = lineDepartment != null && lineDepartment.length() > 0;
        if (result) {
            String[] heads = lineDepartment.split(prefix);
            TreeSet<Department> linkTree = this.department.subDepartments;
            while (heads.length > 0) {
                Department department = new Department(heads[0]);
                if (!linkTree.contains(department)) {
                    linkTree.add(department);
                }
                linkTree = linkTree.floor(department).subDepartments;
                heads = Arrays.copyOfRange(heads, 1, heads.length);
            }
        }
        return result;
    }

    /**
     * The method add array departments to book (base).
     *
     * @param departments - array ways departments;
     * @return true - is add; false - is not add;
     */
    public boolean addAllDepartments(String[] departments) {
        boolean result = false;
        if (departments != null && departments.length > 0) {
            for (String value : departments) {
                result = addDepartment(value) || result;
            }
        }
        return result;
    }

    /**
     * The recurse method fill list necessary sort.
     *
     * @param fullName - full way department;
     * @param innerTree - inner tree department;
     * @param list - list for write;
     * @param reverse - true - descending sort order; false - ascending sort order;
     * @return list departments;
     */
    public List<String> getFillListNeedSort(String fullName, TreeSet<Department> innerTree, List<String> list, boolean reverse) {
        if (innerTree.size() != 0) {
            Iterator<Department> iterator = reverse ? innerTree.descendingIterator() : innerTree.iterator();
            while (iterator.hasNext()) {
                Department innerDep = iterator.next();
                String newName = fullName.equals("") ? innerDep.name : String.format("%s%s%s", fullName, prefix, innerDep.name);
                list.add(newName);
                list = getFillListNeedSort(newName, innerDep.subDepartments, list, reverse);
            }
        }
        return list;
    }

    /**
     * The method return sort array departments.
     *
     * @param reverse - true - descending sort order; false - ascending sort order;
     * @return - array departments;
     */
    public String[] toArray(boolean reverse) {
        List<String> result = getFillListNeedSort(this.department.name, this.department.subDepartments, new ArrayList<>(), reverse);
        return result.toArray(new String[result.size()]);
    }

    /**
     * The inner class Department - structure department.
     *
     * @author Pavlov Artem
     * @since 23.09.2017
     */
    private class Department implements Comparable<Department> {
        /**
         * The var - name department.
         */
        private String name;

        /**
         * The structure this department.
         */
        private TreeSet<Department> subDepartments;

        /**
         * The constructor for class Department.
         *
         * @param name - name department;
         */
        Department(String name) {
            this.name = name;
            this.subDepartments = new TreeSet<>();
        }

        @Override
        public int compareTo(Department o) {
            return this.name.compareTo(o.name);
        }
    }
}



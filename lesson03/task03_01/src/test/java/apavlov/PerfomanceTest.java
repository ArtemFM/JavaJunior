package apavlov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * The test class PerfomanceTest for classes Perfomance.
 *
 * @author Pavlov Artem
 * @since 06.09.2017
 */
public class PerfomanceTest {
    /**
     * The link to object class`s Perfomance.
     */
    private Perfomance testTime = new Perfomance();

    /**
     * The var-link objects class ArrayList.
     */
    private Collection<String> arrayList = new ArrayList<>();

    /**
     * The var-link objects class LinkedList.
     */
    private Collection<String> linkedList = new LinkedList<>();

    /**
     * The var-link objects class TreeSet.
     */
    private Collection<String> treeSet = new TreeSet<>();

    /**
     * The var - count add elements.
     */
    private int countAdd = 30_000;

    /**
     * The var - count delete elements.
     */
    private int n = 3_000;

    /**
     * The test add elements to collections.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenAddElementsToCollections() throws Exception {
        String info = String.format("Add elements to collections;%s", System.lineSeparator());
        String test1 = String.format("ArrayList = %s;%s", testTime.convertMsToSec(testTime.add(arrayList, countAdd)), System.lineSeparator());
        String test2 = String.format("LinkedList = %s;%s", testTime.convertMsToSec(testTime.add(linkedList, countAdd)), System.lineSeparator());
        String test3 = String.format("TreeSet = %s;%s", testTime.convertMsToSec(testTime.add(treeSet, countAdd)), System.lineSeparator());
        System.out.printf("%s%s%s%s", info, test1, test2, test3);
    }

    /**
     * The test delete elements to collections.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenDeleteElementsToCollections() throws Exception {
        String info = String.format("Delete elements to collections;%s", System.lineSeparator());
        String test1 = String.format("ArrayList = %s;%s", testTime.convertMsToSec(testTime.delete(arrayList, n)), System.lineSeparator());
        String test2 = String.format("LinkedList = %s;%s", testTime.convertMsToSec(testTime.delete(linkedList, n)), System.lineSeparator());
        String test3 = String.format("TreeSet = %s;%s", testTime.convertMsToSec(testTime.delete(treeSet, n)), System.lineSeparator());
        System.out.printf("%s%s%s%s", info, test1, test2, test3);
    }
}
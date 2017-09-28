package apavlov;

import org.junit.Test;

/**
 * The test class PerformanceSetTest for test performance simple sets.
 *
 * @author Pavlov Artem
 * @since 28.09.2017
 */
public class PerformanceSetTest {
    /**
     * The count elements for addition collection.
     */
    private static final int TEST_LENGTH = 10;

    /**
     * The test array for addition collection.
     */
    private String[] arrayForAddition = fillArray(TEST_LENGTH, "Test");

    /**
     * The var ms now.
     */
    private long msStart;

    /**
     * The method filling array chose length.
     *
     * @param length - length array;
     * @param prefix - test for fill;
     * @return - array type String;
     */
    private String[] fillArray(int length, String prefix) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = String.format("%s - %s;", prefix, i);
        }
        return array;
    }

    /**
     * The method for storage ms start work.
     */
    private void start() {
        this.msStart = System.currentTimeMillis();
    }

    /**
     * The method return count seconds and milliseconds.
     *
     * @return - count seconds and milliseconds;
     */
    private String stop() {
        long msStop = System.currentTimeMillis() - this.msStart;
        return String.format("%s sec %s ms", msStop / 1000, msStop % 1000);
    }

    /**
     * The method return String speed work SimpleSet.
     *
     * @return - speed result;
     */
    private String checkSpeedAdditionSetArray() {
        start();
        SimpleSet<String> set = new SimpleSet<>(this.arrayForAddition);
        String strStop = stop();
        return String.format("%-23.22s :  %s", "Set (array list)", strStop);
    }

    /**
     * The method return String speed work SimpleSetLinked.
     *
     * @return - speed result;
     */
    private String checkSpeedAdditionSetLinked() {
        start();
        SimpleLinkedSet<String> set = new SimpleLinkedSet<>(this.arrayForAddition);
        String strStop = stop();
        return String.format("%-23.22s :  %s", "Set (linked list)", strStop);
    }

    /**
     * The method return String speed work SimpleHashSet.
     *
     * @return - speed result;
     */
    private String checkSpeedAdditionSetHashTable() {
        start();
        SimpleHashSet<String> set = new SimpleHashSet<>(this.arrayForAddition);
        String strStop = stop();
        return String.format("%-23.22s :  %s", "Set (hash table list)", strStop);
    }

    /**
     * The method forms share result performance simple collection.
     *
     * @throws Exception - check any errors;
     */
    @Test
    public void whenPrintResultPerformance() throws Exception {
        String sep = System.lineSeparator();
        String about = String.format("Performance test simple collections: length = %s;%s%s", this.TEST_LENGTH, sep, sep);
        System.out.printf("%s%s;%s%s;%s%s;", about, checkSpeedAdditionSetArray(), sep,
                checkSpeedAdditionSetLinked(), sep, checkSpeedAdditionSetHashTable());

    }
}

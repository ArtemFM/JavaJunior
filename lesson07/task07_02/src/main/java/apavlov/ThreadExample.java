package apavlov;

/**
 * The class ThreadExample - thread for check (test) trouble multithreading.
 *
 * @author Pavlov Artem
 * @since 17.12.2017
 */
public class ThreadExample extends Thread {
    /**
     * The var - result work thread.
     */
    private int value;

    /**
     * The test class for check thread.
     */
    private ExampleObject obj;

    /**
     * The spep edit to value.
     */
    private int step;

    /**
     * Max range cycle.
     */
    private int maxCycle;

    /**
     * The getter for var value.
     *
     * @return value;
     */
    public int getValue() {
        return value;
    }

    /**
     * The constructor for class ThreadExample.
     *
     * @param name - name thread;
     * @param obj - link to test class;
     * @param step - step edit value;
     * @param maxCycle - max range cycle;
     */
    public ThreadExample(String name, ExampleObject obj, int step, int maxCycle) {
        super(name);
        this.obj = obj;
        this.step = step;
        this.maxCycle = maxCycle;
    }

    @Override
    public void run() {
        int value = obj.getValue();
        int count = 0;
        while (count++ < maxCycle) {
            value += step;
        }
        System.out.printf("The thread [%s] is finish.%s", getName(), System.lineSeparator());
        this.value = value;
    }
}

/**
 * The class ExampleObject - use for test work thread.
 *
 * @author Pavlov Artem
 * @since 17.12.2017
 */
class ExampleObject {
    /**
     * The value.
     */
    private int value;

    /**
     * The getter for var value.
     *
     * @return value;
     */
    public int getValue() {
        return value;
    }

    /**
     * The constructor for class ExampleObject.
     *
     * @param value - value;
     */
    ExampleObject(int value) {
        this.value = value;
    }
}

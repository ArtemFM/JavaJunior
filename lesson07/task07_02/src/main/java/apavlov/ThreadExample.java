package apavlov;

/**
 * The class ThreadExample - thread for check (test) trouble multithreading.
 *
 * @author Pavlov Artem
 * @since 17.12.2017
 */
public class ThreadExample extends Thread {
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
        int count = 0;
        int value = this.obj.getValue();
        while (count++ < maxCycle) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value += this.step;
            this.obj.setValue(value);
        }
        System.out.printf("Value thread = %d; Value object = %d;%s", value, this.obj.getValue(), System.lineSeparator());
        System.out.printf("The thread [%s] is finish.%s", getName(), System.lineSeparator());
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
     * The setter for var value.
     *
     * @param  value - value;
     */
    public void setValue(int value) {
        this.value = value;
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

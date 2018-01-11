package apavlov.threads;

import apavlov.input.Input;
import apavlov.lift.Lift;
import apavlov.settings.Attribute;

/**
 * The class ControllerThread - thread controller lift.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class ControllerThread extends Thread {
    /**
     * The field work to strings.
     */
    private StringBuilder sb;

    /**
     * The link object Lift.
     */
    private Lift lift;

    /**
     * The link object Input.
     */
    private Input input;

    /**
     * The link object attribute.
     */
    private Attribute attribute;

    /**
     * The constructor for class ControllerThread.
     *
     * @param group - group threads;
     * @param lift - link object lift;
     * @param input - lint object input;
     * @param attribute - link object attribute;
     */
    public ControllerThread(ThreadGroup group, Lift lift, Input input, Attribute attribute) {
        super(group, "Controller");
        this.lift = lift;
        this.input = input;
        this.attribute = attribute;
        this.sb = new StringBuilder();
    }

    @Override
    public void run() {
        System.out.println("Start controller...");
        fillMenu();
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(this.sb);
            int option = this.input.readConsole("Select menu: ", 1, 3);
            if (option != -1) {
                if (option == 3) {
                    System.out.println("Wait stop system...");
                    getThreadGroup().interrupt();
                    break;
                } else {
                    int floor = this.input.readConsole("Input floor: ", 1, this.attribute.getAmountFloor());
                    this.lift.add(Lift.Press.values()[option - 1], floor);
                }
            }
        }
        System.out.println("Stop controller...");
    }

    /**
     * The method print to console menu select user.
     */
    private void fillMenu() {
        sb.delete(0, sb.length());
        sb.append("1. Press button in the lift;").append(System.lineSeparator());
        sb.append("2. Press button out the lift;").append(System.lineSeparator());
        sb.append("3. Stop system and exit;").append(System.lineSeparator());
    }
}

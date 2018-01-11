package apavlov;

import apavlov.input.ValidateInput;
import apavlov.lift.Lift;
import apavlov.settings.Attribute;
import apavlov.settings.CLI;
import apavlov.threads.ControllerThread;
import apavlov.threads.MoveThread;

/**
 * The class StartGUI - for start work lift.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class StartGUI {
    /**
     * The method is point enter program.
     *
     * @param args - parameters work program;
     */
    public static void main(String[] args) {
        Attribute attribute = new CLI(args, 2, 20).getAttribute();
        if (attribute != null) {
            System.out.println("START SYSTEM.");
            Lift lift = new Lift();
            ThreadGroup group = new ThreadGroup("Lift");
            new ControllerThread(group, lift, new ValidateInput(), attribute).start();
            new MoveThread(group, lift, attribute, 1).start();
            while (true) {
                if (group.activeCount() == 0) {
                    break;
                }
            }
            System.out.println("STOP SYSTEM.");
        }
    }
}

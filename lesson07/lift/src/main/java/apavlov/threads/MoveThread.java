package apavlov.threads;

import apavlov.lift.Lift;
import apavlov.settings.Attribute;

/**
 * The class MoveThread - thread move lift.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class MoveThread extends Thread {
    /**
     * The link object Attribute.
     */
    private final Attribute attribute;

    /**
     * The link object Lift.
     */
    private Lift lift;

    /**
     * The position lift now (floor).
     */
    private int positionFloor;

    /**
     * The constructor for class MoveThread.
     *
     * @param group - group thread;
     * @param lift - link object Lift;
     * @param attribute - link object Attribute;
     * @param positionFloor - start position lift;
     */
    public MoveThread(ThreadGroup group, Lift lift, Attribute attribute, int positionFloor) {
        super(group, "Move");
        this.attribute = attribute;
        this.positionFloor = positionFloor;
        this.lift = lift;
    }

    @Override
    public void run() {
        System.out.println("Start lift...");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int nextFloor = this.lift.get();
                System.out.printf("LIFT: next floor %d;%s", nextFloor, System.lineSeparator());
                if (this.positionFloor != nextFloor) {
                    move(nextFloor);
                } else {
                    parking();
                    this.lift.remove(nextFloor);
                }
            }
        } catch (InterruptedException e) {
            //empty block
        }
        System.out.println("Stop lift...");
    }

    /**
     * The method move lift.
     *
     * @param nextFloor - next floor for move lift;
     * @throws InterruptedException - if interrupt;
     */
    private void move(int nextFloor) throws InterruptedException {
        double height = (this.positionFloor - 1) * this.attribute.getHeightFloor();
        double heightEnd = (nextFloor - 1) * this.attribute.getHeightFloor();
        long delay = 100;
        double speedDelay = this.attribute.getSpeedMS() / (1000 / delay);
        while (nextFloor != this.positionFloor) {
            Thread.sleep(delay);
            if (height < heightEnd) {
                height += speedDelay;
            } else {
                height -= speedDelay;
            }
            int floor = (int) (height / this.attribute.getHeightFloor()) + 1;
            if (floor != this.positionFloor) {
                positionFloor = floor;
                if (this.positionFloor != nextFloor) {
                    System.out.printf("LIFT: move %d floor;%s", floor, System.lineSeparator());
                }
            }
        }
    }

    /**
     * The method parking lift on the floor.
     *
     * @throws InterruptedException - if interrupt;
     */
    private void parking() throws InterruptedException {
        System.out.printf("LIFT: stop on the %d floor;%s", this.positionFloor, System.lineSeparator());
        System.out.println("LIFT: open door;");
        Thread.sleep(this.attribute.getSecondsParking() * 1000);
        System.out.println("LIFT: close door;");
    }
}

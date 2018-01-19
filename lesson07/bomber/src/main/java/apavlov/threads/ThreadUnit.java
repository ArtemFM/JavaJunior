package apavlov.threads;

import apavlov.ai.AI;
import apavlov.api.Move;
import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.element.unit.Unit;
import java.util.Random;

/**
 * The class thread ThreadUnit - for work with Unit.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class ThreadUnit extends Thread {
    /**
     * The constant - default delay step unit.
     */
    private static final int SECOND = 1000;

    /**
     * The constant - default milliseconds waiting cell.
     */
    private static final long DELAY_BLOCK = 500;

    /**
     * The constant - 10% chance set bomb.
     */
    private static final double CHANCE_BOMB = 0.1;

    /**
     * The link object Random for generate random action.
     */
    private final Random random;

    /**
     * The milliseconds waiting cell.
     */
    private final long delayBlock;

    /**
     * The link object Unit for work.
     */
    private final Unit unit;

    /**
     * The milliseconds waiting next move.
     */
    private final long delay;

    /**
     * The link object AI for intellectual choice course.
     */
    private final AI ai;

    /**
     * The link enum Move - now course.
     */
    private Move move;

    /**
     * The constructor for class ThreadUnit.
     *
     * @param group - group threads;
     * @param unit - link object Unit;
     * @param delayBlock - milliseconds waiting cell;
     */
    private ThreadUnit(ThreadGroup group, Unit unit, long delayBlock) {
        super(group, "ThreadUnit");
        this.unit = unit;
        this.delay = setDelay(unit);
        this.delayBlock = delayBlock > 0 ? delayBlock : DELAY_BLOCK;
        this.random = new Random();
        this.ai = new AI(unit);
    }

    /**
     * The constructor for class ThreadUnit.
     *
     * @param group - group threads;
     * @param unit - link object Unit;
     */
    public ThreadUnit(ThreadGroup group, Unit unit) {
        this(group, unit, DELAY_BLOCK);
    }

    /**
     * The method return number (if 0 - set bomb).
     *
     * @return random number;
     */
    private int getRandomChance() {
        return this.random.nextInt((int) (1 / CHANCE_BOMB));
    }

    /**
     * The method set correct delay move unit.
     *
     * @param unit - unit;
     * @return correct delay move;
     */
    private int setDelay(Unit unit) {
        int result = 0;
        if (unit != null) {
            int speed = unit.getSpeed();
            result = speed > 0 ? SECOND / speed : SECOND;
        }
        return result;
    }

    @Override
    public void run() {
        if (this.unit != null && this.unit.checkPosition(this.unit.getPosition())) {
            try {
                while (!Thread.currentThread().isInterrupted() && !this.unit.isDead()) {
                    this.move = this.ai.getNextMove(this.move);
                    if (this.move != null) {
                        Position next = move.nextPosition(this.unit.getPosition());
                        Cell cell = this.unit.getCell(next);
                        if (cell != null) {
                            if (getRandomChance() == 0) {
                                this.unit.putBomb();
                            }
                            if (cell.lock()) {
                                move(this.unit.getPosition(), next);
                            } else {
                                cell.putAttack();
                                if (cell.tryLock(this.delayBlock)) {
                                    move(this.unit.getPosition(), next);
                                }
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                //empty block;
            }
        }
    }

    /**
     * The method move unit with first position to second position.
     *
     * @param src - first position (now);
     * @param dest - second position (next);
     * @throws InterruptedException - exception throw Thread.sleep().
     */
    private void move(Position src, Position dest) throws InterruptedException {
        this.unit.getCell(src).removeUnit();
        this.unit.setPosition(dest);
        this.unit.getCell(dest).putUnit(this.unit);
        Thread.sleep(this.delay);
    }
}

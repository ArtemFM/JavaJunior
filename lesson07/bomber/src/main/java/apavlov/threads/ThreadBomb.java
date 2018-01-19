package apavlov.threads;

import apavlov.model.element.bomb.Bomb;

/**
 * The class thread ThreadBomb - for work with element Bomb.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class ThreadBomb extends Thread {
    /**
     * The constant - delay cycle thread.
     */
    private static final long DELAY = 100;

    /**
     * The link on the class Bomb.
     */
    private final Bomb bomb;

    /**
     * The constructor for class ThreadBomb.
     *
     * @param bomb - link object Bomb;
     */
    public ThreadBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    @Override
    public void run() {
        if (this.bomb != null) {
            long ms = this.bomb.getDelaySeconds() * 1000;
            try {
                while (!Thread.currentThread().isInterrupted() && !bomb.isDead()) {
                    Thread.sleep(DELAY);
                    ms -= DELAY;
                    if (ms <= 0) {
                        this.bomb.explosionBomb();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

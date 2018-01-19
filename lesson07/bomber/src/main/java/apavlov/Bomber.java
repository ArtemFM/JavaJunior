package apavlov;

import apavlov.game.Game;

/**
 * The class Bomber - use for start game Bomber Man.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Bomber {
    /**
     * The method is point start program.
     *
     * @param args - parameters to start;
     * @throws InterruptedException - exception Thread.sleep();
     */
    public static void main(String[] args) throws InterruptedException {
       new Game().start();
    }
}

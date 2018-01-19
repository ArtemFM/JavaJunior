package apavlov.game;

import apavlov.model.element.Element;
import apavlov.model.element.landscape.MakeLandscape;
import apavlov.model.element.unit.MakeUnit;
import apavlov.model.element.unit.Unit;
import apavlov.board.Board;
import apavlov.board.Position;
import apavlov.threads.ThreadUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class Game - main class game.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Game {
    /**
     * The constant - default position for player.
     */
    private static final Position START_POSITION_PLAYER = new Position(1, 1);

    /**
     * The constant - default min distance set elements and units from the player.
     */
    private static final int DISTANCE_FROM_PLAYER = 5;

    /**
     * The link object for generation random values.
     */
    private final Random random;

    /**
     * The link object game board.
     */
    private final Board board;

    /**
     * The link object unit Player.
     */
    private final Unit player;

    /**
     * The list units (monsters).
     */
    private final List<Unit> monsters;

    /**
     * The group threads all units.
     */
    private final ThreadGroup group;

    /**
     * The thread for player.
     */
    private ThreadUnit threadUnit;

    /**
     * The list for threads monsters.
     */
    private List<ThreadUnit> threadMonstersList;

    /**
     * The default constructor for class Game.
     */
    public Game() {
        this.board = new Board();
        this.random = new Random();
        this.group = new ThreadGroup("All Threads");
        this.player = addUnit(START_POSITION_PLAYER, MakeUnit.PLAYER);
        this.monsters = new ArrayList<>();
        this.threadMonstersList = new ArrayList<>();
    }

    /**
     * The method check is dead all monsters or is not dead.
     *
     * @return true, if all monsters is dead or false;
     */
    private boolean allMonstersIsDead() {
        boolean result = true;
        if (this.monsters != null && !this.monsters.isEmpty()) {
            for (Unit unit : this.monsters) {
                result = unit.isDead();
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * The method start game.
     *
     * @throws InterruptedException - exception throw Thread.sleep();
     */
    public void start() throws InterruptedException {
        initElements();
        createThreadPlayer();
        createThreadMonsters();
        startThreads();
        if (this.player != null) {
            while (!this.player.isDead() && !allMonstersIsDead()) {
                printBoard();
                Thread.sleep(1000);
            }
            printBoard();
            stopThreads();
            if (this.player.isDead()) {
                System.out.println("GAME OVER");
            } else {
                System.out.println("YOU WIN!!!");
            }
        }
    }

    /**
     * The method print to console game board.
     */
    private void printBoard() {
        System.out.println(this.board);
    }

    /**
     * The method start all threads.
     */
    private void startThreads() {
        if (this.threadUnit != null) {
            this.threadUnit.start();
        }
        if (this.threadMonstersList != null) {
            for (ThreadUnit thread : this.threadMonstersList) {
                thread.start();
            }
        }
    }

    /**
     * The method stopped all run threads.
     */
    private void stopThreads() {
        if (this.group != null) {
            this.group.interrupt();
            while (true) {
                if (this.group.activeCount() == 0) {
                    break;
                }
            }
        }
    }

    /**
     * The method create thread player.
     */
    private void createThreadPlayer() {
        if (this.player != null) {
            this.threadUnit = new ThreadUnit(this.group, this.player);
        }
    }

    /**
     * The method create threads monsters.
     */
    private void createThreadMonsters() {
        if (this.monsters != null && !this.monsters.isEmpty()) {
            for (Unit unit : this.monsters) {
                this.threadMonstersList.add(new ThreadUnit(this.group, unit));
            }
        }
    }

    /**
     * The method initializes elements and units to game board.
     */
    private void initElements() {
        addRandomElements(MakeLandscape.STONE, AmountElement.AMOUNT_STONE.getAmount());
        addRandomElements(MakeLandscape.BRICK, AmountElement.AMOUNT_BRICKS.getAmount());
        addRandomElements(MakeLandscape.STRONG_BRICK, AmountElement.AMOUNT_STRONG_BRICKS.getAmount());
        this.monsters.addAll(addRandomUnits(MakeUnit.ONION, AmountElement.AMOUNT_MONSTER_ONION.getAmount()));
        this.monsters.addAll(addRandomUnits(MakeUnit.DROP, AmountElement.AMOUNT_MONSTER_DROP.getAmount()));
        this.monsters.addAll(addRandomUnits(MakeUnit.SPONGE, AmountElement.AMOUNT_MONSTER_SPONGE.getAmount()));
    }

    /**
     * The method add new unit to game board.
     *
     * @param position - position for addition;
     * @param unitMake - enum, for create necessary unit;
     * @return creating unit;
     */
    private Unit addUnit(Position position, MakeUnit unitMake) {
        Unit result = null;
        if (position != null && unitMake != null) {
            Unit unit = unitMake.createUnit(position, this.board.getBoard());
            result = this.board.addUnit(unit) ? unit : null;
        }
        return result;
    }

    /**
     * The method add new element to game board.
     *
     * @param position  - position for addition;
     * @param landscape - enum, for create necessary element;
     * @return creating element;
     */
    private Element addElement(Position position, MakeLandscape landscape) {
        Element result = null;
        if (position != null && landscape != null) {
            Element element = landscape.createElement(position, this.board.getBoard());
            result = this.board.addElement(element) ? element : null;
        }
        return result;
    }

    /**
     * The method random creating set amount elements.
     *
     * @param landscape - enum, for create necessary elements landscape;
     * @param amount    - amount elements for creating;
     */
    private void addRandomElements(MakeLandscape landscape, int amount) {
        if (amount > 0 && landscape != null) {
            List<Position> listPosition = this.board.getListEmptyCell();
            while (!listPosition.isEmpty() && amount != 0) {
                int index = random.nextInt(listPosition.size());
                Position position = listPosition.remove(index);
                if (checkDistancePosition(position, START_POSITION_PLAYER)) {
                    if (addElement(position, landscape) != null) {
                        amount--;
                    }
                }
            }
        }
    }

    /**
     * The method random creating and return list set amount units.
     *
     * @param makeUnit - enum, for create necessary unit;
     * @param amount   - amount unit for creating;
     * @return list random creating units;
     */
    private List<Unit> addRandomUnits(MakeUnit makeUnit, int amount) {
        List<Unit> units = new ArrayList<>();
        if (amount > 0 && makeUnit != null) {
            List<Position> listPosition = this.board.getListEmptyCell();
            while (!listPosition.isEmpty() && amount != 0) {
                int index = random.nextInt(listPosition.size());
                Position position = listPosition.remove(index);
                if (checkDistancePosition(position, START_POSITION_PLAYER)) {
                    Unit unit;
                    if ((unit = addUnit(position, makeUnit)) != null) {
                        units.add(unit);
                        amount--;
                    }
                }
            }
        }
        return units;
    }

    /**
     * The method check two positions on the distance.
     *
     * @param position       - position first;
     * @param positionPlayer - position second;
     * @return true, if two position correspond set min distance;
     */
    private boolean checkDistancePosition(Position position, Position positionPlayer) {
        boolean result = position != null && positionPlayer != null;
        if (result) {
            int x = positionPlayer.getX() + DISTANCE_FROM_PLAYER;
            int y = positionPlayer.getY() + DISTANCE_FROM_PLAYER;
            result = x < position.getX() || y < position.getY();
            if (!result) {
                x = positionPlayer.getX() - DISTANCE_FROM_PLAYER;
                y = positionPlayer.getY() - DISTANCE_FROM_PLAYER;
                result = x > position.getX() || y > position.getY();
            }
        }
        return result;
    }
}

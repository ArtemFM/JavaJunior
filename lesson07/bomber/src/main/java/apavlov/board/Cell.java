package apavlov.board;

import apavlov.model.Empty;
import apavlov.model.Model;
import apavlov.model.element.Element;
import apavlov.model.element.unit.Unit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The class Cell - model cell game board.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public class Cell {
    /**
     * The constant - default model cell.
     */
    private static final Model DEFAULT_MODEL = new Empty();

    /**
     * The link object blocking.
     */
    private final ReentrantLock lock;

    /**
     * The element landscape or bomb.
     */
    private Element element;

    /**
     * The unit.
     */
    private Unit unit;

    /**
     * The default constructor for class Cell.
     */
    public Cell() {
        this.lock = new ReentrantLock();
    }

    /**
     * The constructor for class Cell.
     *
     * @param element - element for addition to this cell;
     */
    public Cell(Element element) {
        this();
        putElement(element);
    }

    /**
     * The method try get lock during ms.
     *
     * @param ms - ms for waiting;
     * @return true, if get lock or false;
     * @throws InterruptedException - exception method tryLock();
     */
    public boolean tryLock(long ms) throws InterruptedException {
        return this.lock.tryLock(ms, TimeUnit.MILLISECONDS);
    }

    /**
     * The method try get lock.
     *
     * @return true, if is lock or false;
     */
    public boolean lock() {
        return this.lock.tryLock();
    }

    /**
     * The method remove blocking, if lock take this thread.
     */
    private void unlock() {
        if (this.element == null && this.unit == null) {
            if (this.lock.isHeldByCurrentThread()) {
                this.lock.unlock();
            }
        }
    }

    /**
     * The method check cell have element or not have.
     *
     * @return true, if cell have element or false;
     */
    public boolean isElement() {
        return this.element != null;
    }

    /**
     * The method put element to this cell, if cell not have other element.
     *
     * @param element - element for put;
     * @return true - if cell not have element or false;
     */
    public boolean putElement(Element element) {
        boolean result = element != null && !isElement();
        if (result) {
            this.element = element;
            lock();
        }
        return result;
    }

    /**
     * The method put unit to this cell, if cell not have other unit.
     *
     * @param unit - unit for put;
     * @return true - if cell not have unit or false;
     */
    public boolean putUnit(Unit unit) {
        boolean result = unit != null && isEmpty();
        if (result) {
            this.unit = unit;
            lock();
        }
        return result;
    }

    /**
     * The method remove element to this cell.
     */
    private void removeElement() {
        this.element = null;
        unlock();
    }

    /**
     * The method remove unit to this cell.
     */
    public void removeUnit() {
        this.unit = null;
        unlock();
    }

    /**
     * The method check, if cell have element or unit and he is dead, then remove element (unit).
     */
    private void checkIsDead() {
        if (this.element != null && this.element.isDead()) {
            removeElement();
        }
        if (this.unit != null && this.unit.isDead()) {
            removeUnit();
        }
        unlock();
    }

    /**
     * The method performs operations at explosion.
     */
    public void explosionBomb() {
        if (this.element != null) {
            this.element.explosionBomb();
        }
        if (this.unit != null) {
            this.unit.explosionBomb();
        }
        checkIsDead();
    }

    /**
     * The method performs operations at attack.
     */
    public void putAttack() {
        if (this.element != null) {
            this.element.putAttack();
        }
        if (this.unit != null) {
            this.unit.putAttack();
        }
        checkIsDead();
    }

    /**
     * The method check cell is empty or is not empty.
     *
     * @return true, if cell is empty or false;
     */
    public boolean isEmpty() {
        return this.element == null && this.unit == null;
    }

    @Override
    public String toString() {
        String result;
        if (this.element != null) {
            result = this.element.toString();
        } else if (this.unit != null) {
            result = this.unit.toString();
        } else {
            result = DEFAULT_MODEL.toString();
        }
        return result;
    }
}

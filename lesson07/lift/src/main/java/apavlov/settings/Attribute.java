package apavlov.settings;

/**
 * The class Attribute - settings lift.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class Attribute {
    /**
     * The constant - amount tabs to start offer.
     */
    private static final int TAB = 3;

    /**
     * The constant - indent.
     */
    private static final int INDENT = 25;

    /**
     * The constant - new line.
     */
    private static final String LS = System.lineSeparator();

    /**
     * StringBuilder - class work to strings.
     */
    private StringBuilder sb;

    /**
     * Amount floors.
     */
    private int amountFloor;

    /**
     * The time parking lift to seconds.
     */
    private int secondsParking;

    /**
     * Speed lift to meters in seconds.
     */
    private double speedMS;

    /**
     * Height floor.
     */
    private double heightFloor;

    /**
     * The getter for field amountFloor.
     *
     * @return amount floors.
     */
    public int getAmountFloor() {
        return amountFloor;
    }

    /**
     * The getter for field secondsParking.
     *
     * @return time parking [seconds];
     */
    public int getSecondsParking() {
        return secondsParking;
    }

    /**
     * The getter for field speedMS.
     *
     * @return speed lift [m/s].
     */
    public double getSpeedMS() {
        return speedMS;
    }

    /**
     * The getter for field heightFloor.
     *
     * @return height floor [meters].
     */
    public double getHeightFloor() {
        return heightFloor;
    }

    /**
     * The constructor for class Attribute.
     *
     * @param amountFloor - amount floors;
     * @param secondsParking - time parking [seconds];
     * @param speedMS - speed lift [m/s];
     * @param heightFloor - height floor [meters];
     */
    public Attribute(int amountFloor, int secondsParking, double speedMS, double heightFloor) {
       this.amountFloor = amountFloor;
       this.secondsParking = secondsParking;
       this.speedMS = speedMS;
       this.heightFloor = heightFloor;
       this.sb = new StringBuilder();
    }

    @Override
    public String toString() {
        sb.delete(0, sb.length());
        String tabs = String.format(String.format("%s%d%s", "%", TAB, "s"), "");
        String indent = String.format("%s-%d%s", "%", INDENT, "s");
        sb.append("Attributes:").append(LS);
        sb.append(tabs).append(String.format(indent, "amount floors: ")).append(this.amountFloor).append(";").append(LS);
        sb.append(tabs).append(String.format(indent, "height floor [meters]: ")).append(String.format("%.2f", this.heightFloor)).append(";").append(LS);
        sb.append(tabs).append(String.format(indent, "speed lift [m/s]: ")).append(String.format("%.2f", this.speedMS)).append(";").append(LS);
        sb.append(tabs).append(String.format(indent, "time parking [seconds]: ")).append(this.secondsParking).append(";").append(LS);
        return sb.toString();
    }
}

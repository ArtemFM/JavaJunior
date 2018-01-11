package apavlov.settings;

import apavlov.error.IncorrectAttributeException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;


/**
 * The class CLI - parse attributes.
 *
 * @author Pavlov Artem
 * @since 11.01.2018
 */
public class CLI {
    /**
     * The array attributes with start program..
     */
    private String[] args;

    /**
     * The collection need attributes.
     */
    private Options options;

    /**
     * Min amount floors to house.
     */
    private int minFloor;

    /**
     * Max amount floors to house.
     */
    private int maxFloor;

    /**
     * The constructor for class CLI.
     *
     * @param args     - array attributes;
     * @param minFloor - min floors to house;
     * @param maxFloor - max floors to house;
     */
    public CLI(String[] args, int minFloor, int maxFloor) {
        this.args = args;
        this.options = new Options();
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
    }

    /**
     * The method init options need attributes.
     */
    private void init() {
        this.options.addOption("f", "floors", true, String.format("amount floors to house [%d..%d];", this.minFloor, this.maxFloor));
        this.options.addOption("?", "help", false, "Show help: ");
        this.options.addOption("h", "height", true, "height floor [meters];");
        this.options.addOption("s", "speed", true, "speed lift [m/s];");
        this.options.addOption("p", "parking", true, "time parking lift [seconds];");
    }

    /**
     * The method parse options to object Attribute.
     *
     * @return link object Attribute;
     */
    public Attribute getAttribute() {
        Attribute result = null;
        init();
        CommandLineParser parser = new BasicParser();
        CommandLine cmd;
        int floors = 0;
        double speed = 0;
        double height = 0;
        int parking = 0;
        try {
            cmd = parser.parse(this.options, this.args);
            if (cmd.hasOption("f")) {
                floors = stringToInt(cmd.getOptionValue("f"));
            }
            if (cmd.hasOption("s")) {
                speed = stringToDouble(cmd.getOptionValue("s"));
            }
            if (cmd.hasOption("h")) {
                height = stringToDouble(cmd.getOptionValue("h"));
            }
            if (cmd.hasOption("p")) {
                parking = stringToInt(cmd.getOptionValue("p"));
            }
            if (cmd.hasOption("?")) {
                throw new IncorrectAttributeException("print help");
            }
            if (checkAttributes(floors, parking, speed, height)) {
                result = new Attribute(floors, parking, speed, height);
            } else {
                printHelp();
            }
        } catch (ParseException | IncorrectAttributeException e) {
            printHelp();
        }
        return result;
    }

    /**
     * The method check correct attributes.
     *
     * @param floors  - amount floors;
     * @param parking - time parking [seconds];
     * @param speed   - speed lift [m/s];
     * @param height  - height floor [meters];
     * @return - true - is correct attributes or false;
     */
    private boolean checkAttributes(int floors, int parking, double speed, double height) {
        return parking > 0 && speed > 0 && height > 0 && floors >= this.minFloor && floors <= this.maxFloor;
    }


    /**
     * The method parse string to double.
     *
     * @param value - value type String;
     * @return value type double;
     * @throws IncorrectAttributeException - if String value is not number;
     */
    private double stringToDouble(String value) throws IncorrectAttributeException {
        double result;
        try {
            result = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IncorrectAttributeException("Error: input is not number.");
        }
        return result;
    }

    /**
     * The method parse string to int.
     *
     * @param value - value type String;
     * @return value type int;
     * @throws IncorrectAttributeException - if String value is not number;
     */
    private int stringToInt(String value) throws IncorrectAttributeException {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IncorrectAttributeException("Error: input is not number.");
        }
        return result;
    }

    /**
     * The method print help program to console.
     */
    private void printHelp() {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("Lift", options);
        System.exit(0);
    }
}

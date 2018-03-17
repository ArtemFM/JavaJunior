package apavlov;

import apavlov.base.BaseSQL;
import apavlov.util.Settings;
import apavlov.xml.XMLParse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * The class Starter - start all processed tasks.
 *
 * @author Pavlov Artem
 * @since 17.03.2018
 */
class Starter {
    /**
     * The constant - link class Logger (log4j 2) for logging system.
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * The constant - name file properties connect to base SQL.
     */
    private static final String NAME_FILE_PROPERTIES = "base.properties";

    /**
     * The constant - default amount values for add to base SQL.
     */
    private static final int DEFAULT_AMOUNT_FIELD = 10_000;

    /**
     * The constant - name table for create to base SQL.
     */
    private static final String NAME_TABLE = "test";

    /**
     * The constant - name row to table.
     */
    private static final String NAME_ROW = "field";

    /**
     * The constant - first name file xml.
     */
    private static final String FIRST_XML = "1.xml";

    /**
     * The constant - second name file xml.
     */
    private static final String SECOND_XML = "2.xml";

    /**
     * The var - amount values for addition to base SQL.
     */
    private int amountValues;

    /**
     * The constructor for class Starter.
     *
     * @param amountValues - amount values for addition to base SQL;
     */
    Starter(int amountValues) {
        this.amountValues = this.formatAmountValues(amountValues);
    }

    /**
     * The main method this class`s. Start all processes.
     */
    void start() {
        Settings settings = new Settings(NAME_FILE_PROPERTIES, LOG);
        if (settings.load() && this.checkDriverJDBC()) {
            new XMLParse(this.connectToBase(settings), LOG).start(FIRST_XML, SECOND_XML);
        }
    }

    /**
     * The method check var amountValues.
     * If amountValues <= 0, then put default value (constant).
     *
     * @param amountValues - var for check and format;
     * @return correct number;
     */
    private int formatAmountValues(int amountValues) {
        if (amountValues <= 0) {
            LOG.error(String.format("The amount values [%d] incorrect. Set default field = %d", amountValues, DEFAULT_AMOUNT_FIELD));
            amountValues = DEFAULT_AMOUNT_FIELD;
        }
        return amountValues;
    }

    /**
     * The method for connecting and work with base SQL.
     *
     * @param settings - link class`s Settings (settings for connected);
     * @return list values from base SQL;
     */
    private LinkedList<Integer> connectToBase(Settings settings) {
        LinkedList<Integer> listValues = null;
        try (Connection connection = DriverManager.getConnection(settings.getURL(), settings.getLogin(), settings.getPassword())) {
            LOG.info("Connect to base is successfully.");
            listValues = new BaseSQL(connection, LOG).start(NAME_TABLE, NAME_ROW, this.amountValues);
        } catch (SQLException e) {
            LOG.fatal(String.format("Connection to base SQL fatal [%s].", settings), e);
        }
        return listValues;
    }

    /**
     * The method check JDBC driver.
     *
     * @return true, if driver is found or false;
     */
    private boolean checkDriverJDBC() {
        boolean result = false;
        try {
            Class.forName("org.postgresql.Driver");
            LOG.info("PostgreSQL JDBC Driver successfully connected.");
            result = true;
        } catch (ClassNotFoundException e) {
            LOG.fatal("PostgreSQL JDBC Driver is not found.", e);
        }
        return result;
    }
}

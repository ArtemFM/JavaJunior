package apavlov.base;

import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * The class BaseSQL - used for work to database SQL.
 *
 * @author Pavlov Artem
 * @since 17.03.2018
 */
public class BaseSQL {
    /**
     * The link to object Connection.
     */
    private final Connection connection;

    /**
     * The link to object Logger (log system).
     */
    private final Logger log;

    /**
     * The constructor for class BaseSQL.
     *
     * @param connection - link object Connection;
     * @param log - link object Logger;
     */
    public BaseSQL(Connection connection, Logger log) {
        this.connection = connection;
        this.log = log;
    }

    /**
     * The method start all operations work with base SQL.
     *
     * @param nameTable - name table base SQL;
     * @param nameRow - name row to table;
     * @param amountValues - amount values for addition;
     * @return list all values to table base SQL;
     */
    public LinkedList<Integer> start(String nameTable, String nameRow, int amountValues) {
        LinkedList<Integer> resultList = new LinkedList<>();
        if (this.createTable(nameTable, nameRow)) {
            if (!this.isEmptyTable(nameTable)) {
                this.log.info(String.format("Start clear table [%s]...", nameTable));
                this.clearTable(nameTable);
                this.log.info(String.format("Table [%s] is clearing.", nameTable));
            } else {
                this.log.info(String.format("Table [%s] is creating.", nameTable));
            }
            this.log.info(String.format("Start addition values to table [%s]...", nameTable));
            if (this.addValues(nameTable, nameRow, amountValues)) {
                this.log.info(String.format("Finish addition values to table [%s].", nameTable));
                this.log.info(String.format("Start write values table [%s] to collections...", nameTable));
                resultList = toArrayTable(nameTable, nameRow);
                this.log.info(String.format("Finish write values table [%s] to collections.", nameTable));
            }
        }
        return resultList;
    }

    /**
     * The method create table to base SQL.
     *
     * @param nameTable - name table for create;
     * @param nameRow - name row to table;
     * @return true, if table is create or false;
     */
    private boolean createTable(String nameTable, String nameRow) {
        boolean result = false;
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY NOT NULL);", nameTable, nameRow);
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.execute();
            result = true;
        } catch (SQLException e) {
            this.log.fatal(String.format("The table [%s] is not create with row [%s].", nameTable, nameRow));
        }
        return result;
    }

    /**
     * The method check is empty table or is not empty.
     *
     * @param nameTable - name table for check;
     * @return true, if table is empty, or false;
     */
    private boolean isEmptyTable(String nameTable) {
        boolean isEmpty = false;
        String sql = String.format("SELECT * FROM %s LIMIT 1;", nameTable);
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            isEmpty = !rs.next();
        } catch (SQLException e) {
            this.log.fatal(String.format("The table [%s] is not found.", nameTable));
        }
        return isEmpty;
    }

    /**
     * The method add values to table base SQL.
     *
     * @param nameTable - name table for addition;
     * @param nameRow - name row for addition;
     * @param amountValues - amount values for add;
     * @return true, if add values correct or false;
     */
    private boolean addValues(String nameTable, String nameRow, int amountValues) {
        boolean result = false;
        String sql = this.getSQLQueryAdd(nameTable, nameRow, amountValues);
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            this.log.fatal(String.format("Error adding values to table [%s] to row [%s]", nameTable, nameRow));
        }
        return result;
    }

    /**
     * The method generate script SQL for addition to table values.
     *
     * @param nameTable - name table base SQL;
     * @param nameRow - name row table;
     * @param amountValues - amount values for addition;
     * @return script type String;
     */
    private String getSQLQueryAdd(String nameTable, String nameRow, int amountValues) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("INSERT INTO %s(%s) VALUES", nameTable, nameRow));
        for (int value = 1; value <= amountValues; value++) {
            sb.append("(").append(value).append(")");
            if (value < amountValues) {
                sb.append(",").append(System.lineSeparator());
            } else {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /**
     * The method return all values from table base SQL to collections.
     *
     * @param nameTable - name table base SQL;
     * @param nameRow - name row table;
     * @return list all values;
     */
    private LinkedList<Integer> toArrayTable(String nameTable, String nameRow) {
        LinkedList<Integer> list = new LinkedList<>();
        String sql = String.format("SELECT %s FROM %s;", nameRow, nameTable);
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt(nameRow));
            }
        } catch (SQLException e) {
            this.log.fatal(String.format("The table [%s] is not found.", nameTable));
        }
        return list;
    }

    /**
     * The method clear all values table to base SQL.
     *
     * @param nameTable - name table base SQL;
     */
    private void clearTable(String nameTable) {
        String sql = String.format("TRUNCATE TABLE %s;", nameTable);
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            this.log.fatal(String.format("Error clear table [%s].", nameTable));
        }
    }
}

package apavlov.database;

import apavlov.models.Comment;
import apavlov.models.Item;
import apavlov.util.NameConstant;
import apavlov.util.Settings;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The class BaseSQL for work with base SQL.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
public class BaseSQL {
    /**
     * The link object connect to base SQL.
     */
    private Connection connection;

    /**
     * The link object Logger (system logs) (log4j 2).
     */
    private final Logger log;

    /**
     * The link object load settings for connect from file properties.
     */
    private final Settings settings;

    /**
     * The link object for concat string.
     */
    private final StringBuilder sb;

    /**
     * The constructor for class BaseSQL.
     *
     * @param log - system logs;
     */
    public BaseSQL(Logger log) {
        this.log = log;
        this.settings = new Settings(NameConstant.FILE_PROPERTIES.toString(), log);
        this.sb = new StringBuilder();
    }

    /**
     * The method check active connect to base SQL.
     *
     * @return true, if connect is active or false;
     */
    public boolean isConnect() {
        return this.connection != null;
    }

    /**
     * The method connect to base SQL.
     *
     * @return true, if connect is ready or false;
     */
    public boolean connect() {
        boolean result;
        if (result = this.connection == null) {
            this.log.info("Connect to base. Wait...");
            if (result = this.checkDriverJDBC()) {
                try {
                    this.settings.load();
                    this.connection = DriverManager.getConnection(settings.getURL(), settings.getLogin(), settings.getPassword());
                    if (result = this.createDataBase()) {
                        if (result = createTablesItem() && createTablesComment()) {
                            this.log.info("Connect to base SQL is successfully.");
                        }
                    }
                } catch (SQLException e) {
                    this.log.fatal(String.format("Connection to base SQL is fatal [%s].", this.settings), e);
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * The method for disconnect to base SQL.
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                this.log.fatal("Disconnect base SQL is fatal.", e);
            }
        }
    }

    /**
     * The method add new item to base SQL.
     *
     * @param item - item for adding;
     * @return true, if item is add or false;
     */
    boolean addItemToSQL(Item item) {
        boolean result;
        if (result = item != null) {
            this.sb.delete(0, this.sb.length());
            this.sb.append("INSERT INTO ").append(NameConstant.TABLE_ITEM).append("(");
            this.sb.append(NameConstant.ITEM_ROW_PK).append(", ").append(NameConstant.ITEM_ROW_NAME).append(", ");
            this.sb.append(NameConstant.ITEM_ROW_DESC).append(", ").append(NameConstant.ITEM_ROW_DATE);
            this.sb.append(")").append(System.lineSeparator());
            this.sb.append("VALUES(?, ?, ?, ?);");
            try (PreparedStatement statement = this.connection.prepareStatement(this.sb.toString())) {
                statement.setString(1, item.getId());
                statement.setString(2, item.getName());
                statement.setString(3, item.getDescription());
                statement.setTimestamp(4, item.getDate());
                statement.executeUpdate();
            } catch (SQLException e) {
                this.log.error("Add new item is error.", e);
                result = false;
            }
        }
        return result;
    }

    /**
     * The method add new comment to base SQL.
     *
     * @param comment - comment for adding;
     * @param idItem  - id item;
     * @return true, if comment is add or false;
     */
    boolean addCommentToItemSQL(Comment comment, String idItem) {
        boolean result;
        if (result = comment != null && idItem != null) {
            this.sb.delete(0, this.sb.length());
            this.sb.append("INSERT INTO ").append(NameConstant.TABLE_COMMENTS).append("(");
            this.sb.append(NameConstant.COMMENT_ROW_DATE).append(", ").append(NameConstant.COMMENT_ROW_NAME).append(", ");
            this.sb.append(NameConstant.COMMENT_ROW_FK);
            this.sb.append(")").append(System.lineSeparator());
            this.sb.append("VALUES(?, ?, ?);");
            try (PreparedStatement statement = this.connection.prepareStatement(this.sb.toString())) {
                statement.setTimestamp(1, comment.getDate());
                statement.setString(2, comment.getComment());
                statement.setString(3, idItem);
                statement.executeUpdate();
            } catch (SQLException e) {
                this.log.error("Add new comment is error.", e);
                result = false;
            }
        }
        return result;
    }

    /**
     * The method parse iterator ResultSet to object Item.
     *
     * @param rs - ResultSet;
     * @return - link object Item;
     * @throws SQLException - error work to SQL base;
     */
    private Item parseResultSetToItem(ResultSet rs) throws SQLException {
        Item item = null;
        if (rs != null) {
            String id = rs.getString(NameConstant.ITEM_ROW_PK.toString());
            String name = rs.getString(NameConstant.ITEM_ROW_NAME.toString());
            String desc = rs.getString(NameConstant.ITEM_ROW_DESC.toString());
            Timestamp date = rs.getTimestamp(NameConstant.ITEM_ROW_DATE.toString());
            item = new Item(id, name, desc, date);
        }
        return item;
    }

    /**
     * The method parse iterator ResultSet to object Comment.
     *
     * @param rs - ResultSet;
     * @return - link object Comment;
     * @throws SQLException - error work to SQL base;
     */
    private Comment parseResultSetToComment(ResultSet rs) throws SQLException {
        Comment comment = null;
        if (rs != null) {
            Timestamp date = rs.getTimestamp(NameConstant.COMMENT_ROW_DATE.toString());
            String text = rs.getString(NameConstant.COMMENT_ROW_NAME.toString());
            comment = new Comment(date, text);
        }
        return comment;
    }

    /**
     * The method find item to table SQL by values.
     *
     * @param text    - text for searching;
     * @param nameRow - name row table "item";
     * @param sortRow - variant sorted;
     * @return list finding items;
     */
    List<Item> findBy(String text, String nameRow, String sortRow) {
        List<Item> resultList = new ArrayList<>();
        if (text != null && text.length() > 0 && nameRow != null) {
            text = text.toLowerCase();
            this.sb.delete(0, this.sb.length());
            this.sb.append("SELECT ");
            this.sb.append(NameConstant.ITEM_ROW_PK).append(", ").append(NameConstant.ITEM_ROW_NAME).append(", ");
            this.sb.append(NameConstant.ITEM_ROW_DESC).append(", ").append(NameConstant.ITEM_ROW_DATE);
            this.sb.append(" FROM ").append(NameConstant.TABLE_ITEM).append(" WHERE ");
            this.sb.append("LOWER(").append(nameRow).append(") LIKE '%").append(text).append("%'");
            this.sb.append(" ORDER BY ").append(sortRow).append(";");
            try (PreparedStatement statement = this.connection.prepareStatement(sb.toString());
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    resultList.add(this.parseResultSetToItem(rs));
                }
            } catch (SQLException e) {
                this.log.fatal("Error search values to base SQL.", e);
            }
        }
        return resultList;
    }

    /**
     * The method return all items to list from base SQL.
     *
     * @param rowSort - method sort;
     * @return list all items from base SQL;
     */
    List<Item> getFullItems(String rowSort) {
        List<Item> resultList = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s ORDER BY %s;", NameConstant.TABLE_ITEM.toString(), rowSort);
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                resultList.add(this.parseResultSetToItem(rs));
            }
        } catch (SQLException e) {
            this.log.fatal("Error search values to base SQL.", e);
        }
        return resultList;
    }

    /**
     * The method return all comments to list by id item from base SQL.
     *
     * @param id - id item;
     * @return list all comments from base SQL;
     */
    List<Comment> getFullComments(String id) {
        List<Comment> resultList = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s WHERE %s = ? ORDER BY %s;",
                NameConstant.TABLE_COMMENTS.toString(), NameConstant.COMMENT_ROW_FK.toString(), NameConstant.COMMENT_ROW_DATE.toString());
        ResultSet rs = null;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                resultList.add(this.parseResultSetToComment(rs));
            }
        } catch (SQLException e) {
            this.log.fatal("Error search values to base SQL.", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    this.log.fatal("Error closing ResultSet.", e);
                }
            }
        }
        return resultList;
    }

    /**
     * The method delete line from table base SQL.
     *
     * @param nameTable - name table base SQL;
     * @param nameRow   - name row table;
     * @param value     - value for comparing;
     * @return true, if table is found or false;
     */
    boolean removeLineToSQL(String nameTable, String nameRow, String value) {
        boolean result = true;
        String sql = String.format("DELETE FROM %s WHERE %s = ?", nameTable, nameRow);
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            this.log.fatal("Error remove values to base SQL.", e);
            result = false;
        }
        return result;
    }

    /**
     * The method edit (update) item to base SQL.
     *
     * @param nameTable - name table base SQL;
     * @param item      - item for update;
     * @return true, if item is found and update or false;
     */
    boolean editItemToSQL(String nameTable, Item item) {
        boolean result = item != null;
        if (result) {
            String sql = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?;",
                    nameTable, NameConstant.ITEM_ROW_NAME.toString(), NameConstant.ITEM_ROW_DESC, NameConstant.ITEM_ROW_DATE, NameConstant.ITEM_ROW_PK);
            try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setTimestamp(3, item.getDate());
                statement.setString(4, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                this.log.fatal("Error edit values to base SQL.", e);
                result = false;
            }
        }
        return result;
    }

    /**
     * The method create database tracker, if base not exists.
     *
     * @return true, if table is create or false;
     */
    private boolean createDataBase() {
        boolean result = true;
        String base = this.settings.getDataBase();
        try (PreparedStatement statement = this.connection.prepareStatement(String.format("CREATE DATABASE %s;", base))) {
            statement.execute();
            this.log.info(String.format("DataBase [%s] is creating.", base));
        } catch (SQLException e) {
            this.log.info(String.format("DataBase [%s] is found.", base));
        }
        try {
            this.disconnect();
            this.connection =
                    DriverManager.getConnection(String.format("%s/%s", this.settings.getURL(), base), this.settings.getLogin(), this.settings.getPassword());
        } catch (SQLException e) {
            this.log.fatal(String.format("Error database [%s].", base), e);
            result = false;
        }
        return result;
    }

    /**
     * The method create table items to base SQL, if table not exists.
     *
     * @return true, if table is create or false;
     */
    private boolean createTablesItem() {
        boolean result = true;
        this.sb.delete(0, this.sb.length());
        this.sb.append("CREATE TABLE IF NOT EXISTS ");
        this.sb.append(NameConstant.TABLE_ITEM).append("(").append(System.lineSeparator());
        this.sb.append(NameConstant.ITEM_ROW_PK).append(" VARCHAR(10) NOT NULL PRIMARY KEY UNIQUE,").append(System.lineSeparator());
        this.sb.append(NameConstant.ITEM_ROW_NAME).append(" VARCHAR(100) NOT NULL,").append(System.lineSeparator());
        this.sb.append(NameConstant.ITEM_ROW_DESC).append(" VARCHAR(1000) NOT NULL,").append(System.lineSeparator());
        this.sb.append(NameConstant.ITEM_ROW_DATE).append(" TIMESTAMP DEFAULT now() NOT NULL").append(System.lineSeparator());
        this.sb.append(");");
        try (PreparedStatement statement = this.connection.prepareStatement(sb.toString())) {
            statement.execute();
        } catch (SQLException e) {
            this.log.fatal(String.format("Create table [%s] is failed.", NameConstant.TABLE_ITEM), e);
            result = false;
        }
        return result;
    }

    /**
     * The method create table comments to base SQL, if table not exists.
     *
     * @return true, if table is create or false;
     */
    private boolean createTablesComment() {
        boolean result = true;
        this.sb.delete(0, this.sb.length());
        this.sb.append("CREATE TABLE IF NOT EXISTS ");
        this.sb.append(NameConstant.TABLE_COMMENTS).append("(").append(System.lineSeparator());
        this.sb.append(NameConstant.COMMENT_ROW_PK).append(" SERIAL PRIMARY KEY,").append(System.lineSeparator());
        this.sb.append(NameConstant.COMMENT_ROW_NAME).append(" VARCHAR(3000) NOT NULL,").append(System.lineSeparator());
        this.sb.append(NameConstant.COMMENT_ROW_DATE).append(" TIMESTAMP DEFAULT now() NOT NULL,").append(System.lineSeparator());
        this.sb.append(NameConstant.COMMENT_ROW_FK).append(" VARCHAR(10) REFERENCES ").append(NameConstant.TABLE_ITEM);
        this.sb.append("(").append(NameConstant.COMMENT_ROW_FK).append(")").append(" NOT NULL");
        this.sb.append(");");
        try (PreparedStatement statement = this.connection.prepareStatement(sb.toString())) {
            statement.execute();
        } catch (SQLException e) {
            this.log.fatal(String.format("Create table [%s] is failed.", NameConstant.TABLE_COMMENTS), e);
            result = false;
        }
        return result;
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
            this.log.info("PostgreSQL JDBC Driver successfully connected.");
            result = true;
        } catch (ClassNotFoundException e) {
            this.log.fatal("PostgreSQL JDBC Driver is not found.", e);
        }
        return result;
    }
}

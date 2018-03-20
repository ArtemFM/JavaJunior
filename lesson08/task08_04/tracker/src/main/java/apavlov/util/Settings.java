package apavlov.util;

import apavlov.error.AttributeNotFoundException;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The class Settings - methods for job with file settings.
 *
 * @author Pavlov Artem
 * @since 19.03.2018
 */
public class Settings {
    /**
     * Name file properties (settings).
     */
    private String nameFileProperties;

    /**
     * The link to class Logger (for log system).
     */
    private final Logger log;

    /**
     * The constructor for class Settings.
     *
     * @param nameFileProperties - name file settings;
     * @param log - logger for write to log system;
     */
    public Settings(String nameFileProperties, Logger log) {
        this.nameFileProperties = nameFileProperties;
        this.log = log;
    }

    /**
     * The method for load file properties.
     *
     * @return true, if file is found or false;
     */
    public boolean load() {
        boolean result;
        InputStream in = ClassLoader.getSystemResourceAsStream(this.nameFileProperties);
        if (result = in != null) {
            Properties properties = new Properties();
            try {
                properties.load(in);
                List<String> listNotFoundAttr = this.setAttributes(properties);
                if (!(result = listNotFoundAttr.isEmpty())) {
                    listNotFoundAttr.forEach(a -> this.log.fatal(String.format("Attribute <%s> is not found", a), new AttributeNotFoundException("attribute not found")));
                }
                this.log.info(String.format("The file settings [%s] is successfully loaded.", this.nameFileProperties));
            } catch (IOException e) {
                this.log.fatal(String.format("Error of reading file <%s>.", this.nameFileProperties), e);
                result = false;
            }
        } else {
            this.log.fatal(String.format("File properties [name = %s] is not found.", this.nameFileProperties), new FileNotFoundException(this.nameFileProperties));
        }
        return result;
    }

    /**
     * The private method for set attributes to enum Attributes.
     *
     * @param properties - link to class job to file properties;
     * @return list not found attributes;
     */
    private List<String> setAttributes(Properties properties) {
        List<String> listNotFoundAttr = new ArrayList<>();
        String attribute;
        for (Attributes attr : Attributes.values()) {
            attribute = properties.getProperty(attr.toString());
            if (attribute == null) {
                listNotFoundAttr.add(attr.toString());
            } else {
                attr.setAttribute(attribute);
            }
        }
        return listNotFoundAttr;
    }

    /**
     * The private method for delete excess symbols to attribute.
     *
     * @param attribute - attribute for format;
     * @return format`s string attribute;
     */
    private String formatAttribute(Attributes attribute) {
        return attribute.getAttribute().trim().replaceAll("[/, ;]*", "");
    }

    /**
     * The method return url for connect to base SQL.
     *
     * @return string URL;
     */
    public String getURL() {
        return String.format("jdbc:%s://%s:%s",
                formatAttribute(Attributes.TYPE), formatAttribute(Attributes.IP), formatAttribute(Attributes.PORT));
    }

    /**
     * The method return login for connect to base SQL.
     *
     * @return string login;
     */
    public String getLogin() {
        return formatAttribute(Attributes.LOGIN);
    }

    /**
     * The method return password for connect to base SQL.
     *
     * @return string password;
     */
    public String getPassword() {
        return formatAttribute(Attributes.PASSWORD);
    }

    /**
     * The method return database for connect to base SQL.
     *
     * @return string database;
     */
    public String getDataBase() {
        return formatAttribute(Attributes.DATABASE);
    }

    @Override
    public String toString() {
        return String.format("URL: %s/%s; login: %s; password: %s;", this.getURL(), this.getDataBase(), this.getLogin(), this.getPassword());
    }
}

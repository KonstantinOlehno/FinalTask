package com.university.selectioncommittee.util;

import com.university.selectioncommittee.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String DB_PROPERTIES = "database/db.properties";
    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_DRIVER = "db.driver";
    private static String DB_URL;
    private static String DB_PASSWORD;
    private static String DB_USER;
    private static String DB_DRIVER;

    static {
        /*ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_PROPERTIES,Locale.ENGLISH);
        DB_URL = resourceBundle.getString(PROPERTY_URL);
        DB_DRIVER = resourceBundle.getString(PROPERTY_DRIVER);
        DB_USER = resourceBundle.getString(PROPERTY_USER);
        DB_PASSWORD = resourceBundle.getString(PROPERTY_PASSWORD);
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL,"Driver is not found");
            throw new RuntimeException(e + "Driver is not found");
        }*/

        try {
            properties.load(ConnectionFactory.class.getClassLoader()
                    .getResourceAsStream(DB_PROPERTIES));
            DB_URL = properties.getProperty(PROPERTY_URL);
            DB_USER = properties.getProperty(PROPERTY_USER);
            DB_DRIVER = properties.getProperty(PROPERTY_DRIVER);
            DB_PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            Class.forName(DB_DRIVER);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.ERROR, "Can`t connect to database ");
        }


    }


    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

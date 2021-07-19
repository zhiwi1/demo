package com.epam.webproject.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public final class ConnectionCreator {

    private final static Logger logger = LogManager.getLogger();

    private final static String RELATIVE_PATH_TO_PROPERTIES = "database";

    private final static String URL = "url";

    private final static String USERNAME = "username";

    private final static String PASSWORD = "password";

    private final static String DRIVER = "driver";

    private final static String DATABASE_URL;

    private final static String DATABASE_USERNAME;

    private final static String DATABASE_PASSWORD;

    private final static ResourceBundle properties;


    static {
        properties= ResourceBundle.getBundle(RELATIVE_PATH_TO_PROPERTIES);

        try {
            DATABASE_URL = properties.getString(URL);
            logger.info(DATABASE_URL);
            String driver = properties.getString(DRIVER);
            logger.info(driver);
            DATABASE_USERNAME = properties.getString(USERNAME);
            DATABASE_PASSWORD = properties.getString(PASSWORD);
            Class.forName(driver);
        } catch (ClassNotFoundException | MissingResourceException e) {
            logger.fatal("fatal error: config file " + e);
            throw new RuntimeException(e);
        }
    }

    private ConnectionCreator() {
    }


    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
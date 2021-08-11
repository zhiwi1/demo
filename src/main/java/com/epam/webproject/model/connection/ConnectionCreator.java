package com.epam.webproject.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

 class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();

    private static final String RELATIVE_PATH_TO_PROPERTIES = "database";

    private static final String URL = "url";

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    private static final String DRIVER = "driver";

    private static final String DATABASE_URL;

    private static final String DATABASE_USERNAME;

    private static final String DATABASE_PASSWORD;

    private static final ResourceBundle properties;


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
            logger.fatal("Fatal error: config file " + e);
            throw new RuntimeException(e);
        }
    }

    private ConnectionCreator() {
    }


  static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
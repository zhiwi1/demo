package com.epam.webproject.model.connection;

import com.epam.webproject.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public enum ConnectionPool {
    INSTANCE;
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 8;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> usedConnections;


    ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        initializePool();

    }

    public Connection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnections.take();
            usedConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("The connection is not received " + e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }


    public void releaseConnection(Connection connection) throws ConnectionException {
        if (connection instanceof ProxyConnection) {
            usedConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("The connection is not received " + e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.error("Connection is not proxy or null!");
        }
    }


    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.error("The pool was not destroyed " + e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }


    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("error deregisterDriver with driver: " + driver, e);
            }
        }
    }

    private void initializePool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.fatal(e);
                throw new RuntimeException(e);
            }
        }
    }


}

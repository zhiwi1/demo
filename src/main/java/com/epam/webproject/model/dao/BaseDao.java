package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends Entity, K extends Number> {
    Logger logger = LogManager.getLogger();

    List<T> findAllEntities() throws DaoException;

    Optional<T> findEntityById(K id) throws DaoException;

    boolean add(T t) throws DaoException;

    boolean deleteEntityById(K id) throws DaoException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Close exception", e);
        }
    }


    default void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("Rollback error: " + e.getMessage());
        }
    }
}

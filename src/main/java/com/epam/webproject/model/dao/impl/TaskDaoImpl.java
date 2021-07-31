package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class TaskDaoImpl implements TaskDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String ADD_TASK = "INSERT INTO `tasks` (`title`, `content`, `created_at`,  `user_id`, `complexity`) VALUES (?, ?, ?, (SELECT users.id FROM users WHERE login=?), ?)";
    private static final String FIND_ALL = "SELECT title, content, created_at, updated_at, user_id, complexity, count_for_solve FROM tasks";
    private static final String FIND_TASK_BY_TITLE = "SELECT  title, content, created_at, updated_at, user_id,complexity,count_for_solve FROM tasks WHERE title = ? ";
    private static final String TASKS_FULL_TEXT_SEARCH = "SELECT title FROM `tasks` WHERE MATCH (title,content) AGAINST (?);";
    private static final String DELETE_TASK = "DELETE FROM tasks " +
            "WHERE title =?"+
            "JOIN";
    private static final String FIND_TASKS_BY_USER_LOGIN = "SELECT  title, content, created_at, updated_at, user_id,complexity,count_for_solve FROM tasks WHERE user_id = (SELECT id FROM users WHERE login = ? ) ";

    @Override
    public boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TASK);) {
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setTimestamp(3, new Timestamp(createdAt.getTime()));//date
            statement.setString(4, login);//user_id
            statement.setInt(5, complexity);

            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new User. ", e);
        }
    }


    @Override
    public List<Task> findAll() throws DaoException {
        List<Task> tasks = new LinkedList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                String content = resultSet.getString(CONTENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                java.util.Date updated_at = resultSet.getTimestamp(UPDATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(title, content, created_at, updated_at, user_id, complexity, count_for_solve);
                tasks.add(task);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        //   return users;
        return tasks;
    }

    @Override
    public Optional<Task> findTaskByTitle(String title) throws DaoException {
        Optional<Task> taskOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TASK_BY_TITLE)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String resultTitle = resultSet.getString(TASK_TITLE);
                String content = resultSet.getString(CONTENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                java.util.Date updated_at = resultSet.getTimestamp(UPDATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(title, content, created_at, updated_at, user_id, complexity, count_for_solve);
                taskOptional = Optional.of(task);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return taskOptional;
    }

    @Override
    public ArrayDeque<String> findByFullText(String text) throws DaoException {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TASKS_FULL_TEXT_SEARCH)) {
            preparedStatement.setString(1, text);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                arrayDeque.add(title);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return arrayDeque;
    }

    @Override
    public ArrayDeque<Task> findTasksByUserLogin(String login) throws DaoException {
        ArrayDeque<Task> tasks = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TASKS_BY_USER_LOGIN);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                String content = resultSet.getString(CONTENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                java.util.Date updated_at = resultSet.getTimestamp(UPDATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(title, content, created_at, updated_at, user_id, complexity, count_for_solve);
                tasks.add(task);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        //   return users;
        return tasks;
    }



    @Override
    public boolean deleteTask(String title) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK)) {
            preparedStatement.setString(1, title);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.error("Can't delete", e);
            throw new DaoException(e);
        }
    }

}



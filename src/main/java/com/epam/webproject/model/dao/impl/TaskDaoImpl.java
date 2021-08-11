package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class TaskDaoImpl implements TaskDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String ADD_TASK = "INSERT INTO tasks (title, content, created_at,  user_id, complexity) VALUES (?, ?, ?, (SELECT users.id FROM users WHERE login=?), ?)";

    private static final String FIND_ALL = "SELECT title, content, created_at, user_id, complexity, count_for_solve FROM tasks";

    private static final String FIND_TASK_BY_TITLE = "SELECT  title, content, created_at, user_id,complexity,count_for_solve FROM tasks WHERE title = ? ";

    private static final String TASKS_FULL_TEXT_SEARCH = "SELECT title FROM tasks WHERE MATCH (title,content) AGAINST (?);";

    private static final String DELETE_TASK = "DELETE FROM tasks WHERE title =?";

    private static final String FIND_TASKS_BY_USER_LOGIN = "SELECT  title, content, created_at,user_id,complexity,count_for_solve FROM tasks WHERE user_id = (SELECT id FROM users WHERE login = ? ) LIMIT ?, ?";

    private static final String FIND_TITLE_BY_ID = "SELECT title FROM tasks WHERE id=?";

    private static final String COUNT_OF_TASKS = "SELECT COUNT(`id`) as `count` FROM tasks";

    private static final String FIND_ALL_TASKS_WITH_LIMIT = " SELECT title, content, created_at, user_id, complexity, count_for_solve  FROM tasks" +
            "  LIMIT ?, ?";

    private static final String COUNT_BY_TITLE = "  SELECT COUNT(`title`) as `count` FROM tasks WHERE tasks.title = ?";
    @Override
    public Optional<String> findTitleById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_TITLE_BY_ID)) {
            Optional<String> result = Optional.empty();
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = Optional.ofNullable(resultSet.getString(TASK_TITLE));
            }
            return result;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_TITLE_BY_ID, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_TITLE_BY_ID, e);
        }


    }

    @Override
    public boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TASK);) {
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setTimestamp(3, new Timestamp(createdAt.getTime()));
            statement.setString(4, login);
            statement.setInt(5, complexity);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", ADD_TASK, e.getMessage());
            throw new DaoException("Can not proceed request: " + ADD_TASK, e);
        }
    }


    @Override
    public Deque<Task> findAll() throws DaoException {
        Deque<Task> tasks = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                String content = resultSet.getString(CONTENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(title, content, created_at, user_id, complexity, count_for_solve);
                tasks.add(task);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        return tasks;
    }

    @Override
    public Deque<Task> findAll(int offset, int limit) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TASKS_WITH_LIMIT)) {
            Deque<Task> tasks = new ArrayDeque<>();
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                String content = resultSet.getString(CONTENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(title, content, created_at, user_id, complexity, count_for_solve);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL_TASKS_WITH_LIMIT, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL_TASKS_WITH_LIMIT, e);
        }
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
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(resultTitle, content, created_at, user_id, complexity, count_for_solve);
                taskOptional = Optional.of(task);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_TASK_BY_TITLE, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_TASK_BY_TITLE, e);
        }
        return taskOptional;
    }

    @Override
    public Deque<String> findByFullText(String text) throws DaoException {
        Deque<String> arrayDeque = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TASKS_FULL_TEXT_SEARCH)) {
            preparedStatement.setString(1, text);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                arrayDeque.add(title);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", TASKS_FULL_TEXT_SEARCH, e.getMessage());
            throw new DaoException("Can not proceed request: " + TASKS_FULL_TEXT_SEARCH, e);
        }
        return arrayDeque;
    }

    @Override
    public Deque<Task> findTasksByUserLogin(String login, int offset, int limit) throws DaoException {
        Deque<Task> tasks = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TASKS_BY_USER_LOGIN)) {
            statement.setString(1, login);
            statement.setInt(2, offset);
            statement.setInt(3, limit);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(TASK_TITLE);
                String content = resultSet.getString(CONTENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                int complexity = resultSet.getInt(TASK_COMPLEXITY);
                int count_for_solve = resultSet.getInt(TASK_COUNT_FOR_SOLVE);
                Task task = new Task(title, content, created_at, user_id, complexity, count_for_solve);
                tasks.add(task);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_TASKS_BY_USER_LOGIN, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_TASKS_BY_USER_LOGIN, e);
        }
        return tasks;
    }


    @Override
    public boolean deleteTask(String title) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK)) {
            preparedStatement.setString(1, title);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", DELETE_TASK, e.getMessage());
            throw new DaoException("Can not proceed request: " + DELETE_TASK, e);
        }
    }

    @Override
    public int countOfTasks() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(COUNT_OF_TASKS)) {
            int result = 0;
            while (resultSet.next()) {
                result = resultSet.getInt(COUNT);
            }
            return result;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", COUNT_OF_TASKS, e.getMessage());
            throw new DaoException("Can not proceed request: " + COUNT_OF_TASKS, e);
        }
    }

    @Override
    public boolean existRowsByTitle(String title) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            boolean result = false;
            while (resultSet.next()) {
                result = (resultSet.getInt(COUNT) >= 1);
            }
            return result;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", COUNT_BY_TITLE, e.getMessage());
            throw new DaoException("Can not proceed request: " + COUNT_BY_TITLE, e);
        }
    }

}



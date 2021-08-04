package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.AnswerDao;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Comment;
import com.epam.webproject.model.entity.CorrectnessOfAnswer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class AnswerDaoImpl implements AnswerDao {
    public static final Logger logger = LogManager.getLogger();
    private static final String ADD_ANSWER = "INSERT INTO `answers` (`content`,`task_id` , `user_id`) VALUES (?, (SELECT tasks.id FROM tasks WHERE title=?), (SELECT users.id FROM users WHERE login=?))";
    private static final String FIND_ALL = "SELECT content,  user_id, task_id,likes FROM comments";
    private static final String FIND_ANSWERS_BY_TASK_TITLE = "SELECT answers.id, content, login,likes,correctness,task_id FROM answers " +
            " JOIN `users` ON `users`.`id` = `answers`.`user_id`" +
            "WHERE answers.task_id = (SELECT `id` FROM `tasks` WHERE `title` = ?) ";
    private static final String PLUS_LIKE = "UPDATE answers" +
            "SET likes = likes+1," +
            "WHERE id = ?";
    private static final String MINUS_LIKE = "UPDATE answers" +
            "SET likes = likes-1," +
            "WHERE id = ?";
    private static final String MARK_CORRECT = "UPDATE answers " +
            " JOIN `users` ON `users`.`id` = answers.user_id  " +
            " JOIN `tasks` ON `tasks`.`id` = answers.task_id  " +
            " SET correctness = 'CORRECT', count_of_solve = count_of_solve + 1, count_for_solve = count_for_solve+1 " +
            " WHERE answers.id = ?";
    private static final String MARK_INCORRECT = "UPDATE answers " +
            " JOIN `users` ON `users`.`id` = answers.user_id  " +
            " JOIN `tasks` ON `tasks`.`id` = answers.task_id  " +
            " SET correctness  = 'INCORRECT', count_of_solve = count_of_solve + 1, count_for_solve = count_for_solve+1 " +
            " WHERE answers.id = ?";



    @Override
    public boolean markCorrect(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(MARK_CORRECT)) {
            statement.setLong(1, id);
            statement.execute() ;
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error with " + MARK_CORRECT, e);
        }
    }
    @Override
    public boolean markIncorrect(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(MARK_INCORRECT)) {
            statement.setLong(1, id);
            statement.execute() ;
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error with " + MARK_CORRECT, e);
        }
    }
    public boolean createNewAnswer(String content, String title, String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ANSWER);) {
            statement.setString(1, content);
            statement.setString(2, title);
            statement.setString(3, login);

            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new Comment. ", e);
        }
    }


    public Deque<Answer> findAll() throws DaoException {
        Deque<Answer> answers = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                String answerContent = resultSet.getString(CONTENT);
                long user_id = resultSet.getLong(USER_ID);
                long task_id = resultSet.getLong(TASK_ID);
                long likes = resultSet.getLong(LIKES);
                Answer answer = new Answer(answerContent, likes, task_id, user_id);
                answers.add(answer);

            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        return answers;
    }

    @Override
    public Deque<Answer> findAnswersByTitle(String title) throws DaoException {
        Deque<Answer> arrayDeque = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ANSWERS_BY_TASK_TITLE)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long answerId = resultSet.getLong(ID);
                String commentContent = resultSet.getString(CONTENT);
                String login = resultSet.getString(USER_LOGIN);
                long likes = resultSet.getLong(LIKES);
                CorrectnessOfAnswer correctness=CorrectnessOfAnswer.valueOf(resultSet.getString(CORRECTNESS));
                long taskId=resultSet.getLong(TASK_ID);
                Answer answer = new Answer(answerId, commentContent, likes, login,correctness,taskId);
                arrayDeque.add(answer);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return arrayDeque;
    }

    @Override
    public boolean increaseLike(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PLUS_LIKE)) {
            preparedStatement.setLong(1, id);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.error("Can't like", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean decreaseLike(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PLUS_LIKE)) {
            preparedStatement.setLong(1, id);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.error("Can't like", e);
            throw new DaoException(e);
        }
    }
}

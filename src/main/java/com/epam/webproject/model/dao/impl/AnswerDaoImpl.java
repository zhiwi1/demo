package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.AnswerDao;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.CorrectnessOfAnswer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Deque;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class AnswerDaoImpl implements AnswerDao {
    public static final Logger logger = LogManager.getLogger();
    private static final String ADD_ANSWER = "INSERT INTO `answers` (`content`,`task_id` , `user_id`) VALUES (?, (SELECT tasks.id FROM tasks WHERE title=?), (SELECT users.id FROM users WHERE login=?))";
    private static final String FIND_ALL_WITH_LIMIT = "SELECT content,  user_id, task_id,likes FROM comments  LIMIT ?, ?";
    private static final String FIND_ANSWERS_BY_TASK_TITLE = "SELECT answers.id, content, login,likes,correctness,task_id FROM answers " +
            " JOIN `users` ON `users`.`id` = `answers`.`user_id`" +
            "WHERE answers.task_id = (SELECT `id` FROM `tasks` WHERE `title` = ?)  LIMIT ?, ?";
    //Transaction
    private static final String MARK_CORRECT = "UPDATE answers " +
            " JOIN `users` ON `users`.`id` = answers.user_id  " +
            " JOIN `tasks` ON `tasks`.`id` = answers.task_id  " +
            " SET correctness = 'CORRECT', count_of_solve = count_of_solve + 1, count_for_solve = count_for_solve+1 " +
            " WHERE answers.id = ?";
    private static final String MARK_INCORRECT = "UPDATE answers " +
            " JOIN `users` ON `users`.`id` = answers.user_id  " +
            " JOIN `tasks` ON `tasks`.`id` = answers.task_id  " +
            " SET correctness  = 'INCORRECT', count_of_solve = count_of_solve - 1, count_for_solve = count_for_solve-1 " +
            " WHERE answers.id = ?";

    private static final String UPDATE_CORRECTNESS = "update answers SET correctness = 'CORRECT' where id=?";
    private static final String UPDATE_COUNT_OF_SOLVE_PLUS = "update answers " +
            "JOIN `users` ON `users`.`id` = answers.user_id " +
            " SET  count_of_solve = count_of_solve + 1 where answers.id=?";
    private static final String UPDATE_COUNT_FOR_SOLVE_PLUS = "update answers " +
            "JOIN `tasks` ON `tasks`.`id` = answers.task_id" +
            " SET  count_for_solve = count_for_solve + 1 where answers.id=?";

    private static final String UPDATE_INCORRECTNESS = "update answers SET correctness = 'INCORRECT' where id=?";
    private static final String UPDATE_COUNT_OF_SOLVE_MINUS = "update answers " +
            "JOIN `users` ON `users`.`id` = answers.user_id " +
            " SET  count_of_solve = count_of_solve - 1 where answers.id=?";
    private static final String UPDATE_COUNT_FOR_SOLVE_MINUS = "update answers " +
            "JOIN `tasks` ON `tasks`.`id` = answers.task_id" +
            " SET  count_for_solve = count_for_solve + 1 where answers.id=?";

    private static final String COUNT_OF_ANSWERS_OF_TASK = "SELECT COUNT(`id`) as `count` FROM `answers` WHERE `task_id` =(SELECT `id` FROM `tasks` WHERE `title` = ?)";

    @Override
    public boolean markCorrectTransaction(long id) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        boolean result = false;
        boolean resultOf = false;
        boolean resultFor = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statementForUpdateCorrectness = null;
            try {
                statementForUpdateCorrectness = connection.prepareStatement(UPDATE_CORRECTNESS);
                statementForUpdateCorrectness.setLong(1, id);
                result = statementForUpdateCorrectness.executeUpdate() == 1;
            } finally {
                if (statementForUpdateCorrectness != null) {
                    statementForUpdateCorrectness.close();
                }
            }
            PreparedStatement statementForUpdateCountForSolve = null;
            try {
                statementForUpdateCountForSolve = connection.prepareStatement(UPDATE_COUNT_FOR_SOLVE_PLUS);
                statementForUpdateCountForSolve.setLong(1, id);

                resultFor = statementForUpdateCountForSolve.executeUpdate() == 1;
            } finally {
                if (statementForUpdateCountForSolve != null) {
                    statementForUpdateCountForSolve.close();
                }
            }
            PreparedStatement statementForUpdateCountOfSolve = null;
            try {
                statementForUpdateCountOfSolve = connection.prepareStatement(UPDATE_COUNT_OF_SOLVE_PLUS);
                statementForUpdateCountOfSolve.setLong(1, id);
                resultOf = statementForUpdateCountOfSolve.executeUpdate() == 1;
            } finally {
                if (statementForUpdateCountOfSolve != null) {
                    statementForUpdateCountOfSolve.close();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException(e);
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                    //logger
                }
            }
        }
        logger.debug(result + " " + resultFor + resultOf);
        return result && resultFor && resultOf;
    }


    @Override
    public boolean markIncorrectTransaction(long id) throws DaoException {
        Connection connection = ConnectionPool.INSTANCE.getConnection();
        boolean result = false;
        boolean resultOf = false;
        boolean resultFor = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statementForUpdateCorrectness = null;
            try {
                statementForUpdateCorrectness = connection.prepareStatement(UPDATE_INCORRECTNESS);
                statementForUpdateCorrectness.setLong(1, id);
                result = statementForUpdateCorrectness.executeUpdate() == 1;
            } finally {
                if (statementForUpdateCorrectness != null) {
                    statementForUpdateCorrectness.close();
                }
            }
            PreparedStatement statementForUpdateCountForSolve = null;
            try {
                statementForUpdateCountForSolve = connection.prepareStatement(UPDATE_COUNT_FOR_SOLVE_MINUS);
                statementForUpdateCountForSolve.setLong(1, id);

                resultFor = statementForUpdateCountForSolve.executeUpdate() == 1;
            } finally {
                if (statementForUpdateCountForSolve != null) {
                    statementForUpdateCountForSolve.close();
                }
            }
            PreparedStatement statementForUpdateCountOfSolve = null;
            try {
                statementForUpdateCountOfSolve = connection.prepareStatement(UPDATE_COUNT_OF_SOLVE_MINUS);
                statementForUpdateCountOfSolve.setLong(1, id);
                resultOf = statementForUpdateCountOfSolve.executeUpdate() == 1;
            } finally {
                if (statementForUpdateCountOfSolve != null) {
                    statementForUpdateCountOfSolve.close();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException(e);
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                    //logger
                }
            }
        }
        logger.debug(result + " " + resultFor + resultOf);
        return result && resultFor && resultOf;
    }

    @Override
    public boolean markCorrect(long id) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(MARK_CORRECT)) {
            statement.setLong(1, id);
            statement.execute();
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
            statement.execute();
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


    public Deque<Answer> findAllWithLimit(int offset, int limit) throws DaoException {
        Deque<Answer> answers = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_WITH_LIMIT);) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String answerContent = resultSet.getString(CONTENT);
                long user_id = resultSet.getLong(USER_ID);
                long task_id = resultSet.getLong(TASK_ID);
                long likes = resultSet.getLong(LIKES);
                Answer answer = new Answer(answerContent, likes, task_id, user_id);
                answers.add(answer);

            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL_WITH_LIMIT, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL_WITH_LIMIT, e);
        }
        return answers;
    }

    @Override
    public Deque<Answer> findAnswersByTitleWithLimit(String title, int offset, int limit) throws DaoException {
        Deque<Answer> arrayDeque = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ANSWERS_BY_TASK_TITLE)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long answerId = resultSet.getLong(ID);
                String commentContent = resultSet.getString(CONTENT);
                String login = resultSet.getString(USER_LOGIN);
                long likes = resultSet.getLong(LIKES);
                CorrectnessOfAnswer correctness = CorrectnessOfAnswer.valueOf(resultSet.getString(CORRECTNESS));
                long taskId = resultSet.getLong(TASK_ID);
                Answer answer = new Answer(answerId, commentContent, likes, login, correctness, taskId);
                arrayDeque.add(answer);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return arrayDeque;
    }


    @Override
    public int countOfAnswers(String titleOfTask) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_OF_ANSWERS_OF_TASK)) {
            statement.setString(1, titleOfTask);
            ResultSet resultSet = statement.executeQuery();
            int result = 0;
            while (resultSet.next()) {
                result = resultSet.getInt(COUNT);
            }
            return result;
        } catch (SQLException sqlException) {
            throw new DaoException("SQL request error. " + sqlException.getMessage(), sqlException);
        }
    }
}

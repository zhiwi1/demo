package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.AnswerDao;
import com.epam.webproject.model.entity.Answer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class AnswerDaoImpl implements AnswerDao {
    public static final Logger logger = LogManager.getLogger();
    private static final String ADD_ANSWER = "INSERT INTO `answers` (`content`,`task_id` , `user_id`) VALUES (?, (SELECT tasks.id FROM tasks WHERE title=?), (SELECT users.id FROM users WHERE login=?))";
    private static final String FIND_ALL = "SELECT content,  user_id, task_id,likes FROM comments";


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


    public List<Answer> findAll() throws DaoException {
        List<Answer> answers = new LinkedList<>();
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
        //   return users;
        return answers;
    }
}

package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.CommentDao;
import com.epam.webproject.model.entity.Comment;
import com.epam.webproject.model.entity.Task;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String ADD_СOMMENT = "INSERT INTO `comments` (`created_at`,`comment`,`task_id` , `user_id`) VALUES (?, ?,(SELECT tasks.id FROM tasks WHERE title=?), (SELECT users.id FROM users WHERE login=?))";
    private static final String FIND_ALL = "SELECT comment, created_at, updated_at, user_id, task_id FROM comments";
    private static final String FIND_COMMENTS_BY_TASK_TITLE = "SELECT comment, created_at,updated_at , login FROM comments " +
            " JOIN `users` ON `users`.`id` = `comments`.`user_id`" +
            "WHERE comments.task_id = (SELECT `id` FROM `tasks` WHERE `title` = ?) ";

    public boolean createNewComment(String text, java.util.Date createdAt, String login, String title) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_СOMMENT);) {
            statement.setTimestamp(1, new Timestamp(createdAt.getTime()));
            statement.setString(2, text);
            statement.setString(3, title);
            statement.setString(4, login);

            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new Comment. ", e);
        }
    }


    public Deque<Comment> findAll() throws DaoException {
        Deque<Comment> comments = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                String commentContent = resultSet.getString(COMMENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                java.util.Date updated_at = resultSet.getTimestamp(UPDATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                long task_id = resultSet.getLong(TASK_ID);

                Comment comment = new Comment(commentContent, created_at, updated_at, user_id, task_id);
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        //   return users;
        return comments;
    }

    @Override
    public Deque<Comment> findCommentsByTitle(String title) throws DaoException {
        Deque<Comment> arrayDeque = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENTS_BY_TASK_TITLE)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String commentContent = resultSet.getString(COMMENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                java.util.Date updated_at = resultSet.getTimestamp(UPDATED_AT);
                String login = resultSet.getString(USER_LOGIN);
                Comment comment = new Comment(commentContent, created_at, updated_at, login);
                arrayDeque.add(comment);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return arrayDeque;
    }

}





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
import java.util.LinkedList;
import java.util.List;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String ADD_СOMMENT = "INSERT INTO `comments` (`created_at`,`comment`,`task_id` , `user_id`) VALUES (?, ?,(SELECT tasks.id FROM tasks WHERE title=?), (SELECT users.id FROM users WHERE login=?))";
    private static final String FIND_ALL = "SELECT comment, created_at, updated_at, user_id, task_id FROM comments";


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


    public List<Comment> findAll() throws DaoException {
        List<Comment> comments = new LinkedList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                String commentContent = resultSet.getString(COMMENT);
                java.util.Date created_at = resultSet.getTimestamp(CREATED_AT);
                java.util.Date updated_at = resultSet.getTimestamp(UPDATED_AT);
                long user_id = resultSet.getLong(USER_ID);
                long task_id = resultSet.getLong(TASK_ID);

                Comment comment = new Comment( commentContent,created_at, updated_at, user_id,task_id);
                comments.add(comment);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        //   return users;
        return comments;
    }
}





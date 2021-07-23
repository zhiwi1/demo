package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.entity.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class TaskDaoImpl implements TaskDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String ADD_TASK = "INSERT INTO `tasks` (`title`, `content`, `created_at`,  `user_id`, `complexity`) VALUES (?, ?, ?, (SELECT users.id FROM users WHERE login=?), ?)";

    @Override
    public boolean createNewPost(Post post) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TASK);) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setDate(3, new Date(post.getTimeCreatedAt().getTime()));//date
            statement.setLong(4, post.getIdOfUser());//user_id
            statement.setInt(5, post.getComplexity());

            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new User. ", e);
        }
    }

    @Override
    public boolean createNewPost(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TASK);) {
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setDate(3, new Date(createdAt.getTime()));//date
            statement.setString(4, login);//user_id
            statement.setInt(5, complexity);

            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new User. ", e);
        }
    }

    @Override
    public List<Post> findAll() throws DaoException {
        return null;
    }

}



package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.PostDao;
import com.epam.webproject.model.entity.Post;
import com.epam.webproject.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static com.epam.webproject.model.dao.DatabaseColumnName.USER_ID;

public class PostDaoImpl implements PostDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String ADD_POST = "INSERT INTO `tasks` (`title`, `content`, `created_at`,  `user_id`, `complexity`, `count_for_solve`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_USER_ID_OF_POST = "SELECT id FROM users WHERE login = ?";


    @Override
    public boolean createNewPost(Post post) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_POST);) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setDate(3, new Date(post.getTimeCreatedAt().getTime()));//date
            statement.setLong(4, post.getIdOfUser());//user_id
            statement.setInt(5, post.getComplexity());
            statement.setInt(6, post.getCountForSolve());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new User. ", e);
        }
    }

    @Override
    public boolean createNewPost(String title, String text, java.util.Date createdAt, long id, int complexity, int countForSolve) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_POST);) {
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setDate(3, new Date(createdAt.getTime()));//date
            statement.setLong(4, id);//user_id
            statement.setInt(5, complexity);
            statement.setInt(6, countForSolve);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new User. ", e);
        }
    }

    @Override
    public Optional<Long> findUserIdOfPost(String login) throws DaoException {
        Optional<Long> result = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_POST);) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            result = Optional.of(resultSet.getLong(USER_ID));
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with search of id. ", e);
        }
        return result;
    }

    @Override
    public List<Post> findAll() throws DaoException {
        return null;
    }

}



package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.dao.UserFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT id, login, email, count_of_solve, rates_of_solve, `role`, `status` FROM users WHERE email = ? AND password = ?";

    private static final String FIND_LOGIN_DATA_BY_LOGIN = "SELECT password_hash,salt FROM users WHERE login = ?";

    private static final String FIND_LOGIN_DATA_BY_EMAIL = "SELECT password_hash, salt FROM users WHERE email = ?";

    private static final String ADD_USER = "INSERT INTO `users` (`id`, `login`, `email`, `count_of_solve`, `rates_of_solve`, `role`, `password_hash`,`salt`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

    private static final String FIND_ALL = "SELECT id, login, email, count_of_solve, rates_of_solve, `role`, `status` FROM users";
    //            + " JOIN roles ON roles.id = users.role_id ";
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";
    private static final String SQL_COUNT_BY_EMAIL = "  SELECT COUNT(`email`) as `count`FROM `users`WHERE `users`.`email`=?";
    private static final String SQL_COUNT_BY_LOGIN = "  SELECT COUNT(`login`) as `count`FROM `users`WHERE `users`.`login`=?";
    private static final String FIND_USER_ID_BY_LOGIN = "SELECT id FROM users WHERE login = ?";



    @Override
    public boolean existRowsByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COUNT_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            boolean result = false;
            while (resultSet.next()) {
                result = (resultSet.getInt(COUNT) >= 1);
            }
            return result;
        } catch (SQLException sqlException) {
            throw new DaoException("SQL request error. " + sqlException.getMessage(), sqlException);
        }
    }

    @Override
    public boolean existRowsByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COUNT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            boolean result = false;
            while (resultSet.next()) {
                result = (resultSet.getInt(COUNT) >= 1);
            }
            return result;
        } catch (SQLException sqlException) {
            throw new DaoException("SQL request error. " + sqlException.getMessage(), sqlException);
        }
    }


    @Override
    public Map<String, Optional<String>> findUserLoginDataByLogin(String login) throws DaoException {
        Map<String, Optional<String>> resultMap = new HashMap<>();
        resultMap.put(USER_PASSWORD_SALT, Optional.empty());
        resultMap.put(USER_PASSWORD_HASH, Optional.empty());
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOGIN_DATA_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultMap.put(USER_PASSWORD_HASH, Optional.of(resultSet.getString(USER_PASSWORD_HASH)));
                resultMap.put(USER_PASSWORD_SALT, Optional.of(resultSet.getString(USER_PASSWORD_SALT)));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_LOGIN_DATA_BY_LOGIN, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_LOGIN_DATA_BY_LOGIN, e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Optional<String>> findUserLoginDataByEmail(String email) throws DaoException {
        Map<String, Optional<String>> resultMap = new HashMap<>();
        resultMap.put(USER_PASSWORD_SALT, Optional.empty());
        resultMap.put(USER_PASSWORD_HASH, Optional.empty());
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOGIN_DATA_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultMap.put(USER_PASSWORD_HASH, Optional.of(resultSet.getString(USER_PASSWORD_HASH)));
                resultMap.put(USER_PASSWORD_SALT, Optional.of(resultSet.getString(USER_PASSWORD_SALT)));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_LOGIN_DATA_BY_LOGIN, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_LOGIN_DATA_BY_LOGIN, e);
        }
        return resultMap;
    }


    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                long id = resultSet.getInt(USER_ID);
                String login = resultSet.getString(USER_LOGIN);
                String email = resultSet.getString(USER_EMAIL);
                int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
                RatesType ratesOfSolve = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));

                User user = new User(id, login, email, countOfSolve, role, ratesOfSolve, status);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_ALL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_ALL, e);
        }
        return users;
    }


    public boolean createNewUser(User user, String password, String salt) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER);) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getCountOfSolve());
            statement.setString(5, user.getRatesType().toString());
            statement.setString(6, user.getRoleType().toString());
            statement.setString(7, password);
            statement.setString(8, salt);
            statement.setString(9, user.getStatus().toString());
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with creating new User. ", e);
        }
    }

    public void changePassword(String login, String password) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD)) {
            statement.setString(1, password);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error with changing password. ", e);
        }
    }


    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt(USER_ID);
                String login = resultSet.getString(USER_LOGIN);
                String resultEmail = resultSet.getString(USER_EMAIL);
                int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
                Role userRole = null;
                int roleId = resultSet.getInt(USER_ROLE);
                if (roleId == 1) {
                    userRole = Role.ADMIN;
                } else if (roleId == 2) {
                    userRole = Role.USER;
                }
                RatesType ratesType = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));


                //   User user = new User(id, login, email, countOfSolve, userRole, ratesType);
                //   userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return userOptional;
    }


    @Override
    public Optional<Long> findUserIdByLogin(String login) throws DaoException {
        Optional<Long> result = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ID_BY_LOGIN);) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            result = Optional.of(resultSet.getLong(USER_ID));
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with search of id. ", e);
        }
        return result;
    }

}



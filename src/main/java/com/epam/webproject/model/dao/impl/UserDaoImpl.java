package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.util.PasswordEncryptor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_USER_BY_LOGIN = "SELECT id, email, count_of_solve, rates_of_solve, `role`, `status` FROM users WHERE login = ? ";

    private static final String FIND_USER_BY_EMAIL = "SELECT id, login, count_of_solve, rates_of_solve, `role`, `status` FROM users WHERE email = ? ";

    private static final String FIND_LOGIN_DATA_BY_LOGIN = "SELECT password_hash,salt FROM users WHERE login = ?";

    private static final String FIND_LOGIN_DATA_BY_EMAIL = "SELECT password_hash, salt FROM users WHERE email = ?";

    private static final String ADD_USER = "INSERT INTO `users` (`id`, `login`, `email`, `count_of_solve`, `rates_of_solve`, `role`, `password_hash`,`salt`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

    private static final String FIND_ALL = "SELECT id, login, email, count_of_solve, rates_of_solve, `role`, `status` FROM users";
    //            + " JOIN roles ON roles.id = users.role_id ";

    private static final String BLOCK_USER = "UPDATE users SET status = 'BLOCKED' WHERE login = ?";

    private static final String UNBLOCK_USER = "UPDATE users SET status = 'NORMAL' WHERE login = ?";

    private static final String COUNT_BY_EMAIL = "  SELECT COUNT(`email`) as `count`FROM `users`WHERE `users`.`email`=?";

    private static final String COUNT_BY_LOGIN = "  SELECT COUNT(`login`) as `count`FROM `users`WHERE `users`.`login`=?";

    private static final String FIND_USER_ID_BY_LOGIN = "SELECT id FROM users WHERE login = ?";

    private static final String UPDATE_LOGIN_AND_EMAIL = "UPDATE IGNORE `users` SET `login`=?, `email`=? WHERE `login`=?";

    private static final String USERS_FULL_TEXT_SEARCH = "SELECT  id, login, email, count_of_solve, rates_of_solve, `role`, `status` FROM `users` WHERE MATCH (login,email) AGAINST (?);";

    private static final String FIND_STATUS_BY_USER_LOGIN = " SELECT `status` FROM users WHERE login = ?";

    private static final String FIND_LOGIN_BY_EMAIL = "SELECT login FROM users WHERE email = ?";

    private static final String SET_PASSWORD_BY_ID = "UPDATE users SET password_hash = ? , salt = ? WHERE id = ?";

    private static final String FIND_INFO_FOR_RATES = " SELECT count_of_solve , sum(answers.likes) " +
            "FROM users " +
            "join answers on answers.user_id =users.id where users.id =" +
            "(SELECT id FROM users WHERE login = ?);";
    private static final String UPDATE_RATES = "UPDATE `first_project`.`users` SET `rates_of_solve`= ? WHERE  `login`=?;";


    @Override
    public boolean existRowsByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_BY_EMAIL)) {
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
             PreparedStatement statement = connection.prepareStatement(COUNT_BY_LOGIN)) {
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
                resultMap.put(USER_PASSWORD_HASH, Optional.ofNullable(resultSet.getString(USER_PASSWORD_HASH)));
                resultMap.put(USER_PASSWORD_SALT, Optional.ofNullable(resultSet.getString(USER_PASSWORD_SALT)));
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
                resultMap.put(USER_PASSWORD_HASH, Optional.ofNullable(resultSet.getString(USER_PASSWORD_HASH)));
                resultMap.put(USER_PASSWORD_SALT, Optional.ofNullable(resultSet.getString(USER_PASSWORD_SALT)));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_LOGIN_DATA_BY_LOGIN, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_LOGIN_DATA_BY_LOGIN, e);
        }
        return resultMap;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);) {

            while (resultSet.next()) {
                long id = resultSet.getInt(ID);
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

    @Override
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


    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt(ID);
                String email = resultSet.getString(USER_EMAIL);
                int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
                RatesType ratesOfSolve = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));
                Role role = Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase());
                //todo not upper case
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
                User user = new User(id, login, email, countOfSolve, role, ratesOfSolve, status);
                userOptional = Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt(ID);
                String login = resultSet.getString(USER_LOGIN);
                int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
                RatesType ratesOfSolve = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));
                User user = new User(id, login, email, countOfSolve, role, ratesOfSolve, status);
                userOptional = Optional.ofNullable(user);
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
            result = Optional.of(resultSet.getLong(ID));
        } catch (SQLException e) {
            logger.info("SQL request error({}). {}", e.getErrorCode(), e.getMessage());
            throw new DaoException("Error with search of id. ", e);
        }
        return result;
    }

    @Override
    public boolean updateUserName(String newLogin, String newEmail, String oldLogin) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LOGIN_AND_EMAIL)) {
            statement.setString(1, newLogin);
            statement.setString(2, newEmail);
            statement.setString(3, oldLogin);
            return (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException("SQL request error. " + e.getMessage(), e);
        }
    }

    @Override
    public ArrayDeque<User> findByFullText(String text) throws DaoException {
        ArrayDeque<User> arrayDeque = new ArrayDeque<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USERS_FULL_TEXT_SEARCH)) {
            preparedStatement.setString(1, text);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getInt(ID);
                String login = resultSet.getString(USER_LOGIN);
                String email = resultSet.getString(USER_EMAIL);
                int countOfSolve = resultSet.getInt(COUNT_OF_SOLVE);
                RatesType ratesOfSolve = RatesType.valueOf(resultSet.getString(RATES_OF_SOLVE));
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));

                User user = new User(id, login, email, countOfSolve, role, ratesOfSolve, status);
                arrayDeque.add(user);

            }
        } catch (SQLException e) {
            logger.error("Can't find", e);
            throw new DaoException(e);
        }
        return arrayDeque;
    }

    @Override
    public boolean blockUser(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(BLOCK_USER)) {
            statement.setString(1, login);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Error with changing password. ", e);
        }
    }

    @Override
    public boolean unblockUser(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UNBLOCK_USER)) {
            statement.setString(1, login);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Error with changing password. ", e);
        }
    }


    @Override
    public Optional<Status> findStatusByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_STATUS_BY_USER_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            Status userStatus = null;
            while (resultSet.next()) {
                userStatus = Status.valueOf(resultSet.getString(USER_STATUS));
            }
            return Optional.ofNullable(userStatus);
        } catch (SQLException sqlException) {
            throw new DaoException("SQL request error. " + sqlException.getMessage(), sqlException);
        }
    }

    @Override
    public Optional<String> findLoginByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOGIN_BY_EMAIL)) {
            Optional<String> result = Optional.empty();
            logger.debug(email);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = Optional.ofNullable(resultSet.getString(USER_LOGIN));
            }
            return result;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_LOGIN_BY_EMAIL, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_LOGIN_BY_EMAIL, e);

        }
    }

    @Override
    public void setPasswordById(long id, String password, String salt) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_PASSWORD_BY_ID)) {
            statement.setString(1, password);
            statement.setString(2, salt);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't handle UserDao.setPasswordByID request", e);
        }
    }

    @Override
    public Map<String, Long> findInfoForRates(String login) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_INFO_FOR_RATES)) {
            Map<String, Long> map = new HashMap<>();
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                map.put(COUNT_OF_SOLVE, resultSet.getLong(COUNT_OF_SOLVE));
                map.put(LIKES, resultSet.getLong(SUM_OF_LIKES));
            }
            return map;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can not proceed `{}` request: {}", FIND_INFO_FOR_RATES, e.getMessage());
            throw new DaoException("Can not proceed request: " + FIND_INFO_FOR_RATES, e);

        }
    }

    public boolean setRates(String login, RatesType ratesType) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RATES)) {
            statement.setString(1, ratesType.toString());
            statement.setString(2, login);

            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Can't handle UserDao.setPasswordByID request", e);
        }
    }
}




package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.entity.type.RatesType;
import com.epam.webproject.model.entity.type.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.type.Status;
import com.epam.webproject.model.factory.UserFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE email = ?";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT id, login, email, count_of_solve, rates_of_solve, `role`, `status` FROM users WHERE email = ? AND password = ?";

    private static final String FIND_USER_BY_ID = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE user_id = ?";

    private static final String ADD_USER = "INSERT INTO `users` (`id`, `login`, `email`, `count_of_solve`, `rates_of_solve`, `role`, `password_hash`,`salt`,`status`) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";

    private static final String FIND_ALL = "SELECT id, login, email, count_of_solve, rates_of_solve, `role`, `status` FROM users";
    //            + " JOIN roles ON roles.id = users.role_id ";
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";

    private static final String SQL_FIND_PASSWORD_HASH_BY_EMAIL = "SELECT `users`.`user_pswd_hash` " +
            "FROM `users` " +
            "WHERE `users`.`email`=?";


    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        UserFactory factory=UserFactory.getInstance();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {

            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
                User userEntity=factory.createUser(resultSet);
                 user=Optional.of(userEntity);
        } catch (SQLException e) {
            throw new DaoException("Error with find User by login .", e);
        }
        return user;

    }

    public Optional<String> findPasswordHashByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_HASH_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            String result = null;
            while (resultSet.next()) {
                result = resultSet.getString(USER_PASSWORD_HASH);
            }
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new DaoException("SQL request error. " + e.getMessage(), e);
        }
    }

//    public List<User> findUsersByRole(Role role) throws DaoException {
//        List<User> users = new ArrayList<>();
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
//        //     PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ROLE)) {
//            statement.setLong(1, Role.ordinal(role));
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//            //    User user = UserCreator.createUser(resultSet);
//              //  users.add(user);
//            }
//        } catch (SQLException ex) {
//            throw new DaoException("Error. Impossible get data from data base.", ex);
//        }
//        return users;
//    }


//    public List<User> findUsersByStatus(Status status) throws DaoException {
//        List<User> users = new ArrayList<>();
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_STATUS)) {
//            statement.setLong(1, Status.ordinal(status));
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                //User user = UserCreator.createUser(resultSet);
//           //     users.add(user);
//            }
//        } catch (SQLException ex) {
//            throw new DaoException("Error. Impossible get data from data base.", ex);
//        }
//        return users;
//    }

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
                System.out.println( resultSet.getString(USER_ROLE));
                Role role = Role.valueOf(resultSet.getString(USER_ROLE));
                Status status = Status.valueOf(resultSet.getString(USER_STATUS));

                User user = new User(id, login, email, countOfSolve, role, ratesOfSolve,status);
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
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
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
            logger.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }


}
//    @Override
//    public Optional<User> findByEmail(String email) throws DaoException {
//        Optional<User> userOptional = Optional.empty();
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
//            preparedStatement.setString(1, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                User user = User.builder()
//                        .setId(resultSet.getLong(1))
//                        .setEmail(resultSet.getString(2))
//                        .setName(resultSet.getString(3))
//                        .setSurname(resultSet.getString(4))
//                        .setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()))
//                        .setEnabled(resultSet.getBoolean(6))
//                        .setMoney(resultSet.getBigDecimal(7))
//                        .build();
//                userOptional = Optional.of(user);
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//            throw new DaoException(e);
//        }
//        return userOptional;
//    }
//
//    @Override
//    public Long findMaxUserId() throws DaoException {
//        Long id;
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_MAX_USER_ID)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            id = resultSet.getLong(1);
//        } catch (SQLException e) {
//            logger.error(e);
//            throw new DaoException(e);
//        }
//        return id;
//    }





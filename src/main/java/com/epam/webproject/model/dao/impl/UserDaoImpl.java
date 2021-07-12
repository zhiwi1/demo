package com.epam.webproject.model.dao.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.connection.ConnectionPool;
import com.epam.webproject.model.entity.RoleType;
import com.epam.webproject.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl {
    private final static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE email = ?";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, email, name, surname, role, enabled, money, photo FROM course.users WHERE email = ? AND password = ?";

    private static final String FIND_USER_BY_ID = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE user_id = ?";

    private final static String ADD_USER = "INSERT INTO `users` (`email`, `name`, `surname`, `password`, `role`, `enabled`) VALUES (?, ?, ?, ?, ?, ?)";

//
//    private static final String UPDATE_USER_PASSWORD = "UPDATE course.users SET password = ? WHERE user_id = ?";
//
//    private static final String UPDATE_USER_BALANCE = "UPDATE course.users SET money = ? WHERE user_id = ?";
//
//    private static final String FIND_ALL_USERS_LIMIT = "SELECT user_id, email, name, surname, role, enabled, money, photo FROM course.users LIMIT ?, ?";
//
//    private static final String ENROLL_COURSE = "INSERT INTO `users_x_courses` (`fk_user_id`, `fk_course_id`) VALUES (?, ?)";
//
//    private static final String USER_ENROLL_COURSE = "SELECT fk_user_id, fk_course_id FROM course.users_x_courses WHERE fk_user_id = ? AND fk_course_id = ?";
//
//    private static final String UPDATE_USER_PHOTO = "UPDATE course.users SET photo = ? WHERE user_id = ?";
//
//    private static final String BLOCK_USER = "UPDATE course.users SET enabled = false WHERE user_id = ?";
//
//    private static final String UNBLOCK_USER = "UPDATE course.users SET enabled = true WHERE user_id = ?";
//
//    private static final String FIND_MAX_USER_ID = "SELECT MAX(user_id) FROM users";
//
//    private static final String UPDATE_USER_ROLE = "UPDATE course.users SET role = ? WHERE user_id = ?";
//
//    private static final String UPDATE_USER_NAME_AND_SURNAME = "UPDATE course.users SET name = ?, surname = ? WHERE user_id = ?";
//
//    private static final String FIND_ALL_USERS_ENROLLED_COURSE = "SELECT user_id, email, name, surname, course_id, course_name FROM course.users_x_courses INNER JOIN course.users ON fk_user_id = user_id INNER JOIN course.courses ON fk_course_id = course_id";

//    @Override
//    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
//        Optional<User> userOptional = Optional.empty();
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection())
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
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
//                        .setPhoto(resultSet.getString(8))
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

}

package com.epam.webproject.model.service.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoProvider;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.UserService;
import com.epam.webproject.util.PasswordEncryptor;
import com.epam.webproject.util.UserIdGenerator;
import com.epam.webproject.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = DaoProvider.getInstance().getUserDao();
    private static final int DEFAULT_COUNT_OF_SOLVE = 0;


    //
//        @Override
//        public boolean checkLogin(String email, String password)
//                throws ServiceException {
//            boolean result = false;
//            if (UserValidator.checkEmail(email) && UserValidator.checkPassword(password)) {
//                try {
//                    Optional<LogInData> logInDataOptional = userDao.findLoginDataByEmail(email);
//                    if (logInDataOptional.isPresent()) {
//                        LogInData logInData = logInDataOptional.get();
//                        result = PasswordHash.check(password, logInData.getPasswordHash());
//                    }
//                } catch (DaoException daoException) {
//                    logger.error("checkLogin > Can not read data from database: {}", daoException.getMessage());
//                    throw new ServiceException("Can not read data from database", daoException);
//                }
//            }
//            return result;
//        }


    public boolean signInUser(String loginOrEmail, String password) throws ServiceException {
        boolean result = false;
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        Map<String, Optional<String>> loginData = new HashMap<>();
        try {
            if (UserValidator.checkPassword(password)) {
                if (UserValidator.checkEmail(loginOrEmail)) {
                    loginData = userDao.findUserLoginDataByEmail(loginOrEmail);
                } else {
                    loginData = userDao.findUserLoginDataByLogin(loginOrEmail);
                }
            }
            if (loginData.get(USER_PASSWORD_HASH).isPresent() &&
                    loginData.get(USER_PASSWORD_SALT).isPresent()) {
                String salt = loginData.get(USER_PASSWORD_SALT).get();
                String passwordHash = loginData.get(USER_PASSWORD_HASH).get();
                String realPasswordHash = encryptor.getHash(password, salt);
                result = passwordHash.equals(realPasswordHash);
            }
        } catch (DaoException e) {
            logger.error("Can't sign in", e.getMessage());
            throw new ServiceException("Can't sign in", e);
        }

        return result;
    }

    public Feedback registerUser(String login, String email, String password, String confirmPassword) throws ServiceException {
        Feedback feedback;
        if (UserValidator.checkEmail(email) && UserValidator.checkPassword(password) &&
                UserValidator.checkPasswordAndConfirmPassword(password, confirmPassword)) {
            try {

                if (!userDao.existRowsByLogin(login) && !userDao.existRowsByEmail(email)) {

                    long id = UserIdGenerator.generateId();
                    logger.info(id);
                    PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
                    String salt = encryptor.generateSalt();
                    String hashPassword = encryptor.getHash(password, salt);
                    User user = new User(id, login, email, DEFAULT_COUNT_OF_SOLVE, Role.USER, RatesType.NEWBIE, Status.NORMAL);
                    logger.info(user);
                    boolean isCreated = userDao.createNewUser(user, hashPassword, salt);

                    if (isCreated) {
                        feedback = Feedback.SUCCESS;
                    } else feedback = Feedback.DATABASE_EXCEPTION;
                } else feedback = Feedback.LOGIN_OR_EMAIL_EXISTS;
            } catch (DaoException e) {
                logger.error("Can't create new user : {}", e.getMessage());
                throw new ServiceException("Can't create new user", e);
            }
        } else {
            feedback = Feedback.CHECK_DATA;
        }
        return feedback;
    }
}
//}

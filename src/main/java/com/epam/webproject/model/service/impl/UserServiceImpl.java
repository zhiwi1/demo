package com.epam.webproject.model.service.impl;


import java.util.*;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoProvider;

import static com.epam.webproject.model.dao.DatabaseColumnName.*;

import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.email.MailSender;
import com.epam.webproject.model.entity.*;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.UserService;
import com.epam.webproject.util.PasswordEncryptor;
import com.epam.webproject.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = DaoProvider.getInstance().getUserDao();



    public boolean signInUser(String loginOrEmail, String password) throws ServiceException {
        boolean result = false;
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        Map<String, Optional<String>> loginData = new HashMap<>();
        try {
            if (UserValidator.checkPassword(password)) {
                if (UserValidator.checkEmail(loginOrEmail)) {
                    loginData = userDao.findUserLoginDataByEmail(loginOrEmail);
                } else if (UserValidator.checkLength(loginOrEmail)) {
                    loginData = userDao.findUserLoginDataByLogin(loginOrEmail);
                }
            }

            if (loginData.containsKey(USER_PASSWORD_HASH) && loginData.get(USER_PASSWORD_HASH).isPresent() &&
                    loginData.containsKey(USER_PASSWORD_HASH) &&
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
                    PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
                    String salt = encryptor.generateSalt();
                    String hashPassword = encryptor.getHash(password, salt);
                    User user = new User(login, email, Role.USER, RatesType.NEWBIE, Status.NORMAL);
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

    @Override
    public Optional<User> findByLoginOrEmail(String loginOrEmail) throws ServiceException {
        Optional<User> userOptional = Optional.empty();
        UserDao userDao = DaoProvider.getInstance().getUserDao();
        try {
            if (UserValidator.checkEmail(loginOrEmail)) {
                userOptional = (userDao.findByEmail(loginOrEmail));
            } else {
                userOptional = userDao.findByLogin(loginOrEmail);
            }
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException("Error occured when finding user by login " + loginOrEmail + " :" + e.getMessage(), e);
        }
    }

    @Override
    public Deque<User> showAllUsers() throws ServiceException {
        try {
            Deque<User> users = userDao.findAll();
            return users;
        } catch (DaoException e) {
            logger.error("Can't create task", e.getMessage());
            throw new ServiceException("Can't create task", e);
        }

    }
    @Override
    public Deque<User> findAllUsersWithLimit(int offset,int limit) throws ServiceException {
        try {
            Deque<User> users = userDao.findAll(offset, limit);
            return users;
        } catch (DaoException e) {
            logger.error("Can't create task", e.getMessage());
            throw new ServiceException("Can't create task", e);
        }

    }

    @Override
    public boolean updateUser(String newLogin, String newEmail, String oldLogin, String oldEmail) throws ServiceException {
        boolean result = false;
        boolean isLoginExists = false;
        boolean isEmailExists = false;
        try {
            if (!newLogin.equals(oldLogin)) {
                isLoginExists = userDao.existRowsByLogin(newLogin);
            }
            if (!newEmail.equals(oldEmail) && UserValidator.checkEmail(newEmail)) {
                isEmailExists = userDao.existRowsByEmail(newEmail);
            }
            if (!isLoginExists && !isEmailExists) {
                result = userDao.updateUserName(newLogin, newEmail, oldLogin);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Deque<User> findByFullText(String text) throws ServiceException {
        try {
            Deque<User> arrayDeque = new ArrayDeque<>();
            if (UserValidator.checkLength(text)) {
                arrayDeque = userDao.findByFullText(text);
            }
            return arrayDeque;
        } catch (DaoException e) {
            logger.error("Can't find task", e.getMessage());
            throw new ServiceException("Can't find  task", e);
        }

    }

    @Override
    public boolean blockUser(String login) throws ServiceException {

        try {
            return userDao.blockUser(login);
        } catch (DaoException e) {
            logger.error("Can't block user", e.getMessage());
            throw new ServiceException("Can't block user", e);
        }
    }

    @Override
    public boolean unblockUser(String login) throws ServiceException {

        try {
            return userDao.unblockUser(login);
        } catch (DaoException e) {
            logger.error("Can't block user", e.getMessage());
            throw new ServiceException("Can't block user", e);
        }
    }

    @Override
    public boolean checkUserStatus(String login, Status expectedStatus) throws ServiceException {
        try {
            Optional<Status> userStatusOptional = userDao.findStatusByLogin(login);
            return (userStatusOptional.isPresent() && userStatusOptional.get() == expectedStatus);
        } catch (DaoException e) {
            throw new ServiceException("Can not check status: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<String> findLogin(String loginOrEmail) throws ServiceException {
        Optional<String> loginOptional = Optional.empty();
        try {
            if (UserValidator.checkEmail(loginOrEmail)) {
                loginOptional = userDao.findLoginByEmail(loginOrEmail);
            } else if (UserValidator.checkLength(loginOrEmail)) {
                loginOptional = Optional.of(loginOrEmail);
            }
        } catch (DaoException e) {
            throw new ServiceException("Can not check status: " + e.getMessage(), e);
        }
        return loginOptional;
    }

    @Override
    public boolean forgetPassword(String email) throws ServiceException {
        boolean result = false;
        if (UserValidator.checkEmail(email)) {

            try {
                Optional<User> user = userDao.findByEmail(email);
                if (user.isPresent()) {
                    PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
                    String newPassword = encryptor.generateRandomPassword();
                    String salt = encryptor.generateSalt();
                    String hashPassword = encryptor.getHash(newPassword, salt);
                    userDao.setPasswordById(user.get().getId(), hashPassword, salt);
                    MailSender.send(email, MailSender.messageForgetPassword(user.get().getLogin(), newPassword));
                    result = true;
                }
            } catch (DaoException e) {
                throw new ServiceException("Can't handle forgetPassword request at UserService", e);
            }
        }
        return result;
    }

    @Override
    public RatesType calculateRatesOfSolve(String login) throws ServiceException {
        final class RatesBall {
            private static final int BALL_OF_LIKE = 1;
            private static final int BALL_OF_SOLVE = 5;
        }
        try {
            RatesType ratesType = RatesType.NEWBIE;
            Map<String, Long> infoForCalculating = userDao.findInfoForRates(login);
            long countOfLikes = infoForCalculating.get(LIKES);
            long countOfSolve = infoForCalculating.get(COUNT_OF_SOLVE);
            long rates = RatesBall.BALL_OF_SOLVE * countOfSolve + countOfLikes * RatesBall.BALL_OF_LIKE;
            if (rates > 10 && rates < 20) {
                ratesType = RatesType.STUDENT;
            } else if (rates >= 20 && rates < 30) {
                ratesType = RatesType.HARDWORKER;
            } else if (rates >= 30) {
                ratesType = RatesType.PROFESSIONAL;
            }
            return ratesType;
        } catch (DaoException e) {
            throw new ServiceException("Can't process calculateRatesOfSolve request at UserService", e);

        }
    }

    @Override
    public boolean setRates(String login, RatesType ratesType) throws ServiceException {
        try {
            boolean result = userDao.setRates(login, ratesType);
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Can't process calculateRatesOfSolve request at UserService", e);
        }
    }

    @Override
    public int countOfUsers() throws ServiceException {
        try {
            return userDao.countOfUsers();
        } catch (DaoException daoException) {
            throw new ServiceException("Can not read data from database: " + daoException.getMessage(), daoException);
        }
    }
}
//}

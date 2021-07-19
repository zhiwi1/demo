package com.epam.webproject.model.service.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoDefinition;
import com.epam.webproject.model.dao.DatabaseColumnName;
import com.epam.webproject.model.dao.UserDao;
import com.epam.webproject.model.dao.impl.UserDaoImpl;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.entity.type.RatesType;
import com.epam.webproject.model.entity.type.Role;
import com.epam.webproject.model.entity.type.Status;
import com.epam.webproject.model.service.UserService;
import com.epam.webproject.util.PasswordEncryptor;
import com.epam.webproject.util.UserIdGenerator;
import com.epam.webproject.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private final UserDao userDao = DaoDefinition.getInstance().getUserDao();
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
            if (UserValidator.checkEmail(loginOrEmail) && UserValidator.checkPassword(password)) {
                loginData = userDao.findUserLoginDataByEmail(loginOrEmail);
            } else {
                loginData = userDao.findUserLoginDataByLogin(loginOrEmail);
            }
            if (loginData.get(DatabaseColumnName.USER_PASSWORD_HASH).isPresent() &&
                    loginData.get(DatabaseColumnName.USER_PASSWORD_SALT).isPresent()) {
                String salt = loginData.get(DatabaseColumnName.USER_PASSWORD_SALT).get();
                String passwordHash = loginData.get(DatabaseColumnName.USER_PASSWORD_HASH).get();
                String realPasswordHash = encryptor.getHash(password, salt);
                result = passwordHash.equals(realPasswordHash);
            }
        } catch (DaoException daoException) {
            logger.error("Can't sign in", daoException.getMessage());
            throw new ServiceException();
        }

        return result;
    }

    public boolean registerUser(String login, String email, String password, String confirmPassword) throws ServiceException {
        //todo validation+ delete bool
        long id = UserIdGenerator.generateId();
        logger.info(id);
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        String salt = encryptor.generateSalt();
        String hashPassword = encryptor.getHash(password, salt);
        User user = new User(id, login, email, DEFAULT_COUNT_OF_SOLVE, Role.USER, RatesType.NEWBIE, Status.NORMAL);
        logger.info(user);
        try {
            userDao.createNewUser(user, hashPassword, salt);
        } catch (DaoException daoException) {
            logger.error("Can't create new user : {}", daoException.getMessage());
            throw new ServiceException("Can't create new user", daoException);
        }
        return true;
    }
}
//}

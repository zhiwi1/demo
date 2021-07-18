package com.epam.webproject.model.service.impl;


    import java.util.Optional;

    import com.epam.webproject.exception.DaoException;
    import com.epam.webproject.exception.ServiceException;
    import com.epam.webproject.model.dao.UserDao;
    import com.epam.webproject.model.service.UserService;
    import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//    public class UserServiceImpl implements UserService {
//        private static final Logger logger = LogManager.getLogger();
//        private final UserDao userDao = DaoDefinition.getInstance().getUserDao();
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
//    }
//}

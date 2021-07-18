package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.RequestParameter;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

//public class RegisterUserCommand implements Command {
//    private static final Logger logger = LogManager.getLogger();
//    private final UserService service = new UserServiceImpl();
//   Router router = new Router();
//
//@Override
//    public Router execute(HttpServletRequest request) {
//
//        User user = new User();
//        String firstName = request.getParameter(FIRST_NAME_PARAM);
//        String lastName = request.getParameter(LAST_NAME_PARAM);
//        String email = request.getParameter(EMAIL_PARAM);
//        String login = request.getParameter(LOGIN_PARAM);
//        String password = request.getParameter(PASSWORD_PARAM);
//        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);
//
//        Map<String, Boolean> dataCheckService = new HashMap<>();
//        dataCheckService.put(login, false);
//        dataCheckService.put(password, false);
//        dataCheckService.put(confirmPassword, false);
//
//        if (login.matches(DataUserValidator.LOGIN.getRegExp())) {
//            //todo проверка на то есть ли такой в базе, если есть такой то тоже оповестить
//            dataCheckService.put(login, true);
//            user.setLogin(login);
//        } else {
//            request.setAttribute(LOGIN_ERROR_PARAM, DataUserValidator.LOGIN.getMessage());
//        }
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//
//        if (email.matches(DataUserValidator.EMAIL.getRegExp())) {
//            dataCheckService.put(email, true);
//            user.setEmail(email);
//        } else {
//            request.setAttribute(EMAIL_ERROR_PARAM, DataUserValidator.EMAIL.getMessage());
//        }
//
//        user.setRole(UserRole.STUDENT);
//        user.setStatus(UserStatus.ACTIVE);
//
//        if (password.matches(DataUserValidator.PASSWORD.getRegExp())) {
//            dataCheckService.put(password, true);
//        } else {
//            request.setAttribute(PASSWORD_ERROR_PARAM, DataUserValidator.PASSWORD.getMessage());
//        }
//        if (password.equals(confirmPassword)) {
//            try {
//                service.createNewUser(user, password);
//            } catch (ServiceException e) {
//                logger.log(Level.ERROR, "Error while client registration data", e);
//                router.setPagePath(ERROR_PAGE);
//            }
//        } else {
//            request.setAttribute(PASSWORD_ERROR_PARAM, DataUserValidator.PASSWORD.getMessage());
//        }
//
//        //todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли
//
//        router.setPagePath(REGISTRATION);
//        request.setAttribute(USER_PARAM, user);
//        router.setPagePath(LOGIN_PAGE); //todo убрать и продумать всю логику неудачной регистрации
//        return router;
//    }


//}

//public class RegisterUserCommand implements Command {
//
//    private static final Logger logger = LogManager.getRootLogger();
//
//    @Override
//    public Router execute(HttpServletRequest request, HttpServletResponse response) {
//        String email = request.getParameter(RequestParameter.EMAIL.getValue());
//        String login = request.getParameter(RequestParameter.LOGIN.getValue());
//        String password = request.getParameter(RequestParameter.PASSWORD.getValue());
//        String passwordRepeat = request.getParameter(RequestParameter.PASSWORD_CONFIRM.getValue());
//        ServiceProvider serviceProvider = ServiceProvider.getInstance();
//        UserService userService = serviceProvider.getUserService();
//        Router router;
//        try {
//            RegisterResultInfo registerResult = userService.registerUser(email, login, password, passwordRepeat, IMAGE_DEFAULT.getValue(), Role.USER);
//            String jsonResponse = new JSONObject()
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_EMAIL_STATUS.getValue(), registerResult.isEmailCorrect())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_LOGIN_STATUS.getValue(), registerResult.isLoginCorrect())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_STATUS.getValue(), registerResult.isPasswordCorrect())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_CONFIRM_STATUS.getValue(), registerResult.isPasswordConfirmationCorrect())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_EMAIL_FEEDBACK.getValue(), registerResult.getEmailErrorInfo())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_LOGIN_FEEDBACK.getValue(), registerResult.getLoginErrorInfo())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_FEEDBACK.getValue(), registerResult.getPasswordErrorInfo())
//                    .put(ErrorFeedback.REGISTER_RESULT_INFO_PASSWORD_CONFIRM_FEEDBACK	.getValue(), registerResult.getPasswordConfirmationErrorInfo())
//                    .toString();
//
//            if (registerResult.isEmailCorrect() && registerResult.isLoginCorrect()
//                    && registerResult.isPasswordCorrect() && registerResult.isPasswordConfirmationCorrect()) {
//                response.setStatus(200);
//                router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
//            } else {
//                response.setStatus(400);
//                router = new Router(null, jsonResponse, RouterType.AJAX_RESPONSE);
//            }
//        } catch (ServiceException e) {
//            logger.log(Level.ERROR, "Exception occured while register: {}", e.getMessage());
//            router = new Router(PagePath.ERROR_PAGE_SERVER_JSP.getValue(), null, RouterType.FORWARD);
//        }
//        return router;
//    }

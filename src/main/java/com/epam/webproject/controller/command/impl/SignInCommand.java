package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.ProjectException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.service.ServiceDefinition;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws ProjectException {
        String loginOrPassword = request.getParameter(RequestParameter.EMAIL_LOGIN);
        String password =request.getParameter(RequestParameter.PASSWORD);

        UserService userService = ServiceDefinition.getInstance().getUserService();
        try {
            boolean is = userService.signInUser(loginOrPassword, password);
        } catch (ServiceException exception) {
            throw new ProjectException("SignIn command error ", exception);
        }
        return new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);}

    }

//    @Override
//    public Router execute(HttpServletRequest request){
//     //   String currentLocale = (String) request.getSession().getAttribute(SessionKey.CURRENT_LOCALE.name());
//       // Localizer localizer = Localizer.valueOf(currentLocale.toUpperCase());
//        try {
//
//            String email = request.getParameter(RequestParameter.EMAIL);
//            String login=request.getParameter(RequestParameter.LOGIN);
//            String password = request.getParameter(RequestParameter.PASSWORD);
//            String passwordConfirm = request.getParameter(RequestParameter.CONFIRM_PASSWORD);
//    return new Router(RouterType.FORWARD,PagePath.LOGIN_PAGE);}}}
//            UserService userService = UserServiceImpl.;
//            Map<ResponceKey, String> resultMap = userService.registerUser(email, password, passwordConfirm, firstName,
//                    lastName, phone, role);
//
//            if (resultMap.get(ResponceKey.RESP_REGISTRATION_RESULT_STATUS).equals(KEY_STYLE_SUCCESS.getValue())) {
//                resultMap.put(ResponceKey.RESP_REGISTRATION_RESULT_MESSAGE,
//                       // localizer.getText(LocalizeKey.TEXT_REGISTRATION_SUCCESS_MESSAGE));
//            } else {
//                if (resultMap.get(ResponceKey.RESP_REGISTRATION_RESULT_STATUS).equals(RequestFieldKey.KEY_STYLE_ERROR.getValue())) {
//                    resultMap.put(ResponceKey.RESP_REGISTRATION_RESULT_MESSAGE,
//                         //   localizer.getText(LocalizeKey.TEXT_REGISTRATION_ERROR_MESSAGE));
//                }
//                if (resultMap.get(ResponceKey.RESP_REGISTRATION_RESULT_STATUS).equals(KEY_STYLE_INSERT_ERROR.getValue())) {
//                    resultMap.put(ResponceKey.RESP_REGISTRATION_RESULT_MESSAGE,
//                           // localizer.getText(LocalizeKey.TEXT_REGISTRATION_INSERT_ERROR_MESSAGE));
//                    resultMap.put(ResponceKey.RESP_REGISTRATION_RESULT_STATUS, RequestFieldKey.KEY_STYLE_ERROR.getValue());
//                }
//            }
//
//            for (Map.Entry<ResponceKey, String> mapElement : resultMap.entrySet()) {
//                request.setAttribute(mapElement.getKey().name(), mapElement.getValue());
//            }
//
//            return new Router(RouterType.FORWARD, RouterPath.LOGIN_REGISTRATION_PAGE);
//        } catch (ServiceException serviceException) {
//            throw new ProjectException("Registration command error", serviceException);
//        }


package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ProjectException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String email = request.getParameter(RequestParameter.EMAIL);
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordConfirm = request.getParameter(RequestParameter.CONFIRM_PASSWORD);
        UserService userService = ServiceProvider.getInstance().getUserService();
        try {
            Feedback feedback = userService.registerUser(login, email, password, passwordConfirm);
            Router router = new Router();
            switch (feedback) {
                case SUCCESS: {
                    //todo messages
                  //  request.getSession().setAttribute(RequestParameter.LOGIN, login);
                    router = new Router(RouterType.REDIRECT, PagePath.GO_TO_LOGIN_PAGE);
                    break;
                }
                case DATABASE_EXCEPTION: {
                    router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
                    break;
                }
                case CHECK_DATA:
                case LOGIN_OR_EMAIL_EXISTS: {
                    request.setAttribute(RequestAttribute.MESSAGE, feedback);
                    //   request.getSession().setAttribute(RequestAttribute.PREV_REQUEST, PagePath.REGISTRATION_PAGE);
                    router = new Router(RouterType.REDIRECT, PagePath.GO_TO_REGISTRATION_PAGE_COMMAND);
                    break;
                }
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Registration command error", e);
        }
    }
}

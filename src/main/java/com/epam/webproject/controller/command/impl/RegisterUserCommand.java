package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
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
                    router = new Router(RouterType.REDIRECT, PagePath.GO_TO_LOGIN_PAGE);
                    break;
                }
                case CHECK_DATA:
                case DATA_EXISTS: {
                    request.getSession().setAttribute(RequestAttribute.MESSAGE, feedback);
                    router = new Router(RouterType.REDIRECT, PagePath.GO_TO_REGISTRATION_PAGE_COMMAND, RequestParameter.PREPARATION_FOR_ERROR_MESSAGE, feedback.toString());
                    break;
                }
                default: {
                    router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
                    break;
                }
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Registration command error"+e.getMessage(), e);
        }
    }
}

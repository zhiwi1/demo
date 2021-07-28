package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ProjectException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String loginOrEmail = request.getParameter(RequestParameter.EMAIL_LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        UserService userService = ServiceProvider.getInstance().getUserService();
        try {//todo error page and other
            boolean isSignIn = userService.signInUser(loginOrEmail, password);
            if (isSignIn) {
                router = new Router(RouterType.FORWARD, PagePath.HOME_PAGE);
            } else {
                router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
            }
            request.getSession().setAttribute(RequestAttribute.LOGIN, loginOrEmail);
        } catch (ServiceException e) {
            throw new CommandException("SignIn command error ", e);
        }
        return router;
    }

}



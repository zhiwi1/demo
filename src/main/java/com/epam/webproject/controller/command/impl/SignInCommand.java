package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.localization.Localization;
import com.epam.webproject.localization.LocalizationKey;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class SignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String loginOrEmail = request.getParameter(RequestParameter.EMAIL_LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        UserService userService = ServiceProvider.getInstance().getUserService();
        try {
            boolean isSignIn = userService.signInUser(loginOrEmail, password);
            if (isSignIn) {
                Optional<String> loginOptional = userService.findLogin(loginOrEmail);
                loginOptional.ifPresent(login -> request.getSession().setAttribute(RequestAttribute.LOGIN, login));
                  router = new Router(RouterType.REDIRECT, PagePath.GO_TO_HOME_PAGE);
            } else {
                router = new Router(RouterType.REDIRECT,PagePath.GO_TO_LOGIN_PAGE,RequestParameter.PREPARATION_FOR_ERROR_MESSAGE,RequestParameter.TRUE);
            }

        } catch (ServiceException e) {
            throw new CommandException("SignIn command error ", e);
        }
        return router;
    }

}



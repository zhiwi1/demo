package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class SignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String loginOrEmail = request.getParameter(RequestParameter.EMAIL_LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = ServiceProvider.getInstance().getUserService();
        try {
            Router router = new Router();
            boolean isSignIn = userService.signInUser(loginOrEmail, password);
            if (isSignIn) {

                Optional<String> loginOptional = userService.findLogin(loginOrEmail);
                if (loginOptional.isPresent()) {
                    request.getSession().setAttribute(RequestAttribute.LOGIN, loginOptional.get());
                    Optional<Role> roleOptional = userService.findRoleByLogin(loginOptional.get());
                    request.getSession().setAttribute(RequestAttribute.ROLE,roleOptional.get());
                }

                router = new Router(RouterType.REDIRECT, PagePath.GO_TO_HOME_PAGE);
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.GO_TO_LOGIN_PAGE, RequestParameter.PREPARATION_FOR_ERROR_MESSAGE, RequestParameter.TRUE);
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException("SignIn command error ", e);
        }

    }

}



package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ProjectException;
import com.epam.webproject.exception.ServiceException;
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
                //todo error page and other
                boolean is = userService.registerUser(login, email, password, passwordConfirm);
            } catch (ServiceException e) {
                throw new CommandException("Registration command error", e);
            }
        return new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);}


    }

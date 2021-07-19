package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.ProjectException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceDefinition;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws ProjectException {

            String email = request.getParameter(RequestParameter.EMAIL);
            String login = request.getParameter(RequestParameter.LOGIN);
            String password = request.getParameter(RequestParameter.PASSWORD);
            String passwordConfirm = request.getParameter(RequestParameter.CONFIRM_PASSWORD);
            UserService userService = ServiceDefinition.getInstance().getUserService();
            try {
                boolean is = userService.registerUser(login, email, password, passwordConfirm);
            } catch (ServiceException exception) {
                throw new ProjectException("Registration command error", exception);
            }
        return new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);}


    }

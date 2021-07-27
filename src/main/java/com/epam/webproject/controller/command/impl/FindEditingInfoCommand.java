package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class FindEditingInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String loginOrEmail = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        UserService userService = ServiceProvider.getInstance().getUserService();
        try {
            Optional<User> optionalUser = userService.findByLoginOrEmail(loginOrEmail);
            User user = optionalUser.get();

            request.getSession().setAttribute(RequestAttribute.EMAIL, user.getEmail());
            request.getSession().setAttribute(RequestAttribute.LOGIN, user.getLogin());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(RouterType.FORWARD, PagePath.EDITING_INFO_PAGE);
    }
}
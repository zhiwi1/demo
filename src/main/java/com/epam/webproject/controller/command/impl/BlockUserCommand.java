package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Deque;
import java.util.List;

public class BlockUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        UserService userService = ServiceProvider.getInstance().getUserService();
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            boolean result = userService.blockUser(login);
            Router router = new Router();
            if (result) {
                Deque<User> users=userService.showAllUsers();
                request.setAttribute(RequestAttribute.USERS,users);
                router = new Router(RouterType.REDIRECT, PagePath.SHOW_ALL_USERS_COMMAND);
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

    }
}

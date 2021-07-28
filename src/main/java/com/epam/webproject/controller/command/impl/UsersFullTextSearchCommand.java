package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;

public class UsersFullTextSearchCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        UserService service = ServiceProvider.getInstance().getUserService();
        String text = request.getParameter(RequestParameter.TEXT);
        try {
            ArrayDeque<User> arrayDeque = service.findByFullText(text);
            request.setAttribute(RequestAttribute.FULL_TEXT_SEARCH_USERS, arrayDeque);
            router = new Router(RouterType.FORWARD, PagePath.ALL_USERS_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Fulltext search error", e);
        }
        return router;
    }
}

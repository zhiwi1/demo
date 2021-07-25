package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowAllUsersCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService service = ServiceProvider.getInstance().getUserService();
        try {
            List<User> users=service.showAllUsers();
            request.setAttribute(RequestAttribute.USERS,users);
            return new Router(RouterType.FORWARD, PagePath.ALL_USERS_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Can't show tasks ", e);
        }

    }
}

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

import java.util.ArrayDeque;
import java.util.List;

public class ShowMyTasksCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TaskService service = ServiceProvider.getInstance().getTaskService();
        String login = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        try {
            ArrayDeque<Task> tasks = service.findTasksByUserLogin(login);
            request.setAttribute(RequestAttribute.TASKS, tasks);
            return new Router(RouterType.FORWARD, PagePath.USER_TASKS_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Can't show tasks ", e);
        }
    }
}

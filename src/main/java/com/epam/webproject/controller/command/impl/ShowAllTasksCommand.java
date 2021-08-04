package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.ServiceProvider;

import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Deque;
import java.util.List;

public class ShowAllTasksCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TaskService service = ServiceProvider.getInstance().getTaskService();
        try {
            Deque<Task> tasks=service.findAllTasks();
            request.setAttribute(RequestAttribute.TASKS,tasks);
            return new Router(RouterType.FORWARD, PagePath.ALL_TASKS_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Can't show tasks ", e);
        }

    }
}

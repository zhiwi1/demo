package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.ServiceProvider;

import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class ShowAllTasksCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role == null) {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
        } else {
            TaskService service = ServiceProvider.getInstance().getTaskService();
            try {

                int limit = 4;

                String pageString = Optional.ofNullable(request.getParameter(RequestParameter.KEY_PAGE))
                        .orElse("1");
                int currentPage = Integer.parseInt(pageString);

                double countOfTasks = service.countOfTasks();

                int maxPage = (int) Math.ceil(countOfTasks / limit);

                if (maxPage > 0 && maxPage < currentPage) {
                    currentPage = maxPage;
                }

                Deque<Task> tasks = service.findAllTasksWithLimit((currentPage - 1) * (limit), limit);

                request.setAttribute(RequestAttribute.TASKS, tasks);
                request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPage);
                request.setAttribute(RequestAttribute.MAX_PAGE, maxPage);

                router = new Router(RouterType.FORWARD, PagePath.ALL_TASKS_PAGE);
            } catch (ServiceException e) {
                throw new CommandException("Can't show tasks ", e);
            }

        }
        return router;
    }
}

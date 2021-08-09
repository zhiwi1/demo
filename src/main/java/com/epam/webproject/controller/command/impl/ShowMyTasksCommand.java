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
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class ShowMyTasksCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TaskService service = ServiceProvider.getInstance().getTaskService();
        String login = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
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

            Deque<Task> tasks = service.findTasksByUserLogin(login, (currentPage - 1) * (limit), limit);
            request.setAttribute(RequestAttribute.TASKS, tasks);
            request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPage);
            request.setAttribute(RequestAttribute.MAX_PAGE, maxPage);

            return new Router(RouterType.FORWARD, PagePath.USER_TASKS_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Can't show tasks ", e);
        }
    }
}

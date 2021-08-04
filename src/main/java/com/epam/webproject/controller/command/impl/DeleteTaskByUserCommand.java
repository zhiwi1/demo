package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteTaskByUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String title = request.getParameter(RequestParameter.TITLE);
        TaskService service = ServiceProvider.getInstance().getTaskService();
        try {
            boolean isDeleted = service.deleteTask(title);
            Router router = new Router();
            if (isDeleted) {
                router = new Router(RouterType.REDIRECT, PagePath.SHOW_MY_TASKS_COMMAND_PAGE);
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Delete command error", e);
        }

    }
}

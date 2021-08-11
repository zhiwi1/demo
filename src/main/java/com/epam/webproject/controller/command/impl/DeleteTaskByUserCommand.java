package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;


public class DeleteTaskByUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            String title = request.getParameter(RequestParameter.TITLE);
            TaskService service = ServiceProvider.getInstance().getTaskService();
            try {
                boolean isDeleted = service.deleteTask(title);
                if (isDeleted) {
                    router = new Router(RouterType.REDIRECT, PagePath.SHOW_MY_TASKS_COMMAND_PAGE);
                } else {
                    router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
                }
                return router;
            } catch (ServiceException e) {
                throw new CommandException("eleteTaskByUserCommand command error"+e.getMessage(), e);
            }
        } else {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);


        }
        return router;
    }
}

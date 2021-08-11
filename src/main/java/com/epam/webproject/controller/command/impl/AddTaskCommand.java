package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;

import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class AddTaskCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            String title = request.getParameter(RequestParameter.TITLE);
            String text = request.getParameter(RequestParameter.TEXT);
            String complexity = request.getParameter(RequestParameter.COMPLEXITY);
            String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
            TaskService postService = ServiceProvider.getInstance().getTaskService();
            try {
                Feedback feedback = postService.createTask(title, text, new Date(), loginOfUser, complexity);
                switch (feedback) {
                    case SUCCESS: {
                        router = new Router(RouterType.REDIRECT, PagePath.GO_TO_HOME_PAGE);
                        break;
                    }
                    case CHECK_DATA:
                    case DATA_EXISTS: {
                        request.getSession().setAttribute(RequestAttribute.MESSAGE, feedback);
                        router = new Router(RouterType.REDIRECT, PagePath.GO_TO_ADD_TASK_PAGE_COMMAND);
                        break;
                    }
                    default: {
                        router = new Router(RouterType.REDIRECT, PagePath.DEFAULT_COMMAND);
                        break;
                    }
                }

            } catch (ServiceException e) {
                throw new CommandException("AddTaskCommand command error"+e.getMessage(), e);
            }
        } else {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);

        }return router;}
}
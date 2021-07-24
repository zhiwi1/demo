package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;

import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class AddTaskCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String title = request.getParameter(RequestParameter.TITLE);
        String text = request.getParameter(RequestParameter.TEXT);
        String complexity = request.getParameter(RequestParameter.COMPLEXITY);
//        String createdAt = request.getParameter(RequestParameter.CREATED_AT);
        String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        TaskService postService = ServiceProvider.getInstance().getTaskService();
        try {
            Feedback feedback = postService.createTask(title, text, new Date(), loginOfUser, complexity);
            Router router = new Router();
            switch (feedback) {
                case SUCCESS: {
                    //todo messages
                    router = new Router(RouterType.FORWARD, PagePath.HOME_PAGE);
                    break;
                }
                case DATABASE_EXCEPTION: {
                    router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
                    break;
                }
                case CHECK_DATA:
                case LOGIN_OR_EMAIL_EXISTS: {
                    request.setAttribute(RequestAttribute.MESSAGE, feedback);
                    //   request.getSession().setAttribute(RequestAttribute.PREV_REQUEST, PagePath.REGISTRATION_PAGE);
                    router = new Router(RouterType.FORWARD, PagePath.REGISTRATION_PAGE);
                    break;
                }
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Registration command error", e);
        }
    }
}
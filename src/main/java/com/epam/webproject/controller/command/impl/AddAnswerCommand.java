package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

public class AddAnswerCommand implements Command {
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            String text = request.getParameter(RequestParameter.ANSWER);
            String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
            String titleOfTask = request.getParameter(RequestParameter.TITLE);
            AnswerService answerService = ServiceProvider.getInstance().getAnswerService();

            try {
                boolean isCreated = answerService.createAnswer(text, loginOfUser, titleOfTask);
                if (isCreated) {
                    router = new Router(RouterType.REDIRECT, PagePath.OPEN_TASK_PAGE_COMMAND + titleOfTask);
                } else {
                    router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
                }
                return router;

            } catch (ServiceException e) {
                throw new CommandException("AddAnswerCommand command error" + e.getMessage(), e);
            }
        } else {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);


        }
        return router;
    }
}

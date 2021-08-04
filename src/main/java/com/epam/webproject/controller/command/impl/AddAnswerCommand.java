package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Comment;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.CommentService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;
import java.util.Optional;

public class AddAnswerCommand implements Command {
    public Router execute(HttpServletRequest request) throws CommandException {

        String text = request.getParameter(RequestParameter.ANSWER);
        String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        String titleOfTask = request.getParameter(RequestParameter.TITLE);
        AnswerService answerService = ServiceProvider.getInstance().getAnswerService();

        try {

            boolean isCreated = answerService.createAnswer(text, loginOfUser, titleOfTask);
            Router router = new Router();
            if (isCreated) {
                TaskService taskService = ServiceProvider.getInstance().getTaskService();
                router = new Router(RouterType.REDIRECT, PagePath.OPEN_TASK_PAGE_COMMAND + titleOfTask);
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
            }
            return router;

        } catch (ServiceException e) {
            throw new CommandException("Add Answer command error", e);
        }

    }
}

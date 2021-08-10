package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class MarkIncorrectAnswerCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role == null) {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
        } else {
        AnswerService answerService = ServiceProvider.getInstance().getAnswerService();
        String titleOfTask = request.getParameter(RequestParameter.TITLE);
        String answerId = request.getParameter(RequestParameter.ANSWER_ID);
        String taskId=request.getParameter(RequestParameter.TASK_ID);
        TaskService service=ServiceProvider.getInstance().getTaskService();
        try {
            Optional<String> taskTitle=service.findTitleById(Long.parseLong(taskId));
            boolean result = answerService.markIncorrect(Long.parseLong(answerId));
            if (result) {
                router = new Router(RouterType.REDIRECT, PagePath.FIND_ANSWERS_OF_TASK_COMMAND,"&title=",taskTitle.get());
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

    }   return router;}
}

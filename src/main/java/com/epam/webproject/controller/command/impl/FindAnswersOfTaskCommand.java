package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindAnswersOfTaskCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String title = request.getParameter(RequestParameter.TITLE);
        AnswerService service = ServiceProvider.getInstance().getAnswerService();
        try {
            Deque<Answer> answers = service.findAnswersByTitle(title);
            request.setAttribute(RequestAttribute.ANSWERS, answers);
            return new Router(RouterType.FORWARD, PagePath.ANSWERS_OF_TASK_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

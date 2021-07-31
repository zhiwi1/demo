package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;

public class FindAnswersOfTaskCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String title = request.getParameter(RequestParameter.TITLE);
        AnswerService service = ServiceProvider.getInstance().getAnswerService();
        try {
            ArrayDeque<Answer> answers = service.findAnswersByTitle(title);
            System.out.println(answers);
            request.setAttribute(RequestAttribute.ANSWERS, answers);
            return new Router(RouterType.FORWARD, PagePath.ANSWERS_OF_TASK_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

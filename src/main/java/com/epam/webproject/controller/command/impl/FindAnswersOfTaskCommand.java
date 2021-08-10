package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class FindAnswersOfTaskCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role == null) {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
        } else {
            String title = request.getParameter(RequestParameter.TITLE);
            AnswerService service = ServiceProvider.getInstance().getAnswerService();
            try {
                int limit = 4;

                String pageString = Optional.ofNullable(request.getParameter(RequestParameter.KEY_PAGE))
                        .orElse("1");
                int currentPage = Integer.parseInt(pageString);

                double countOfTasks = service.countOfAnswers(title);

                int maxPage = (int) Math.ceil(countOfTasks / limit);

                if (maxPage > 0 && maxPage < currentPage) {
                    currentPage = maxPage;
                }
                Deque<Answer> answers = service.findAnswersByTitle(title, (currentPage - 1) * (limit), limit);
                request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPage);
                request.setAttribute(RequestAttribute.MAX_PAGE, maxPage);
                request.setAttribute(RequestAttribute.ANSWERS, answers);
                router = new Router(RouterType.FORWARD, PagePath.ANSWERS_OF_TASK_PAGE);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return router;
    }
}

package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.*;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.CommentService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.util.RegexpPropertyUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

public class OpenTaskPageCommand implements Command {
    private static final int LIMIT = 8;
    private static final String FIRST_PAGE = "1";
    private static final String REGEXP_PROP_COMMENT = "regexp.comment";
    private static final String REGEXP_PROP_ANSWER = "regexp.answer";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {

            String title = request.getParameter(RequestParameter.TITLE);
            TaskService taskService = ServiceProvider.getInstance().getTaskService();
            AnswerService answerService = ServiceProvider.getInstance().getAnswerService();
            CommentService commentService = ServiceProvider.getInstance().getCommentService();

            try {
                RegexpPropertyUtil propertyUtil = RegexpPropertyUtil.getInstance();
                final String REGEXP_ANSWER = propertyUtil.getProperty(REGEXP_PROP_ANSWER);
                final String REGEXP_COMMENT = propertyUtil.getProperty(REGEXP_PROP_COMMENT);
                request.setAttribute(RequestAttribute.REGEXP_ANSWER, REGEXP_ANSWER);
                request.setAttribute(RequestAttribute.REGEXP_COMMENT, REGEXP_COMMENT);
                Optional<Task> optionalTask = taskService.findTaskByTitle(title);
                if (optionalTask.isPresent()) {
                    String pageStringComment = Optional.ofNullable(request.getParameter(RequestParameter.COMMENT_PAGE))
                            .orElse(FIRST_PAGE);
                    int currentPageComment = Integer.parseInt(pageStringComment);
                    double countOfComments = commentService.countOfComments(title);
                    int maxPageComment = (int) Math.ceil(countOfComments / LIMIT);
                    if (maxPageComment > 0 && maxPageComment < currentPageComment) {
                        currentPageComment = maxPageComment;
                    }
                    String pageStringAnswer = Optional.ofNullable(request.getParameter(RequestParameter.KEY_PAGE))
                            .orElse(FIRST_PAGE);
                    int currentPageAnswer = Integer.parseInt(pageStringAnswer);
                    double countOfAnswers = answerService.countOfAnswers(title);
                    int maxPageAnswer = (int) Math.ceil(countOfAnswers / LIMIT);
                    if (maxPageAnswer > 0 && maxPageAnswer < currentPageAnswer) {
                        currentPageAnswer = maxPageAnswer;
                    }


                    Task task = optionalTask.get();
                    request.setAttribute(RequestAttribute.TASK, task);
                    Deque<Comment> deque = commentService.findCommentsByTitle(title, (currentPageComment - 1) * (LIMIT), LIMIT);
                    request.setAttribute(RequestAttribute.COMMENTS, deque);
                    Deque<Answer> dequeOfAnswers = answerService.findAnswersByTitle(title, (currentPageAnswer - 1) * (LIMIT), LIMIT);
                    request.setAttribute(RequestAttribute.ANSWERS, dequeOfAnswers);
                    request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPageAnswer);
                    request.setAttribute(RequestAttribute.MAX_PAGE, maxPageAnswer);

                    request.setAttribute(RequestAttribute.COMMENT_CURRENT_PAGE, currentPageComment);
                    request.setAttribute(RequestAttribute.COMMENT_MAX_PAGE, maxPageComment);
                    router = new Router(RouterType.FORWARD, PagePath.TASK_PAGE);

                } else {
                    router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
                }
            } catch (ServiceException e) {
                throw new CommandException("OpenTaskPageCommand command error: " + e.getMessage(), e);
            }
        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
        }
        return router;
    }
}

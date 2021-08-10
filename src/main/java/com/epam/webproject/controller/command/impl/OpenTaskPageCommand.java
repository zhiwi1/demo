package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.*;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.CommentService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.*;
import java.util.stream.Collectors;

public class OpenTaskPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role == null) {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
        } else {
        String title = request.getParameter(RequestParameter.TITLE);
        TaskService taskService = ServiceProvider.getInstance().getTaskService();
        AnswerService answerService = ServiceProvider.getInstance().getAnswerService();
        CommentService commentService = ServiceProvider.getInstance().getCommentService();

        try {
            Optional<Task> optionalTask = taskService.findTaskByTitle(title);
            if (optionalTask.isPresent()) {
                int limit = 8;
                String pageStringComment = Optional.ofNullable(request.getParameter(RequestParameter.COMMENT_PAGE))
                        .orElse("1");
                int currentPageComment = Integer.parseInt(pageStringComment);
                double countOfComments = commentService.countOfComments(title);
                int maxPageComment = (int) Math.ceil(countOfComments / limit);
                if (maxPageComment > 0 && maxPageComment < currentPageComment) {
                    currentPageComment = maxPageComment;
                }
                String pageStringAnswer = Optional.ofNullable(request.getParameter(RequestParameter.KEY_PAGE))
                        .orElse("1");
                int currentPageAnswer = Integer.parseInt(pageStringAnswer);
                double countOfAnswers = answerService.countOfAnswers(title);
                int maxPageAnswer = (int) Math.ceil(countOfAnswers / limit);
                if (maxPageAnswer > 0 && maxPageAnswer < currentPageAnswer) {
                    currentPageAnswer = maxPageAnswer;
                }


                Task task = optionalTask.get();
                request.setAttribute(RequestAttribute.TASK, task);
                Deque<Comment> deque = commentService.findCommentsByTitle(title, (currentPageComment - 1) * (limit), limit);
                request.setAttribute(RequestAttribute.COMMENTS, deque);
                Deque<Answer> dequeOfAnswers = answerService.findAnswersByTitle(title,(currentPageAnswer - 1) * (limit), limit);
                request.setAttribute(RequestAttribute.ANSWERS, dequeOfAnswers);
                request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPageAnswer);
                request.setAttribute(RequestAttribute.MAX_PAGE, maxPageAnswer);

                request.setAttribute(RequestAttribute.COMMENT_CURRENT_PAGE,currentPageComment);
                request.setAttribute(RequestAttribute.COMMENT_MAX_PAGE,maxPageComment);
                router = new Router(RouterType.FORWARD, PagePath.TASK_PAGE);

            } else router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

    }  return router;}
}

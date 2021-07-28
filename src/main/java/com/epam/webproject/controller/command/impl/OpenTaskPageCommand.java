package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Comment;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.CommentService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OpenTaskPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String title = request.getParameter(RequestParameter.TITLE);
        TaskService taskService = ServiceProvider.getInstance().getTaskService();
        AnswerService answerService = ServiceProvider.getInstance().getAnswerService();
        CommentService commentService = ServiceProvider.getInstance().getCommentService();

        try {
            Optional<Task> optionalTask = taskService.findTaskByTitle(title);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                request.setAttribute(RequestAttribute.TASK, task);
                ArrayDeque<Comment> deque = commentService.findCommentsByTitle(title);
                request.setAttribute(RequestAttribute.COMMENTS, deque);
                List<Answer> dequeOfAnswers = new ArrayList<>(answerService.findAnswersByTitle(title));
                request.setAttribute(RequestAttribute.ANSWERS, dequeOfAnswers);
                router = new Router(RouterType.FORWARD, PagePath.TASK_PAGE);
            } else router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

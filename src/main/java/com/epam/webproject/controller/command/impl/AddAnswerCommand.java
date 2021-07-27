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
        Router router = new Router();
        String text = request.getParameter(RequestParameter.ANSWER);
        String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        String titleOfTask = request.getParameter(RequestParameter.TITLE);
        AnswerService answerService = ServiceProvider.getInstance().getAnswerService();
        CommentService commentService = ServiceProvider.getInstance().getCommentService();
        try {
            boolean isCreated = answerService.createAnswer(text, loginOfUser, titleOfTask);
            if (isCreated) {
                TaskService taskService = ServiceProvider.getInstance().getTaskService();
                Optional<Task> optionalTask = taskService.findTaskByTitle(titleOfTask);
                if (optionalTask.isPresent()) {
                    Task task = optionalTask.get();
                    request.setAttribute(RequestAttribute.TASK, task);
                    ArrayDeque<Comment> deque = commentService.findCommentsByTitle(titleOfTask);
                    request.setAttribute(RequestAttribute.COMMENTS, deque);
                    ArrayDeque<Answer> answerArrayDeque = answerService.findAnswersByTitle(titleOfTask);
                    request.setAttribute(RequestAttribute.ANSWERS, answerArrayDeque);
                    router = new Router(RouterType.FORWARD, PagePath.TASK_PAGE);
                } else {
                    router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
                }
                return router;
            }
        } catch (ServiceException e) {
            throw new CommandException("Add Answer command error", e);
        }
        return router;
    }
}

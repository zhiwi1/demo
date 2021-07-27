package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Comment;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Optional;

public class AddCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String text = request.getParameter(RequestParameter.COMMENT);
        String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        String titleOfTask = request.getParameter(RequestParameter.TITLE);
        CommentService commentService = ServiceProvider.getInstance().getCommentService();
        AnswerService answerService=ServiceProvider.getInstance().getAnswerService();
        try {
            boolean isCreated = commentService.createComment(text, loginOfUser, titleOfTask);
            if (isCreated) {
                TaskService taskService = ServiceProvider.getInstance().getTaskService();
                Optional<Task> optionalTask = taskService.findTaskByTitle(titleOfTask);
                if (optionalTask.isPresent()) {
                    Task task = optionalTask.get();
                    request.setAttribute(RequestAttribute.TASK, task);
                    ArrayDeque<Comment> deque = commentService.findCommentsByTitle(titleOfTask);
                    request.setAttribute(RequestAttribute.COMMENTS, deque);
                    ArrayDeque<Answer> answerDeque = answerService.findAnswersByTitle(titleOfTask);
                    request.setAttribute(RequestAttribute.ANSWERS, answerDeque);
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

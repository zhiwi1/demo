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

        String text = request.getParameter(RequestParameter.COMMENT);
        String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        String titleOfTask = request.getParameter(RequestParameter.TITLE);
        CommentService commentService = ServiceProvider.getInstance().getCommentService();
        try {
            boolean isCreated = commentService.createComment(text, loginOfUser, titleOfTask);
            Router router = new Router();
            if (isCreated) {
                router = new Router(RouterType.REDIRECT, PagePath.OPEN_TASK_PAGE_COMMAND+titleOfTask);
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
            }
            return router;

        } catch (ServiceException e) {
            throw new CommandException("Add Answer command error", e);
        }

    }


}

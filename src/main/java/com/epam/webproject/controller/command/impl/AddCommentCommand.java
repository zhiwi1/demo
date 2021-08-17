package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.*;
import jakarta.servlet.http.HttpServletRequest;

public class AddCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            String text = request.getParameter(RequestParameter.COMMENT);
            String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
            String titleOfTask = request.getParameter(RequestParameter.TITLE);
            CommentService commentService = ServiceProvider.getInstance().getCommentService();
            try {
                commentService.createComment(text, loginOfUser, titleOfTask);
                router = new Router(RouterType.REDIRECT, PagePath.OPEN_TASK_PAGE_COMMAND + titleOfTask);
            } catch (ServiceException e) {
                throw new CommandException("Add AddCommentCommand error " + e.getMessage(), e);
            }

        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);

        }
        return router;
    }

}

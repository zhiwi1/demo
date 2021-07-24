package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.service.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class AddCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

            String text = request.getParameter(RequestParameter.TEXT);
            String loginOfUser = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
            String titleOfTask = (String) request.getSession().getAttribute(RequestAttribute.TITLE);
        CommentService service=ServiceProvider.getInstance().getCommentService();

             try {
                boolean isCreated = service.createComment(text, loginOfUser, titleOfTask);
                Router router = new Router();
                if (isCreated) {
                    router = new Router(RouterType.FORWARD, PagePath.HOME_PAGE);
                } else router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
                return router;
            } catch (ServiceException e) {
                throw new CommandException("Add comment command error", e);
            }
        }
}

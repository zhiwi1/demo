package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToLoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
  //      request.getSession().setAttribute(RequestAttribute.PREV_REQUEST, PagePath.LOGIN_PAGE);
        return new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
    }
}

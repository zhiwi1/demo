package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
       // request.getSession().setAttribute(RequestAttribute.PREV_REQUEST, PagePath.REGISTRATION_PAGE);
        return new Router(RouterType.FORWARD, PagePath.REGISTRATION_PAGE);
    }
}

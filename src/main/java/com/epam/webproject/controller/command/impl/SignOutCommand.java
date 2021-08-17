package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(RequestAttribute.PREV_REQUEST, PagePath.HOME_PAGE);
        request.getSession().removeAttribute(RequestAttribute.ROLE);
        request.getSession().removeAttribute(RequestAttribute.LOGIN);
        return new Router(RouterType.REDIRECT, PagePath.GO_TO_LOGIN_PAGE);
    }
}


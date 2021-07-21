package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {


    @Override
    public Router execute(HttpServletRequest req) {
        HttpSession SESSION = req.getSession();
        String LOCALE = req.getParameter(RequestParameter.LOCALE);
        String PREVIOUS_REQUEST = (String) SESSION.getAttribute(RequestAttribute.PREV_REQUEST);
        SESSION.setAttribute(RequestAttribute.LOCALE, LOCALE);
        Router router = new Router(RouterType.REDIRECT, PREVIOUS_REQUEST);
        return router;
    }
}


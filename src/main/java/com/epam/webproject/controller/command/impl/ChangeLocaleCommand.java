package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLocaleCommand implements Command {

    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String locale = req.getParameter(RequestParameter.LOCALE);
        String previousRequest = req.getParameter(RequestAttribute.PREV_REQUEST);
        session.setAttribute(RequestAttribute.LOCALE, locale);
        Router router = new Router(RouterType.REDIRECT, previousRequest);
        return router;
    }
}


package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLocaleCommand implements Command {

public static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest req) {

        HttpSession SESSION = req.getSession();
        String LOCALE = req.getParameter(RequestParameter.LOCALE);
        String PREVIOUS_REQUEST = (String) SESSION.getAttribute(RequestAttribute.PREV_REQUEST);
        logger.info(PREVIOUS_REQUEST);
        SESSION.setAttribute(RequestAttribute.LOCALE, LOCALE);

        Router router = new Router(RouterType.REDIRECT, PREVIOUS_REQUEST);
        return router;
    }
}


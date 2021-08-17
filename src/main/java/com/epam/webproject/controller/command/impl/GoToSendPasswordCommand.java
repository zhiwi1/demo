package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.util.RegexpPropertyUtil;
import jakarta.servlet.http.HttpServletRequest;

public class GoToSendPasswordCommand implements Command {
    private static final String REGEXP_PROP_EMAIL = "regexp.email";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();
        final String REGEXP_EMAIL = regexpPropertyUtil.getProperty(REGEXP_PROP_EMAIL);
        request.setAttribute(RequestAttribute.REGEXP_EMAIL, REGEXP_EMAIL);
        return new Router(RouterType.FORWARD, PagePath.NEW_PASSWORD_PAGE);
    }
}

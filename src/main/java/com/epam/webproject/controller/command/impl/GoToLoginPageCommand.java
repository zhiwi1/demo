package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.localization.Localization;
import com.epam.webproject.localization.LocalizationKey;
import jakarta.servlet.http.HttpServletRequest;

public class GoToLoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        //for localization
        String flag = request.getParameter(RequestParameter.ERROR_MESSAGE);
        if (flag != null && flag.equals("true")) {
            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
            Localization localization = Localization.valueOf(locale.toUpperCase());
            String errorMessage = localization.getText(LocalizationKey.LOGIN_ERROR_MESSAGE);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
        }
        return new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
    }
}

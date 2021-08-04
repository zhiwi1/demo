package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.util.localization.LocalizationType;
import com.epam.webproject.util.localization.LocalizationKey;
import jakarta.servlet.http.HttpServletRequest;

public class GoToLoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        //for localization
        String flagOfError = request.getParameter(RequestParameter.ERROR_MESSAGE);
        if (flagOfError != null && flagOfError.equals("true")) {
            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);

            LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
            String errorMessage = localization.getText(LocalizationKey.LOGIN_ERROR_MESSAGE);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);

        }
        String flagOfEmail = request.getParameter(RequestParameter.EMAIL_MESSAGE);
        if (flagOfEmail != null && flagOfEmail.equals("true")) {
            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
            LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
            String emailMessage = localization.getText(LocalizationKey.EMAIL_MESSAGE);
            request.setAttribute(RequestAttribute.EMAIL_MESSAGE, emailMessage);

        }
        return new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
    }
}

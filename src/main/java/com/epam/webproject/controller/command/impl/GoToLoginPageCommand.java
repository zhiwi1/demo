package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.util.RegexpPropertyUtil;
import com.epam.webproject.util.localization.LocalizationType;
import com.epam.webproject.util.localization.LocalizationKey;
import jakarta.servlet.http.HttpServletRequest;

public class GoToLoginPageCommand implements Command {
    private static final String REGEXP_PROP_PASSWORD = "regexp.password";
    private static final String REGEXP_PROP_EMAIL_OR_LOGIN = "regexp.email_or_login";
    private static final String FLAG_OF_ERROR = "true";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        //for localization
        RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

        final String REGEXP_PASSWORD = regexpPropertyUtil.getProperty(REGEXP_PROP_PASSWORD);
        final String REGEXP_EMAIL_OR_LOGIN = regexpPropertyUtil.getProperty(REGEXP_PROP_EMAIL_OR_LOGIN);

        request.setAttribute(RequestAttribute.REGEXP_PASSWORD, REGEXP_PASSWORD);
        request.setAttribute(RequestAttribute.REGEXP_EMAIL_OR_LOGIN, REGEXP_EMAIL_OR_LOGIN);


        String flagOfError = request.getParameter(RequestParameter.ERROR_MESSAGE);
        if (flagOfError != null && flagOfError.equals(FLAG_OF_ERROR)) {
            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
            LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
            String errorMessage = localization.getText(LocalizationKey.LOGIN_ERROR_MESSAGE);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);

        }
        String flagOfEmail = request.getParameter(RequestParameter.EMAIL_MESSAGE);
        if (flagOfEmail != null && flagOfEmail.equals(FLAG_OF_ERROR)) {
            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
            LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
            String emailMessage = localization.getText(LocalizationKey.EMAIL_MESSAGE);
            request.setAttribute(RequestAttribute.EMAIL_MESSAGE, emailMessage);

        }
        return new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
    }
}

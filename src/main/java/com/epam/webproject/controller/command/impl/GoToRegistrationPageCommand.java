package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.util.RegexpPropertyUtil;
import com.epam.webproject.util.localization.LocalizationKey;
import com.epam.webproject.util.localization.LocalizationType;
import com.epam.webproject.model.service.Feedback;
import jakarta.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {
    private static final String REGEXP_PROP_PASSWORD = "regexp.password";
    private static final String REGEXP_PROP_EMAIL_OR_LOGIN = "regexp.email_or_login";
    private static final String REGEXP_PROP_EMAIL = "regexp.email";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();
        final String REGEXP_PASSWORD = regexpPropertyUtil.getProperty(REGEXP_PROP_PASSWORD);
        final String REGEXP_LOGIN_OR_EMAIL = regexpPropertyUtil.getProperty(REGEXP_PROP_EMAIL_OR_LOGIN);
        final String REGEXP_EMAIL = regexpPropertyUtil.getProperty(REGEXP_PROP_EMAIL);
        request.setAttribute(RequestAttribute.REGEXP_PASSWORD, REGEXP_PASSWORD);
        request.setAttribute(RequestAttribute.REGEXP_EMAIL, REGEXP_EMAIL);
        request.setAttribute(RequestAttribute.REGEXP_EMAIL_OR_LOGIN, REGEXP_LOGIN_OR_EMAIL);
        Feedback feedback = (Feedback) request.getSession().getAttribute(RequestAttribute.MESSAGE);
        request.getSession().removeAttribute(RequestAttribute.MESSAGE);
        if (feedback != null) {

            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
            LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
            switch (feedback) {
                case CHECK_DATA: {
                    String errorMessage = localization.getText(LocalizationKey.REGISTRATION_CHECK_DATA_MESSAGE);
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
                    break;
                }
                case DATA_EXISTS: {
                    String errorMessage = localization.getText(LocalizationKey.REGISTRATION_LOGIN_OR_EMAIL_EXISTS);
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
                    break;
                }
            }


        }
        return new Router(RouterType.FORWARD, PagePath.REGISTRATION_PAGE);
    }
}

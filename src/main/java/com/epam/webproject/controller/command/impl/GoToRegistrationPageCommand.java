package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.util.localization.LocalizationKey;
import com.epam.webproject.util.localization.LocalizationType;
import static com.epam.webproject.util.localization.LocalizationKey.*;
import com.epam.webproject.model.service.Feedback;
import jakarta.servlet.http.HttpServletRequest;

public class GoToRegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String feedbackToString = request.getParameter(RequestParameter.ERROR_MESSAGE);
        if (feedbackToString != null) {
            Feedback feedback = Feedback.valueOf(feedbackToString);
            String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
            LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
            switch (feedback) {
                case CHECK_DATA: {
                    String errorMessage = localization.getText(LocalizationKey.REGISTRATION_CHECK_DATA_MESSAGE);
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
                    break;
                }
                case LOGIN_OR_EMAIL_EXISTS: {
                    String errorMessage = localization.getText(LocalizationKey.REGISTRATION_LOGIN_OR_EMAIL_EXISTS);
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
                    break;
                }
            }


        }
        return new Router(RouterType.FORWARD, PagePath.REGISTRATION_PAGE);
    }
}

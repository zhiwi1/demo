package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.util.localization.LocalizationKey;
import com.epam.webproject.util.localization.LocalizationType;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAddTaskPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Feedback feedback = (Feedback) request.getSession().getAttribute(RequestAttribute.MESSAGE);
        request.getSession().removeAttribute(RequestAttribute.MESSAGE);
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            if (feedback != null) {
                String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);
                LocalizationType localization = LocalizationType.valueOf(locale.toUpperCase());
                switch (feedback) {
                    case CHECK_DATA: {
                        String errorMessage = localization.getText(LocalizationKey.ADDTASK_CHECK);
                        request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
                        break;
                    }
                    case DATA_EXISTS: {
                        String errorMessage = localization.getText(LocalizationKey.ADDTASK_ERROR_MESSAGE);
                        request.setAttribute(RequestAttribute.ERROR_MESSAGE, errorMessage);
                        break;
                    }

                }
            }
            router = new Router(RouterType.FORWARD, PagePath.ADD_TASK_PAGE);

        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
        }
        return router;
    }
}


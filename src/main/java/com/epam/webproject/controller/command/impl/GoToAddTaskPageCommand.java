package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.util.RegexpPropertyUtil;
import com.epam.webproject.util.localization.LocalizationKey;
import com.epam.webproject.util.localization.LocalizationType;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAddTaskPageCommand implements Command {
    private static final String REGEXP_PROP_TITLE = "regexp.title";
    private static final String REGEXP_PROP_TASK = "regexp.task";
    private static final String REGEXP_PROP_COMPLEXITY = "regexp.complexity";

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
            RegexpPropertyUtil propertyUtil = RegexpPropertyUtil.getInstance();
            final String REGEXP_TITLE = propertyUtil.getProperty(REGEXP_PROP_TITLE);
            final String REGEXP_TASK = propertyUtil.getProperty(REGEXP_PROP_TASK);
            final String REGEXP_COMPLEXITY=propertyUtil.getProperty(REGEXP_PROP_COMPLEXITY);
            request.setAttribute(RequestAttribute.REGEXP_TITLE, REGEXP_TITLE);
            request.setAttribute(RequestAttribute.REGEXP_TASK, REGEXP_TASK);
            request.setAttribute(RequestAttribute.REGEXP_COMPLEXITY,REGEXP_COMPLEXITY);
            router = new Router(RouterType.FORWARD, PagePath.ADD_TASK_PAGE);

        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
        }
        return router;
    }
}


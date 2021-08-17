package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import com.epam.webproject.util.RegexpPropertyUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class FindEditingInfoCommand implements Command {
    private static final String REGEXP_PROP_EMAIL_OR_LOGIN = "regexp.email_or_login";
    private static final String REGEXP_PROP_EMAIL = "regexp.email";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            String loginOrEmail = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
            UserService userService = ServiceProvider.getInstance().getUserService();
            try {
                Optional<User> optionalUser = userService.findByLoginOrEmail(loginOrEmail);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    request.getSession().setAttribute(RequestAttribute.EMAIL, user.getEmail());
                    request.getSession().setAttribute(RequestAttribute.LOGIN, user.getLogin());
                } else {
                    router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
                }
            } catch (ServiceException e) {
                throw new CommandException("FindEditingInfoCommand command error: " + e.getMessage(), e);
            }
            RegexpPropertyUtil regexpPropertyUtil=RegexpPropertyUtil.getInstance();
            final String REGEXP_LOGIN_OR_EMAIL = regexpPropertyUtil.getProperty(REGEXP_PROP_EMAIL_OR_LOGIN);
            final String REGEXP_EMAIL = regexpPropertyUtil.getProperty(REGEXP_PROP_EMAIL);
            request.setAttribute(RequestAttribute.REGEXP_EMAIL, REGEXP_EMAIL);
            request.setAttribute(RequestAttribute.REGEXP_EMAIL_OR_LOGIN, REGEXP_LOGIN_OR_EMAIL);
            router = new Router(RouterType.FORWARD, PagePath.EDITING_INFO_PAGE);
        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
        }
        return router;
    }

}


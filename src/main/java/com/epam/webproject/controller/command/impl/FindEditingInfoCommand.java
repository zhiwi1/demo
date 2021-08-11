package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class FindEditingInfoCommand implements Command {

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
            router = new Router(RouterType.FORWARD, PagePath.EDITING_INFO_PAGE);
        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
        }
        return router;
    }

}


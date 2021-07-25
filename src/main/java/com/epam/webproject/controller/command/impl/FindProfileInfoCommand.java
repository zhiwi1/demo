package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class FindProfileInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String login = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        UserService service = ServiceProvider.getInstance().getUserService();
        try {
            Optional<User> optionalUser = service.findByLoginOrEmail(login);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(RequestAttribute.USER, user);
                router = new Router(RouterType.FORWARD, PagePath.PROFILE_PAGE);
            } else {
                router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Registration command error", e);
        }
        return router;
    }
}

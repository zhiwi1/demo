package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class FindProfileInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) { String login = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
            UserService service = ServiceProvider.getInstance().getUserService();
            try {

                RatesType ratesType = service.calculateRatesOfSolve(login);
                service.setRates(login, ratesType);
                Optional<User> optionalUser = service.findByLoginOrEmail(login);
                if (optionalUser.isPresent()) {
                    request.setAttribute(RequestAttribute.USER, optionalUser.get());
                    router = new Router(RouterType.FORWARD, PagePath.PROFILE_PAGE);
                } else {
                    router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
                }
            } catch (ServiceException e) {
                throw new CommandException("FindProfileInfoCommand command error: " + e.getMessage(), e);
            }
        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);

        }return router;
    }
}
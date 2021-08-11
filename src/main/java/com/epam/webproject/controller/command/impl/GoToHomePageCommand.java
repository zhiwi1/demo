package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.model.entity.Role;
import jakarta.servlet.http.HttpServletRequest;

public class GoToHomePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        request.getSession().removeAttribute(RequestAttribute.BLOCK_MESSAGE);
        if (role != null) {
            router = new Router(RouterType.FORWARD, PagePath.HOME_PAGE);

        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);
        }
        return router;
    }
}

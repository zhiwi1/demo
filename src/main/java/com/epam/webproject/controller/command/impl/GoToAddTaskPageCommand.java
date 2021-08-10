package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.model.entity.Role;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAddTaskPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role == null) {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
        } else {
            router= new Router(RouterType.FORWARD, PagePath.ADD_TASK_PAGE);
        }
        return router;
    }}


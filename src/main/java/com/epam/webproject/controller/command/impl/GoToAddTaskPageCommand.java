package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.controller.command.RouterType;
import com.epam.webproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToAddTaskPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(RouterType.FORWARD, PagePath.ADD_TASK_PAGE);
    }
}


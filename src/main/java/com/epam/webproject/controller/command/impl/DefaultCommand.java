package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.controller.command.RouterType;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest req) {
        return new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
    }
}
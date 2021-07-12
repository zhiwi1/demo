package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.controller.command.RouterType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstJSP implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(RouterType.FORWARD,PagePath.first_page);
    }
}
package com.epam.webproject.controller.command;

import com.epam.webproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}

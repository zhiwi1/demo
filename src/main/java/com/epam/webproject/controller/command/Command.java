package com.epam.webproject.controller.command;

import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ProjectException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}

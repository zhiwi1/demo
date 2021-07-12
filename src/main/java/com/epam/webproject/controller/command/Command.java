package com.epam.webproject.controller.command;

import com.epam.webproject.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    Router execute(HttpServletRequest request, HttpServletResponse response) ;
}

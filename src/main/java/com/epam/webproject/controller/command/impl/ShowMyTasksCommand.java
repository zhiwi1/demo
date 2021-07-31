package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class ShowMyTasksCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TaskService taskService=ServiceProvider.getInstance().getTaskService();

        return null;
    }
}

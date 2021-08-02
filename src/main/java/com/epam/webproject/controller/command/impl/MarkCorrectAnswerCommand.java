package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.Command;
import com.epam.webproject.controller.command.Router;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

public class MarkCorrectAnswerCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TaskService service= ServiceProvider.getInstance().getTaskService();
        service.
                //todo do admin mark correct and like// 2 days
        //2 days fix bags
        //front //3 days
        //test //4 days
        //m0ck //
        //javadoc //day
        //case//2 days
        //teory//all this 7 days min
        //3 weeks
        return null;
    }
}

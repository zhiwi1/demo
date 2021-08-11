package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class MarkCorrectAnswerCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            AnswerService answerService = ServiceProvider.getInstance().getAnswerService();

            String answerId = request.getParameter(RequestParameter.ANSWER_ID);
            String taskId = request.getParameter(RequestParameter.TASK_ID);
            TaskService service = ServiceProvider.getInstance().getTaskService();

            try {
                Optional<String> taskTitle = service.findTitleById(Long.parseLong(taskId));
                boolean result = answerService.markCorrect(Long.parseLong(answerId));
                if (result&&taskTitle.isPresent()) {
                    router = new Router(RouterType.REDIRECT, PagePath.FIND_ANSWERS_OF_TASK_COMMAND, PagePath.PREPARATION_FOR_PARAM_TITLE, taskTitle.get());
                } else {
                    router = new Router(RouterType.REDIRECT, PagePath.DEFAULT_COMMAND);
                }

            } catch (ServiceException e) {
                throw new CommandException("MarkCorrectAnswerCommand command error: " + e.getMessage(), e);
            }

        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);

        }
        return router;
    }
}

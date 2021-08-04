package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.List;

public class LikeAnswerCommand implements Command {
    private static final Logger logger=LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        AnswerService answerService = ServiceProvider.getInstance().getAnswerService();
        long answer_id = Long.parseLong( request.getParameter(RequestAttribute.ANSWER_ID));



        TaskService taskService = ServiceProvider.getInstance().getTaskService();
        logger.debug(answer_id);

        try {
            Deque<Task> tasks=taskService.findAllTasks();
            request.setAttribute(RequestAttribute.TASKS,tasks);
            boolean isLike = answerService.likeOrUnlike(answer_id, true);
            if (isLike) {
                router = new Router(RouterType.FORWARD, PagePath.TASK_PAGE);

            } else {
                router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Like answer command error", e);
        }
        return router;
    }
}

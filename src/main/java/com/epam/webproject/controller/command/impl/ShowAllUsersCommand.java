package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class ShowAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role == null) {
            router = new Router(RouterType.FORWARD, PagePath.ERROR_PAGE);
        } else {
            UserService service = ServiceProvider.getInstance().getUserService();
            try {

                int limit = 3;

                String pageString = Optional.ofNullable(request.getParameter(RequestParameter.KEY_PAGE))
                        .orElse("1");
                int currentPage = Integer.parseInt(pageString);

                double countOfTasks = service.countOfUsers();

                int maxPage = (int) Math.ceil(countOfTasks / limit);

                if (maxPage > 0 && maxPage < currentPage) {
                    currentPage = maxPage;
                }

                Deque<User> users = service.findAllUsersWithLimit((currentPage - 1) * (limit), limit);


                request.setAttribute(RequestAttribute.USERS, users);
                request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPage);

                request.setAttribute(RequestAttribute.MAX_PAGE, maxPage);

                router = new Router(RouterType.FORWARD, PagePath.ALL_USERS_PAGE);
            } catch (ServiceException e) {
                throw new CommandException("Can't show USERS ", e);
            }

        }
        return router;
    }
}

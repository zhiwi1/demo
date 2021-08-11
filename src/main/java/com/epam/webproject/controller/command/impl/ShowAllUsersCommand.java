package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Deque;
import java.util.Optional;

public class ShowAllUsersCommand implements Command {
    private static final int LIMIT = 4;
    private static final String FIRST_PAGE = "1";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Role role = (Role) request.getSession().getAttribute(RequestAttribute.ROLE);
        if (role != null) {
            UserService service = ServiceProvider.getInstance().getUserService();
            try {
                String pageString = Optional.ofNullable(request.getParameter(RequestParameter.KEY_PAGE))
                        .orElse(FIRST_PAGE);
                int currentPage = Integer.parseInt(pageString);
                double countOfTasks = service.countOfUsers();
                int maxPage = (int) Math.ceil(countOfTasks / LIMIT);
                if (maxPage > 0 && maxPage < currentPage) {
                    currentPage = maxPage;
                }
                Deque<User> users = service.findAllUsersWithLimit((currentPage - 1) * (LIMIT), LIMIT);
                request.setAttribute(RequestAttribute.USERS, users);
                request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPage);
                request.setAttribute(RequestAttribute.MAX_PAGE, maxPage);
                router = new Router(RouterType.FORWARD, PagePath.ALL_USERS_PAGE);
            } catch (ServiceException e) {
                throw new CommandException("ShowAllUsersCommand command error: "+e.getMessage(), e);
            }
        } else {
            router = new Router(RouterType.FORWARD, PagePath.LOGIN_PAGE);


        }
        return router;
    }
}

package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        String email = request.getParameter(RequestParameter.EMAIL);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        try {
            boolean result = userService.forgetPassword(email);
            if (result) {
                router = new Router(RouterType.REDIRECT, PagePath.GO_TO_LOGIN_PAGE, RequestParameter.PREPARATION_FOR_EMAIL_MESSAGE, RequestParameter.TRUE);
            } else {
                router = new Router(RouterType.REDIRECT, PagePath.GO_TO_SEND_PASSWORD_COMMAND);
            }
        } catch (ServiceException e) {
            logger.error("Error at ForgetPasswordCommand "+e.getMessage(), e);
            router = new Router(RouterType.REDIRECT, PagePath.ERROR_PAGE);
        }
        return router;
    }
}


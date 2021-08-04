package com.epam.webproject.controller.command.impl;

import com.epam.webproject.controller.command.*;
import com.epam.webproject.exception.CommandException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class EditPageCommand implements Command {
private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String oldLogin = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        String oldEmail = (String) request.getSession().getAttribute(RequestAttribute.EMAIL);
        String newLogin = request.getParameter(RequestParameter.LOGIN);
        String newEmail = request.getParameter(RequestParameter.EMAIL);
        UserService service = ServiceProvider.getInstance().getUserService();
        try {
            Router router = new Router();
            boolean isUpdated = service.updateUser(newLogin, newEmail, oldLogin, oldEmail);
            if(isUpdated){
                Optional<User> optionalUser = service.findByLoginOrEmail(newLogin);
                User user = optionalUser.get();
                request.getSession().setAttribute(RequestAttribute.LOGIN,newLogin);
                request.getSession().setAttribute(RequestAttribute.EMAIL,newEmail);
                request.setAttribute(RequestAttribute.USER, user);
                router=new Router(RouterType.REDIRECT, PagePath.FIND_PROFILE_INFO_COMMAND);
            }else {
                //todo loginoremail
                request.setAttribute(RequestAttribute.MESSAGE,"Login Or Email exists");
                router=new Router(RouterType.REDIRECT,PagePath.FIND_EDITING_INFO_COMMAND);
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

    }
}

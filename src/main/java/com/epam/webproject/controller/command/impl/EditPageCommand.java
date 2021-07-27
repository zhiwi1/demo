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
        Router router = new Router();
        String oldLogin = (String) request.getSession().getAttribute(RequestAttribute.LOGIN);
        String oldEmail = (String) request.getSession().getAttribute(RequestAttribute.EMAIL);
        String newLogin = request.getParameter(RequestParameter.LOGIN);
        String newEmail = request.getParameter(RequestParameter.EMAIL);
        logger.info(oldLogin+" ",newLogin);
        logger.info(oldEmail+" "+newEmail);
        UserService service = ServiceProvider.getInstance().getUserService();
        try {
            boolean result = service.updateUser(newLogin, newEmail, oldLogin, oldEmail);
            if(result){
                Optional<User> optionalUser = service.findByLoginOrEmail(newLogin);
                User user = optionalUser.get();
                request.getSession().setAttribute(RequestAttribute.LOGIN,newLogin);
                request.getSession().setAttribute(RequestAttribute.EMAIL,newEmail);
                request.setAttribute(RequestAttribute.USER, user);
                router=new Router(RouterType.FORWARD, PagePath.PROFILE_PAGE);
            }else {
                //todo loginoremail
                request.setAttribute(RequestAttribute.MESSAGE,"Login Or Email exists");
                router=new Router(RouterType.FORWARD,PagePath.EDITING_INFO_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

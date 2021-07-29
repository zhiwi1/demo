package com.epam.webproject.controller.filter;

import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.RequestAttribute;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.User;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class BanFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String login = (String) session.getAttribute(RequestAttribute.LOGIN);
        UserService userService = ServiceProvider.getInstance().getUserService();
        try {
            if (userService.checkUserStatus(login, Status.BLOCKED)) {
                httpServletRequest.removeAttribute(RequestAttribute.LOGIN);
                //todo message of block
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(PagePath.LOGIN_PAGE);
                dispatcher.forward(httpServletRequest, httpServletResponse);
                httpServletResponse.sendRedirect(PagePath.LOGIN_PAGE);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (ServiceException serviceException) {
            logger.error("Filter error: {}", serviceException.getMessage());
            throw new ServletException("Filter error: " + serviceException.getMessage(), serviceException);
        }
    }
}

package com.epam.webproject.controller.filter;

import com.epam.webproject.controller.command.RequestAttribute;
import com.epam.webproject.model.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        String role = (String) session.getAttribute(RequestAttribute.ROLE);

        if (role == null) {
            session.setAttribute(RequestAttribute.ROLE, Role.ADMIN.getValue());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}


package com.epam.webproject.controller.filter;

import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.RequestAttribute;
import com.epam.webproject.model.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/controller?command=go_to_home_page_command","/controller?command=go_to_add_task_page_command",
        "/controller?command=show_all_tasks_command","/controller?command=open_task_page_command",
"/controller?command=show_my_tasks_command","/controller?command=find_answers_of_task_command","/controller?command=show_all_users_command",
        "/controller?command=tasks_full_text_search_command","/controller?command=users_full_text_search_command"})

//todo
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
       Role role = (Role) session.getAttribute(RequestAttribute.ROLE);

        if (role == null) {
            System.out.println(role);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(PagePath.LOGIN_PAGE);
            dispatcher.forward(httpServletRequest, servletResponse);

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}


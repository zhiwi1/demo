//package com.epam.webproject.controller.filter;
//
//import com.epam.webproject.controller.command.PagePath;
//import com.epam.webproject.controller.command.RequestAttribute;
//import com.epam.webproject.model.entity.Role;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.IOException;
//
//@WebFilter(dispatcherTypes = {
//DispatcherType.REQUEST,
//        DispatcherType.FORWARD,
//}, urlPatterns = {"/controller"}, servletNames = {"Controller"})
//
////todo
//public class RoleFilter implements Filter {
//
//
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//
//        String path = ((HttpServletRequest) req).getServletPath();
//        System.out.println(path);
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//
//        Role type = (Role) session.getAttribute(RequestAttribute.ROLE);
//        if (type == null) {
//
//            RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher(PagePath.LOGIN_PAGE);
//            dispatcher.forward(req, resp);
//            return;
//        }
//        // pass the request along the filter chain
//        chain.doFilter(request, response);
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//    }
//}
//
//

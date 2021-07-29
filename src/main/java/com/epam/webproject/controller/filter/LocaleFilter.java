package com.epam.webproject.controller.filter;

import com.epam.webproject.controller.command.CommandType;
import com.epam.webproject.controller.command.RequestAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

//@WebFilter(urlPatterns = {"/controller"})
//public class LocaleFilter implements Filter {
//    private static final Logger logger = LogManager.getLogger();
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession(true);
//logger.debug(httpRequest.getServletPath());
//logger.debug(httpRequest.getQueryString());
//
//        String queryString = httpRequest.getQueryString();
//
//        String prevRequest = RequestAttribute.CONTROLLER_URL + queryString;
//
//        if (session.getAttribute(RequestAttribute.LOCALE) == null) {
//            session.setAttribute(RequestAttribute.LOCALE, Locale.getDefault());
//        }
//
//        if (queryString != null) {
//            session.setAttribute(RequestAttribute.PREV_REQUEST, prevRequest);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void destroy() {
//    }
//}

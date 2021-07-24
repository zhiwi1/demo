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

@WebFilter(urlPatterns = {"/controller"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);

        String queryString = httpRequest.getQueryString();
//        if(queryString==null){
//            queryString=RequestAttribute.GO_TO_LOGIN_PAGE;
//        }
      //  Request. Get session. SetAttribute (sessionAttribute. Prev Page. Name(), Pagepath. (имя страницы). Value())
        queryString = changePreviousRequest(queryString);
        String prevRequest = RequestAttribute.CONTROLLER_URL + queryString;

        if (session.getAttribute(RequestAttribute.LOCALE) == null) {
            session.setAttribute(RequestAttribute.LOCALE, Locale.getDefault());
        }

        if (queryString != null) {
            session.setAttribute(RequestAttribute.PREV_REQUEST, prevRequest);
        }
        filterChain.doFilter(request, response);
    }

    private String changePreviousRequest(String prevRequest) {
        String result = prevRequest;
        String c = "command=";
//todo clear code
        logger.info(prevRequest);
        logger.info(c + CommandType.SIGN_UP_COMMAND.toString());
        if (prevRequest != null) {
            if (prevRequest.equals(c + CommandType.SIGN_UP_COMMAND.toString().toLowerCase())) {
                result = c + CommandType.GO_TO_REGISTRATION_PAGE_COMMAND.toString().toLowerCase();
            }
            if (prevRequest.equals(c + CommandType.SIGN_IN_COMMAND.toString().toLowerCase())) {
                result = CommandType.GO_TO_LOGIN_PAGE_COMMAND.toString().toLowerCase();
            }
            if (prevRequest.equals(c + CommandType.ADD_TASK_COMMAND.toString().toLowerCase())) {
                result = CommandType.GO_TO_ADD_TASK_PAGE_COMMAND.toString().toLowerCase();
            }

            return result;
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}

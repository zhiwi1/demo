package com.epam.webproject.controller.listener;

import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.RequestAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);


      //  se.getSession().setAttribute(RequestAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_PAGE);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}


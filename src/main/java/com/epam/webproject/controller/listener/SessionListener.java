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
        se.getSession().setAttribute(RequestAttribute.LOCALE,"en");

        HttpSessionListener.super.sessionCreated(se);




    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}


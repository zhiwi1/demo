package com.epam.webproject.controller.listener;

import com.epam.webproject.controller.command.PagePath;
import com.epam.webproject.controller.command.RequestAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LANGUAGE = "en";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().setAttribute(RequestAttribute.LOCALE, DEFAULT_LANGUAGE);
        HttpSessionListener.super.sessionCreated(sessionEvent);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSessionListener.super.sessionDestroyed(sessionEvent);
    }
}


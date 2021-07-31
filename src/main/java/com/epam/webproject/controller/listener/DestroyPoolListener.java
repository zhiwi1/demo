package com.epam.webproject.controller.listener;

import com.epam.webproject.controller.command.RequestAttribute;
import com.epam.webproject.model.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DestroyPoolListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroyPool();
    }

}

package com.epam.webproject.model.service;

import com.epam.webproject.model.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static ServiceProvider instance;
    private final UserService userService;
//todo other services
    private ServiceProvider() {

        this.userService = new UserServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }

        return instance;
    }

    public UserService getUserService() {
        return this.userService;
    }
}
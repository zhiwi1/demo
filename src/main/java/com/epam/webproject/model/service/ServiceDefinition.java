package com.epam.webproject.model.service;

import com.epam.webproject.model.service.impl.UserServiceImpl;

public class ServiceDefinition {
    private static ServiceDefinition instance;
    private final UserService userService;
//todo other services
    private ServiceDefinition() {

        this.userService = new UserServiceImpl();
    }

    public static ServiceDefinition getInstance() {
        if (instance == null) {
            instance = new ServiceDefinition();
        }

        return instance;
    }

    public UserService getUserService() {
        return this.userService;
    }
}
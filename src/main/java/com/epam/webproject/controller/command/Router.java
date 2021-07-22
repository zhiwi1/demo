package com.epam.webproject.controller.command;

public class Router {
    private RouterType routerType;
    private String routerPath;

    public Router( ){}
    public Router(RouterType routerType, String routerPath) {
        this.routerType = routerType;
        this.routerPath = routerPath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public String getRouterPath() {
        return routerPath;
    }


}
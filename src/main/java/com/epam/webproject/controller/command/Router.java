package com.epam.webproject.controller.command;

public class Router {
    private RouterType routerType;
    private String routerPath;

    public Router( ){}
    public Router(RouterType routerType, String routerPath) {
        this.routerType = routerType;
        this.routerPath = routerPath;
    }
    public Router(RouterType routerType, String routerPath,String preparationForParam,String param) {
        this.routerType = routerType;
        this.routerPath = routerPath+preparationForParam+param;
    }


    public RouterType getRouterType() {
        return routerType;
    }

    public String getRouterPath() {
        return routerPath;
    }


}
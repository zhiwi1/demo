package com.epam.webproject.controller.command;

public final class PagePath {
    public static final String HOME_PAGE = "/jsp/home.jsp";
    public static final String ERROR_PAGE = "404.jsp";
    public static final String LOGIN_PAGE = "/jsp/login.jsp";
    public static final String REGISTRATION_PAGE = "/jsp/registration.jsp";
    public static final String ADD_TASK_PAGE = "/jsp/addtask.jsp";
    public static final String ALL_TASKS_PAGE = "/jsp/alltasks.jsp";
    public static final String PROFILE_PAGE = "/jsp/profile.jsp";
    public static final String ALL_USERS_PAGE = "/jsp/allusers.jsp";
    public static final String EDITING_INFO_PAGE = "/jsp/editpage.jsp";
    public static final String TASK_PAGE = "/jsp/task.jsp";

    public static final String GO_TO_LOGIN_PAGE = "controller?command=go_to_login_page_command";
    public static final String GO_TO_HOME_PAGE = "controller?command=go_to_home_page_command";
    public static final String GO_TO_REGISTRATION_PAGE_COMMAND = "controller?command=go_to_home_page_command";
    public static final String OPEN_TASK_PAGE_COMMAND = "controller?command=open_task_page_command&title=";
    public static final String GO_TO_ADD_TASK_PAGE_COMMAND = "controller?command=go_to_add_task_page_command";
    public static final String FIND_PROFILE_INFO_COMMAND = "controller?command=find_profile_info_command";
    public static final String FIND_EDITING_INFO_COMMAND = "controller?command=find_editing_info_command";


    private PagePath() {
    }

}

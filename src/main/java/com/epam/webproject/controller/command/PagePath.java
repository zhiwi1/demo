package com.epam.webproject.controller.command;

public final class PagePath {
    public static final String HOME_PAGE = "/jsp/home.jsp";
    public static final String ERROR_PAGE = "/jsp/404.jsp";
    public static final String LOGIN_PAGE = "/jsp/login.jsp";
    public static final String REGISTRATION_PAGE = "/jsp/registration.jsp";
    public static final String ADD_TASK_PAGE = "/jsp/addtask.jsp";
    public static final String ALL_TASKS_PAGE = "/jsp/alltasks.jsp";
    public static final String PROFILE_PAGE = "/jsp/profile.jsp";
    public static final String ALL_USERS_PAGE = "/jsp/allusers.jsp";
    public static final String EDITING_INFO_PAGE = "/jsp/editpage.jsp";
    public static final String TASK_PAGE = "/jsp/task.jsp";
    public static final String NEW_PASSWORD_PAGE = "/jsp/newpassword.jsp";
    public static final String USER_TASKS_PAGE="/jsp/usertasks.jsp";
    public static final String ANSWERS_OF_TASK_PAGE="/jsp/answersoftask.jsp";

    public static final String GO_TO_LOGIN_PAGE = "controller?command=go_to_login_page_command";
    public static final String GO_TO_HOME_PAGE = "controller?command=go_to_home_page_command";
    public static final String GO_TO_REGISTRATION_PAGE_COMMAND = "controller?command=go_to_registration_page_command";
    public static final String OPEN_TASK_PAGE_COMMAND = "controller?command=open_task_page_command&title=";
    public static final String GO_TO_ADD_TASK_PAGE_COMMAND = "controller?command=go_to_add_task_page_command";
    public static final String FIND_PROFILE_INFO_COMMAND = "controller?command=find_profile_info_command";
    public static final String FIND_EDITING_INFO_COMMAND = "controller?command=find_editing_info_command";
    public static final String SHOW_ALL_USERS_COMMAND = "controller?command=show_all_users_command";
    public static final String SHOW_MY_TASKS_COMMAND_PAGE="controller?command=show_my_tasks_command";
    public static final String FIND_ANSWERS_OF_TASK_COMMAND="controller?command=find_answers_of_task_command";
    public static final String DEFAULT_COMMAND="controller?command=default_command";
    public static final String PREPARATION_FOR_PARAM_TITLE="&title=";
    public static final String GO_TO_SEND_PASSWORD_COMMAND="controller?command=go_to_send_password_command";


    private PagePath() {
    }

}

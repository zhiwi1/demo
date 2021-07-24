package com.epam.webproject.model.service;

import com.epam.webproject.model.service.impl.AnswerServiceImpl;
import com.epam.webproject.model.service.impl.CommentServiceImpl;
import com.epam.webproject.model.service.impl.TaskServiceImpl;
import com.epam.webproject.model.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static ServiceProvider instance;
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    private final AnswerService answerService;

    //todo other services
    private ServiceProvider() {
        this.postService = new TaskServiceImpl();
        this.userService = new UserServiceImpl();
        this.commentService = new CommentServiceImpl();
        this.answerService=new AnswerServiceImpl();
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

    public PostService getPostService() {
        return this.postService;
    }

    public CommentService getCommentService() {
        return this.commentService;
    }

    public AnswerService getAnswerService() {
        return this.answerService;
    }
}
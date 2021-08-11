package com.epam.webproject.model.dao;


import com.epam.webproject.model.dao.impl.AnswerDaoImpl;
import com.epam.webproject.model.dao.impl.CommentDaoImpl;
import com.epam.webproject.model.dao.impl.TaskDaoImpl;
import com.epam.webproject.model.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static DaoProvider instance;
    private final UserDao userDao;
    private final TaskDao taskDao;
    private final AnswerDao answerDao;
    private final CommentDao commentDao;


    private DaoProvider() {
        this.userDao = new UserDaoImpl();
        this.taskDao = new TaskDaoImpl();
        this.answerDao = new AnswerDaoImpl();
        this.commentDao = new CommentDaoImpl();
    }

    public static DaoProvider getInstance() {
        if (instance == null) {
            instance = new DaoProvider();
        }

        return instance;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public AnswerDao getAnswerDao() {
        return answerDao;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }
}


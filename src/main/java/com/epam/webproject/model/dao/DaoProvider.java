package com.epam.webproject.model.dao;


import com.epam.webproject.model.dao.impl.TaskDaoImpl;
import com.epam.webproject.model.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static DaoProvider instance;

    private final UserDao userDao;
    private final TaskDao taskDao;


    private DaoProvider() {
        this.userDao = new UserDaoImpl();
        this.taskDao = new TaskDaoImpl();
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
}


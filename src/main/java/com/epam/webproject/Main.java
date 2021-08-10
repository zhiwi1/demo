package com.epam.webproject;

import com.epam.webproject.exception.DaoException;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.*;
import com.epam.webproject.model.dao.impl.AnswerDaoImpl;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.CommentService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

class Main {
    private  static  final Logger logger = LogManager.getLogger();

    public static void main(String args[]) throws DaoException , ServiceException
    {
UserDao userDao=DaoProvider.getInstance().getUserDao();
        System.out.println(userDao.findRoleByLogin("zhiwi"));

    }
}
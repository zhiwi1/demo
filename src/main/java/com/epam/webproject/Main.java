package com.epam.webproject;

import com.epam.webproject.exception.DaoException;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.*;
import com.epam.webproject.model.dao.impl.AnswerDaoImpl;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.model.service.ServiceProvider;
import com.epam.webproject.model.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

class Main {
    private  static  final Logger logger = LogManager.getLogger();

    public static void main(String args[]) throws DaoException , ServiceException
    {
        TaskService service =ServiceProvider.getInstance().getTaskService();
        Optional<String> taskTitle=service.findTitleById(1);
        System.out.println(taskTitle);
        AnswerService answerService=ServiceProvider.getInstance().getAnswerService();
        System.out.println(answerService.markCorrect(2));
    }
}
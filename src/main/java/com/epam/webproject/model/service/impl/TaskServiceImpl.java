package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoProvider;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.validator.TaskValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LogManager.getLogger();
    private static final TaskDao taskDao = DaoProvider.getInstance().getTaskDao();


    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException {
        Feedback feedback;
        if (TaskValidator.checkComplexity(complexity)) {
            int complexityInt = Integer.parseInt(complexity);
            try {
                boolean isCreated = taskDao.createNewTask(title, text, createdAt, loginOfUser, complexityInt);
                if (isCreated) {
                    feedback = Feedback.SUCCESS;
                } else {
                    feedback = Feedback.DATABASE_EXCEPTION;
                }
            } catch (DaoException e) {
                logger.error("Can't create task", e.getMessage());
                throw new ServiceException("Can't create task", e);
            }


        } else {
            feedback = Feedback.CHECK_DATA;

        }
        return feedback;
    }

    public List<Task> findAllTasks() throws ServiceException {
        try {
            List<Task> tasks = taskDao.findAll();
            return tasks;
        } catch (DaoException e) {
            logger.error("Can't show all tasks", e.getMessage());
            throw new ServiceException("Can't show all task", e);
        }

    }

    public Optional<Task> findTaskByTitle(String title) throws ServiceException {
        Optional<Task> task = Optional.empty();
        try {
            task = taskDao.findTaskByTitle(title);
        } catch (DaoException e) {
            logger.error("Can't find task", e.getMessage());
            throw new ServiceException("Can't find  task", e);
        }
        return task;
    }

    public ArrayDeque<String> findByFullText(String text) throws ServiceException {
        try {
            ArrayDeque<String> arrayDeque = new ArrayDeque<>();
            if (TaskValidator.checkLength(text)) {
                arrayDeque = taskDao.findByFullText(text);
            }
            return arrayDeque;
        } catch (DaoException e) {
            logger.error("Can't find task", e.getMessage());
            throw new ServiceException("Can't find  task", e);
        }

    }
}

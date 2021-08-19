package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoProvider;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.validator.TaskValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LogManager.getLogger();
    private static final TaskDao taskDao = DaoProvider.getInstance().getTaskDao();

    @Override
    public Optional<String> findTitleById(long id) throws ServiceException {
        try {
            return taskDao.findTitleById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl:   findTitleById(long id) {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:    findTitleById(long id) " + e.getMessage(), e);
        }
    }

    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException {
        Feedback feedback = Feedback.DATABASE_EXCEPTION;
        if (TaskValidator.checkComplexity(complexity) && TaskValidator.checkLength(text) && TaskValidator.checkTitle(title)) {
            try {
                if (!taskDao.existRowsByTitle(title)) {
                    int complexityInt = Integer.parseInt(complexity);

                    boolean isCreated = taskDao.createNewTask(title, text, createdAt, loginOfUser, complexityInt);
                    if (isCreated) {
                        feedback = Feedback.SUCCESS;
                    }
                } else {
                    feedback = Feedback.DATA_EXISTS;
                }
            } catch (DaoException e) {
                logger.log(Level.ERROR, "Can not execute TaskServiceImpl:   createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) {}", e.getMessage());
                throw new ServiceException("Can not execute TaskServiceImpl:    createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) " + e.getMessage(), e);

            }
        } else {
            feedback = Feedback.CHECK_DATA;

        }
        return feedback;
    }

    public Deque<Task> findAllTasksWithLimit(int offset, int limit) throws ServiceException {
        try {
            Deque<Task> tasks = taskDao.findAll(offset, limit);
            return tasks;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl:   findAllTasksWithLimit(int offset, int limit) {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:    findAllTasksWithLimit(int offset, int limit) " + e.getMessage(), e);

        }

    }

    public Optional<Task> findTaskByTitle(String title) throws ServiceException {
        Optional<Task> task = Optional.empty();
        try {
            task = taskDao.findTaskByTitle(title);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl: findTaskByTitle(String title)  {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:   findTaskByTitle(String title)  " + e.getMessage(), e);

        }
        return task;
    }

    public Deque<String> findByFullText(String text) throws ServiceException {
        try {
            Deque<String> arrayDeque = new ArrayDeque<>();
            if (TaskValidator.checkLength(text)) {
                arrayDeque = taskDao.findByFullText(text);
            }
            return arrayDeque;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl: findByFullText(String text)  {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:   findByFullText(String text)  " + e.getMessage(), e);

        }

    }

    @Override
    public Deque<Task> findTasksByUserLogin(String login, int offset, int limit) throws ServiceException {
        try {
            Deque<Task> tasks = taskDao.findTasksByUserLogin(login, offset, limit);
            return tasks;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl: findTasksByUserLogin(String login, int offset, int limit)   {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:   findTasksByUserLogin(String login, int offset, int limit)   " + e.getMessage(), e);

        }

    }

    @Override
    public boolean deleteTask(String title) throws ServiceException {
        try {
            boolean result = taskDao.deleteTask(title);
            return result;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl: deleteTask(String title)   {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:   deleteTask(String title)   " + e.getMessage(), e);

        }
    }

    @Override
    public int countOfTasks() throws ServiceException {
        try {
            return taskDao.countOfTasks();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute TaskServiceImpl: countOfTasks()   {}", e.getMessage());
            throw new ServiceException("Can not execute TaskServiceImpl:  countOfTasks()   " + e.getMessage(), e);

        }
    }
}

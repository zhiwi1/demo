package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoProvider;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.TaskService;
import com.epam.webproject.validator.PostValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LogManager.getLogger();
    private static final TaskDao taskDao = DaoProvider.getInstance().getTaskDao();


    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException {
        Feedback feedback;
        if (PostValidator.checkComplexity(complexity)) {
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

    public List<Task> showAllTasks() throws ServiceException {
        try {
            List<Task> tasks = taskDao.findAll();
            return tasks;
        } catch (DaoException e) {
            logger.error("Can't create task", e.getMessage());
            throw new ServiceException("Can't create task", e);
        }

    }
}

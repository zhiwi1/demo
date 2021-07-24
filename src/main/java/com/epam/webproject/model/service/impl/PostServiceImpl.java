package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.DaoProvider;
import com.epam.webproject.model.dao.TaskDao;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.model.service.PostService;
import com.epam.webproject.validator.PostValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PostServiceImpl implements PostService {
    private static final Logger logger = LogManager.getLogger();
    private static final TaskDao taskDao = DaoProvider.getInstance().getTaskDao();


    public Feedback createPost(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException {

        Feedback feedback;
        if (PostValidator.checkComplexity(complexity)) {

            int complexityInt = Integer.parseInt(complexity);
            int countOfSolve = Integer.parseInt(complexity);
            try {

                boolean isCreated = taskDao.createNewTask(title, text, createdAt, loginOfUser, complexityInt);
                if (isCreated) {
                    feedback = Feedback.SUCCESS;
                } else {
                    feedback = Feedback.DATABASE_EXCEPTION;
                }
            } catch (DaoException e) {
                logger.error("Can't create post", e.getMessage());
                throw new ServiceException("Can't sign in", e);
            }


        } else {
            feedback = Feedback.CHECK_DATA;

        }
        return feedback;
    }
}

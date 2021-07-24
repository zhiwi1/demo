package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.dao.CommentDao;
import com.epam.webproject.model.dao.DaoProvider;
import com.epam.webproject.model.service.CommentService;
import com.epam.webproject.model.service.Feedback;
import com.epam.webproject.validator.CommentValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;


public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger();
    private static final CommentDao commentDao = DaoProvider.getInstance().getCommentDao();


    public boolean createComment(String text, String login, String title) throws ServiceException {

        boolean result = false;
        if (CommentValidator.checkComment(text)) {
            Date currentDate = new Date();
            try {
                result = commentDao.createNewComment(text, currentDate, login, title);
            } catch (DaoException e) {
                logger.error("Can't create comment", e.getMessage());
                throw new ServiceException("Can't create comment", e);
            }

        }
        return result;
    }
}

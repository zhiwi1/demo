package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;

import com.epam.webproject.model.dao.AnswerDao;
import com.epam.webproject.model.dao.DaoProvider;

import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.entity.Comment;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.validator.AnswerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

public class AnswerServiceImpl implements AnswerService {
    private static final Logger logger = LogManager.getLogger();
    private static final AnswerDao answerDao = DaoProvider.getInstance().getAnswerDao();

    public boolean createAnswer(String text, String login, String title) throws ServiceException {

        boolean result = false;
        if (AnswerValidator.checkAnswer(text)) {
            try {
                result = answerDao.createNewAnswer(text, title, login);
            } catch (DaoException e) {
                logger.error("Can't create comment", e.getMessage());
                throw new ServiceException("Can't create comment", e);
            }

        }
        return result;
    }

    @Override
    public Deque<Answer> findAnswersByTitle(String title) throws ServiceException {
        try {
            return answerDao.findAnswersByTitle(title);
        } catch (DaoException e) {
            logger.error("Can't find", e.getMessage());
            throw new ServiceException("Can't find", e);
        }


    }

    @Override
    public boolean likeOrUnlike(long answerId, boolean flag) throws ServiceException {
        boolean result = false;
        try {

            if (flag) {
                result = answerDao.increaseLike(answerId);
            } else {
                result = answerDao.decreaseLike(answerId);
            }
        } catch (DaoException e) {
            logger.error("Can't like", e.getMessage());
            throw new ServiceException("Can't like", e);
        }
        return result;
    }

    @Override
    public boolean markCorrect(long answerId) throws ServiceException {

        try {
            return answerDao.markCorrect(answerId);
        } catch (DaoException e) {
            logger.error("Can't block user", e.getMessage());
            throw new ServiceException("Can't block user", e);
        }
    }
    @Override
    public boolean markIncorrect(long answerId) throws ServiceException {

        try {
            return answerDao.markIncorrect(answerId);
        } catch (DaoException e) {
            logger.error("Can't block user", e.getMessage());
            throw new ServiceException("Can't block user", e);
        }
    }
}

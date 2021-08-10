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
    public Deque<Answer> findAnswersByTitle(String title,int offset,int limit) throws ServiceException {
        try {
            return answerDao.findAnswersByTitleWithLimit(title,offset,limit);
        } catch (DaoException e) {
            logger.error("Can't find", e.getMessage());
            throw new ServiceException("Can't find", e);
        }


    }



    @Override
    public boolean markCorrect(long answerId) throws ServiceException {

        try {
            return answerDao.markCorrectTransaction(answerId);
        } catch (DaoException e) {
            logger.error("Can't block user", e.getMessage());
            throw new ServiceException("Can't block user", e);
        }
    }
    @Override
    public boolean markIncorrect(long answerId) throws ServiceException {

        try {
            return answerDao.markIncorrectTransaction(answerId);
        } catch (DaoException e) {
            logger.error("Can't block user", e.getMessage());
            throw new ServiceException("Can't block user", e);
        }
    }
    @Override
    public int countOfAnswers(String titleOfTask) throws ServiceException {
        try {
            return answerDao.countOfAnswers(titleOfTask);
        } catch (DaoException e) {
            logger.error("Can't find", e.getMessage());
            throw new ServiceException("Can't find", e);
        }
    }
}

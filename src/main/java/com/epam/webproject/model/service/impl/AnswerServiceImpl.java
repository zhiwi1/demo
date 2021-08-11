package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;

import com.epam.webproject.model.dao.AnswerDao;
import com.epam.webproject.model.dao.DaoProvider;

import com.epam.webproject.model.entity.Answer;
import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.validator.AnswerValidator;
import org.apache.logging.log4j.Level;
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
                logger.log(Level.ERROR, "Can not execute AnswerServiceImpl: createAnswer(String text, String login, String title)  {}", e.getMessage());
                throw new ServiceException("Can not execute AnswerServiceImpl: createAnswer(String text, String login, String title) " + e.getMessage(), e);

            }

        }
        return result;
    }

    @Override
    public Deque<Answer> findAnswersByTitle(String title, int offset, int limit) throws ServiceException {
        try {
            return answerDao.findAnswersByTitleWithLimit(title, offset, limit);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute AnswerServiceImpl: findAnswersByTitle(String title,int offset,int limit)  {}", e.getMessage());
            throw new ServiceException("Can not execute AnswerServiceImpl: findAnswersByTitle(String title,int offset,int limit) " + e.getMessage(), e);

        }


    }


    @Override
    public boolean markCorrect(long answerId) throws ServiceException {

        try {
            return answerDao.markCorrectTransaction(answerId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute AnswerServiceImpl: markCorrect(long answerId)  {}", e.getMessage());
            throw new ServiceException("Can not execute AnswerServiceImpl: markCorrect(long answerId) " + e.getMessage(), e);

        }
    }

    @Override
    public boolean markIncorrect(long answerId) throws ServiceException {

        try {
            return answerDao.markIncorrectTransaction(answerId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute AnswerServiceImpl: markIncorrect(long answerId)  {}", e.getMessage());
            throw new ServiceException("Can not execute AnswerServiceImpl: markIncorrect(long answerId) " + e.getMessage(), e);

        }
    }

    @Override
    public int countOfAnswers(String titleOfTask) throws ServiceException {
        try {
            return answerDao.countOfAnswers(titleOfTask);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Can not execute AnswerServiceImpl: countOfAnswers(String titleOfTask)  {}", e.getMessage());
            throw new ServiceException("Can not execute AnswerServiceImpl: countOfAnswers(String titleOfTask) " + e.getMessage(), e);

        }
    }
}

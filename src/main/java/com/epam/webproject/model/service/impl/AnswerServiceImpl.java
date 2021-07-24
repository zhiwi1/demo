package com.epam.webproject.model.service.impl;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;

import com.epam.webproject.model.dao.AnswerDao;
import com.epam.webproject.model.dao.DaoProvider;

import com.epam.webproject.model.service.AnswerService;
import com.epam.webproject.validator.AnswerValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

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
}

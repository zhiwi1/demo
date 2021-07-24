package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;

public interface AnswerService {
    public boolean createAnswer(String text, String login, String title) throws ServiceException ;

    }

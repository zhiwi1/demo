package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;

import java.util.ArrayDeque;

public interface AnswerService {
    public boolean createAnswer(String text, String login, String title) throws ServiceException;

    public ArrayDeque<Answer> findAnswersByTitle(String title) throws ServiceException;

    boolean likeOrUnlike(long answerId, boolean flag) throws ServiceException;
}

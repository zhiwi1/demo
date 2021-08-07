package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;

import java.util.ArrayDeque;
import java.util.Deque;

public interface AnswerService {
    public boolean createAnswer(String text, String login, String title) throws ServiceException;

    public Deque<Answer> findAnswersByTitle(String title,int offset,int limit) throws ServiceException;

    boolean likeOrUnlike(long answerId, boolean flag) throws ServiceException;

    public boolean markCorrect(long answerId) throws ServiceException;

    public boolean markIncorrect(long answerId) throws ServiceException;

    public int countOfAnswers(String titleOfTask) throws ServiceException;
}

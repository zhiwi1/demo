package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public interface AnswerDao {
    public boolean createNewAnswer(String content, String title, String login) throws DaoException;

    public Deque<Answer> findAllWithLimit(int offset, int limit) throws DaoException;

    public Deque<Answer> findAnswersByTitleWithLimit(String title, int offset, int limit) throws DaoException;
    public boolean markCorrectTransaction(long id) throws DaoException;
    public boolean markIncorrectTransaction(long id) throws DaoException;
    public boolean markCorrect(long id) throws DaoException;

    public boolean markIncorrect(long id) throws DaoException;

    public int countOfAnswers(String titleOfTask) throws DaoException;

}

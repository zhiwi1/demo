package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Answer;


import java.util.Deque;

public interface AnswerDao {
    public boolean createNewAnswer(String content, String title, String login) throws DaoException;

    public Deque<Answer> findAllWithLimit(int offset, int limit) throws DaoException;

    public Deque<Answer> findAnswersByTitleWithLimit(String title, int offset, int limit) throws DaoException;

    public boolean markCorrectTransaction(long id) throws DaoException;

    public boolean markIncorrectTransaction(long id) throws DaoException;

    public int countOfAnswers(String titleOfTask) throws DaoException;

}

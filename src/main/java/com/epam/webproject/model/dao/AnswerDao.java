package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public interface AnswerDao {
    public boolean createNewAnswer(String content, String title, String login) throws DaoException;

    public Deque<Answer> findAll() throws DaoException;

    public Deque<Answer> findAnswersByTitle(String title) throws DaoException;

    public boolean increaseLike(long id) throws DaoException;

    public boolean decreaseLike(long id) throws DaoException;

    public boolean markCorrect(long id) throws DaoException;

    public boolean markIncorrect(long id) throws DaoException;


    }

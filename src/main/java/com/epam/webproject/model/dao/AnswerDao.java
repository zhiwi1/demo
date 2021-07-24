package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Answer;

import java.util.List;

public interface AnswerDao {
    public boolean createNewAnswer(String content, String title, String login) throws DaoException;
    public List<Answer> findAll() throws DaoException;
}

package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Task;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;

public interface TaskDao {


    boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException;

    List<Task> findAll() throws DaoException;

    public Optional<Task> findTaskByTitle(String title) throws DaoException;

    public ArrayDeque<String> findByFullText(String text) throws DaoException;
}

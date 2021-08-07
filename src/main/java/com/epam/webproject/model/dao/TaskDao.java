package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Task;
import com.epam.webproject.model.entity.User;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public interface TaskDao {


    boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException;

    Deque<Task> findAll() throws DaoException;

    public Optional<Task> findTaskByTitle(String title) throws DaoException;

    public Deque<String> findByFullText(String text) throws DaoException;

    public Deque<Task> findTasksByUserLogin(String login) throws DaoException;

    public boolean deleteTask(String title) throws DaoException;

    public Optional<String> findTitleById(long id) throws DaoException;

    public int countOfTasks() throws DaoException;

    public Deque<Task> findAll(int offset, int limit) throws DaoException ;

    }

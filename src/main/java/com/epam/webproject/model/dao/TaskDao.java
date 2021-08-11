package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Task;
import java.util.Deque;
import java.util.Optional;

public interface TaskDao {


    public boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException;

    public Deque<Task> findAll() throws DaoException;

    public Optional<Task> findTaskByTitle(String title) throws DaoException;

    public Deque<String> findByFullText(String text) throws DaoException;

    public Deque<Task> findTasksByUserLogin(String login, int offset, int limit) throws DaoException;

    public boolean deleteTask(String title) throws DaoException;

    public Optional<String> findTitleById(long id) throws DaoException;

    public int countOfTasks() throws DaoException;

    public Deque<Task> findAll(int offset, int limit) throws DaoException;

    public boolean existRowsByTitle(String title) throws DaoException;

}

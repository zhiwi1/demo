package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Task;

import java.util.List;

public interface TaskDao {


   boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException ;
        List<Task> findAll() throws DaoException;
}

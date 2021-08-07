package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException;

    public Deque<Task> findAllTasks() throws ServiceException;

    public Optional<Task> findTaskByTitle(String title) throws ServiceException;

    public Deque<String> findByFullText(String text) throws ServiceException;

    public Deque<Task> findTasksByUserLogin(String login) throws ServiceException;

    public boolean deleteTask(String title) throws ServiceException;

    public Optional<String> findTitleById(long id) throws ServiceException;

    public int countOfTasks() throws ServiceException;

    public Deque<Task> findAllTasksWithLimit(int offset,int limit) throws ServiceException;
}


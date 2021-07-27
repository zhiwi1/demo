package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException;
    public List<Task> findAllTasks() throws ServiceException;
    public Optional<Task> findTaskByTitle(String title) throws ServiceException;
    public ArrayDeque<String> findByFullText(String text) throws ServiceException;
}


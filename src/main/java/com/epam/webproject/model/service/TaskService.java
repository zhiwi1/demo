package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;

import java.util.List;

public interface TaskService {
    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException;
    public List<Task> showAllTasks() throws ServiceException;
}

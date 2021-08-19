package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Task;

import java.util.Deque;
import java.util.Optional;

/**
 * The interface Task service.
 */
public interface TaskService {

    /**
     * Create task feedback.
     *
     * @param title       the title
     * @param text        the text
     * @param createdAt   the date created at
     * @param loginOfUser the login of user
     * @param complexity  the complexity
     * @return the feedback
     * @throws ServiceException the service exception
     */
    public Feedback createTask(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException;

    /**
     * Find task by title .
     *
     * @param title the title of task
     * @return the optional with task
     * @throws ServiceException the service exception
     */
    public Optional<Task> findTaskByTitle(String title) throws ServiceException;

    /**
     * Find tasks (full-text search by mysql).
     *
     * @param text the text
     * @return the deque with titles of tasks
     * @throws ServiceException the service exception
     */
    public Deque<String> findByFullText(String text) throws ServiceException;

    /**
     * Find tasks by user's login .
     *
     * @param login  the login of user
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with tasks
     * @throws ServiceException the service exception
     */
    public Deque<Task> findTasksByUserLogin(String login, int offset, int limit) throws ServiceException;

    /**
     * Delete task.
     *
     * @param title the title
     * @return the boolean (result of deleting)
     * @throws ServiceException the service exception
     */
    public boolean deleteTask(String title) throws ServiceException;

    /**
     * Find title of task by id of task .
     *
     * @param id the id of task
     * @return the optional with title of task
     * @throws ServiceException the service exception
     */
    public Optional<String> findTitleById(long id) throws ServiceException;

    /**
     * Count of tasks.
     *
     * @return the int (count)
     * @throws ServiceException the service exception
     */
    public int countOfTasks() throws ServiceException;

    /**
     * Find all tasks with limit for pagination.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with tasks
     * @throws ServiceException the service exception
     */
    public Deque<Task> findAllTasksWithLimit(int offset, int limit) throws ServiceException;
}


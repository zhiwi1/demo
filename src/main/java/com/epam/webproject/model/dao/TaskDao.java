package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Task;

import java.util.Deque;
import java.util.Optional;

/**
 * Interface for class with SQL requests to `tasks`
 */
public interface TaskDao {


    /**
     * Create new task.
     *
     * @param title      the title
     * @param text       the text
     * @param createdAt  the date created at
     * @param login      the login
     * @param complexity the complexity
     * @return the boolean of result
     * @throws DaoException the dao exception
     */
    public boolean createNewTask(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException;


    /**
     * Find task by title .
     *
     * @param title the title of task
     * @return the optional with task
     * @throws DaoException the dao exception
     */
    public Optional<Task> findTaskByTitle(String title) throws DaoException;

    /**
     * Full-text search by mysql .
     *
     * @param text the text for fulltext search
     * @return the deque with titles of tasks
     * @throws DaoException the dao exception
     */
    public Deque<String> findByFullText(String text) throws DaoException;

    /**
     * Find tasks by user login.
     *
     * @param login  the login
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with tasks
     * @throws DaoException the dao exception
     */
    public Deque<Task> findTasksByUserLogin(String login, int offset, int limit) throws DaoException;

    /**
     * Delete task by title.
     *
     * @param title the title of task
     * @return the boolean with result
     * @throws DaoException the dao exception
     */
    public boolean deleteTask(String title) throws DaoException;

    /**
     * Find title by id .
     *
     * @param id the id of task
     * @return the optional with title
     * @throws DaoException the dao exception
     */
    public Optional<String> findTitleById(long id) throws DaoException;

    /**
     * Count of tasks.
     *
     * @return the int (count)
     * @throws DaoException the dao exception
     */
    public int countOfTasks() throws DaoException;

    /**
     * Find deque with tasks with limit for pagination .
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with tasks
     * @throws DaoException the dao exception
     */
    public Deque<Task> findAll(int offset, int limit) throws DaoException;

    /**
     * Сheck existence rows by title.
     *
     * @param title the title
     * @return the boolean with result
     * @throws DaoException the dao exception
     */
    public boolean existRowsByTitle(String title) throws DaoException;

}

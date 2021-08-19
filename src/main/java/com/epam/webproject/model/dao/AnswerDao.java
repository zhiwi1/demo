package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Answer;


import java.util.Deque;

/**
 * Interface for class with SQL requests to `answers`
 */
public interface AnswerDao {

    /**
     * Create new answer boolean.
     *
     * @param content the content
     * @param title   the title
     * @param login   the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public boolean createNewAnswer(String content, String title, String login) throws DaoException;


    /**
     * Find answers by title with limit deque.
     *
     * @param title  the title of task
     * @param offset the offset
     * @param limit  the limit
     * @return the deque
     * @throws DaoException the dao exception
     */
    public Deque<Answer> findAnswersByTitleWithLimit(String title, int offset, int limit) throws DaoException;

    /**
     * Mark correct( transaction) answer.
     *
     * @param id the id of answer
     * @return the boolean with result of marking
     * @throws DaoException the dao exception
     */
    public boolean markCorrect(long id) throws DaoException;

    /**
     * Mark incorrect (transaction) answer.
     *
     * @param id the id of answe
     * @return the boolean with result of marking
     * @throws DaoException the dao exception
     */
    public boolean markIncorrect(long id) throws DaoException;

    /**
     * Count of answers.
     *
     * @param titleOfTask the title of task
     * @return the int (count)
     * @throws DaoException the dao exception
     */
    public int countOfAnswers(String titleOfTask) throws DaoException;

}

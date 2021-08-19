package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Answer;

import java.util.Deque;

/**
 * The interface Answer service.
 */
public interface AnswerService {

    /**
     * Create answer boolean.
     *
     * @param text  the text
     * @param login the login
     * @param title the title
     * @return the boolean
     * @throws ServiceException the service exception
     */
    public boolean createAnswer(String text, String login, String title) throws ServiceException;

    /**
     * Find answers by title deque.
     *
     * @param title  the title
     * @param offset the offset
     * @param limit  the limit
     * @return the deque
     * @throws ServiceException the service exception
     */
    public Deque<Answer> findAnswersByTitle(String title,int offset,int limit) throws ServiceException;

    /**
     * Mark correct boolean.
     *
     * @param answerId the answer id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    public boolean markCorrect(long answerId) throws ServiceException;

    /**
     * Mark incorrect.
     *
     * @param answerId the id of answer
     * @return the boolean (result of marking)
     * @throws ServiceException the service exception
     */
    public boolean markIncorrect(long answerId) throws ServiceException;

    /**
     * Count of answers .
     *
     * @param titleOfTask the title of task
     * @return the int (count)
     * @throws ServiceException the service exception
     */
    public int countOfAnswers(String titleOfTask) throws ServiceException;
}

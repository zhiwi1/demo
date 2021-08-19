package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Comment;

import java.util.Deque;

/**
 * Interface for class with SQL requests to `comments`
 */
public interface CommentDao {
    /**
     * Create new comment.
     *
     * @param text      the text
     * @param createdAt the created at
     * @param login     the login
     * @param title     the title
     * @return the boolean, result of adding
     * @throws DaoException the dao exception
     */
    public boolean createNewComment(String text, java.util.Date createdAt, String login, String title) throws DaoException;

    /**
     * Find comments by title with limit for pagination.
     *
     * @param title  the title of task
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with comments
     * @throws DaoException the dao exception
     */
    public Deque<Comment> findCommentsByTitleWithLimit(String title, int offset, int limit) throws DaoException;

    /**
     * Count of comments .
     *
     * @param titleOfTask the title of task
     * @return the int (count)
     * @throws DaoException the dao exception
     */
    public int countOfComments(String titleOfTask) throws DaoException;
}

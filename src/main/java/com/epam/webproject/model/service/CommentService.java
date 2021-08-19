package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Comment;

import java.util.Deque;

/**
 * The interface Comment service.
 */
public interface CommentService {

    /**
     * Create comment.
     *
     * @param text  the text
     * @param login the login
     * @param title the title
     * @return the boolean (result of creating)
     * @throws ServiceException the service exception
     */
    public boolean createComment(String text, String login, String title) throws ServiceException;

    /**
     * Find comments by title.
     *
     * @param title  the title
     * @param offset the offset
     * @param limit  the limit
     * @return the deque with comments
     * @throws ServiceException the service exception
     */
    public Deque<Comment> findCommentsByTitle(String title,int offset,int limit) throws ServiceException;

    /**
     * Count of comments.
     *
     * @param titleOfTask the title of task
     * @return the int (count)
     * @throws ServiceException the service exception
     */
    public int countOfComments(String titleOfTask) throws ServiceException;
}

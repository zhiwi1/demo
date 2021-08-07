package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Comment;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public interface CommentDao {
    public Deque<Comment> findAllWithLimit(int offset, int limit) throws DaoException;
    public boolean createNewComment(String text, java.util.Date createdAt, String login, String title) throws DaoException;
    public Deque<Comment> findCommentsByTitleWithLimit(String title, int offset, int limit) throws DaoException;
    public int countOfComments(String titleOfTask) throws DaoException;
}

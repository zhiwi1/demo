package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Comment;

import java.util.ArrayDeque;

public interface CommentService {
    public boolean createComment(String text, String login, String title) throws ServiceException;
    public ArrayDeque<Comment> findCommentsByTitle(String title) throws ServiceException;
}

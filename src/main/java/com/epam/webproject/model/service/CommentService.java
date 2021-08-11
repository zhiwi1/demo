package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;
import com.epam.webproject.model.entity.Comment;

import java.util.Deque;

public interface CommentService {
    public boolean createComment(String text, String login, String title) throws ServiceException;
    public Deque<Comment> findCommentsByTitle(String title,int offset,int limit) throws ServiceException;
    public int countOfComments(String titleOfTask) throws ServiceException;
}

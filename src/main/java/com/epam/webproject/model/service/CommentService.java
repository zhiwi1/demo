package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;

public interface CommentService {
    public boolean createComment(String text, String login, String title) throws ServiceException;
}

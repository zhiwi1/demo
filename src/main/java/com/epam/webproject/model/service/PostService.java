package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;

public interface PostService {
    public Feedback createPost(String title, String text, java.util.Date createdAt, String loginOfUser, String complexity) throws ServiceException;
}

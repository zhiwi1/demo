package com.epam.webproject.model.service;

import com.epam.webproject.exception.ServiceException;

public interface PostService {
    Feedback createPost(String title, String text, java.util.Date createdAt, long idOfUser, String complexity, String countForSolve) throws ServiceException;
}

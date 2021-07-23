package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Post;
import com.epam.webproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    boolean createNewPost( Post post) throws DaoException;

   boolean createNewPost(String title, String text, java.util.Date createdAt, String login, int complexity) throws DaoException ;
        List<Post> findAll() throws DaoException;
}

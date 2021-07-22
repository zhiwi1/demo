package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.Post;
import com.epam.webproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    boolean createNewPost( Post post) throws DaoException;
    public Optional<Long> findUserIdOfPost(String login) throws DaoException;
    boolean createNewPost(String title, String text, java.util.Date createdAt,long id,int complexity,int countForSolve) throws DaoException;
    List<Post> findAll() throws DaoException;
}

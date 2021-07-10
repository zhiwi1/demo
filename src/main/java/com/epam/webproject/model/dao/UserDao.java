package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.dao.BaseDao;
import com.epam.webproject.model.entity.Entity;
import com.epam.webproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Entity, Long> {
    Long findMaxUserId() throws DaoException;

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    boolean addUser(User user, String password) throws DaoException;

    boolean updatePassword(String password, Long userId) throws DaoException;

    boolean updateNameAndSurname(User user) throws DaoException;

}


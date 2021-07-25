package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.dao.BaseDao;
import com.epam.webproject.model.entity.Entity;
import com.epam.webproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {
    List<User> findAll() throws DaoException;

    boolean createNewUser(User user, String password, String salt) throws DaoException;

    public Map<String, Optional<String>> findUserLoginDataByLogin(String login) throws DaoException;

    public Map<String, Optional<String>> findUserLoginDataByEmail(String email) throws DaoException;

    public boolean existRowsByEmail(String email) throws DaoException;

    public boolean existRowsByLogin(String login) throws DaoException;

    public Optional<Long> findUserIdByLogin(String login) throws DaoException;

    public Optional<User> findByLogin(String login) throws DaoException;

    public Optional<User> findByEmail(String email) throws DaoException;
}


package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.dao.BaseDao;
import com.epam.webproject.model.entity.Entity;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.User;

import java.util.ArrayDeque;
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

    public boolean updateUserName(String newLogin, String newEmail, String oldLogin) throws DaoException;

    public ArrayDeque<User> findByFullText(String text) throws DaoException;

    public boolean blockUser(String login) throws DaoException;

    public boolean unblockUser(String login) throws DaoException;

    public Optional<Status> findStatusByLogin(String login) throws DaoException;

    public Optional<String> findLoginByEmail(String login) throws DaoException;
}


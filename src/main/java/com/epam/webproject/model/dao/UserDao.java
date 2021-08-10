package com.epam.webproject.model.dao;

import com.epam.webproject.exception.DaoException;
import com.epam.webproject.model.entity.RatesType;
import com.epam.webproject.model.entity.Role;
import com.epam.webproject.model.entity.Status;
import com.epam.webproject.model.entity.User;

import java.util.*;

public interface UserDao {
    Deque<User> findAll() throws DaoException;

    boolean createNewUser(User user, String password, String salt) throws DaoException;

    public Map<String, Optional<String>> findUserLoginDataByLogin(String login) throws DaoException;

    public Map<String, Optional<String>> findUserLoginDataByEmail(String email) throws DaoException;

    public boolean existRowsByEmail(String email) throws DaoException;

    public boolean existRowsByLogin(String login) throws DaoException;

    public Optional<Long> findUserIdByLogin(String login) throws DaoException;

    public Optional<User> findByLogin(String login) throws DaoException;

    public Optional<User> findByEmail(String email) throws DaoException;

    public boolean updateUserName(String newLogin, String newEmail, String oldLogin) throws DaoException;

    public Deque<User> findByFullText(String text) throws DaoException;

    public boolean blockUser(String login) throws DaoException;

    public boolean unblockUser(String login) throws DaoException;

    public Optional<Status> findStatusByLogin(String login) throws DaoException;

    public Optional<String> findLoginByEmail(String login) throws DaoException;

    public void setPasswordById(long id, String password, String salt) throws DaoException;

    public Map<String, Long> findInfoForRates(String login) throws DaoException;

    public boolean setRates(String login, RatesType ratesType) throws DaoException;

    public int countOfUsers() throws DaoException;

    public Deque<User> findAll(int offset, int limit) throws DaoException;

    public Optional<Role> findRoleByLogin(String login) throws DaoException;
}


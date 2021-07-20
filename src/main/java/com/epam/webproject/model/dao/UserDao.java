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

    //public Optional<String> findLoginDataByEmail(String email) throws DaoException;

    boolean createNewUser(User user, String password, String salt) throws DaoException;

    public Map<String, Optional<String>> findUserLoginDataByLogin(String login) throws DaoException;

    public Map<String, Optional<String>> findUserLoginDataByEmail(String email) throws DaoException;



//    Long findMaxUserId() throws DaoException;
//
//    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
//
//    Optional<User> findByEmail(String email) throws DaoException;
//
//    boolean addUser(User user, String password) throws DaoException;
//
//    boolean updatePassword(String password, Long userId) throws DaoException;
//
//    boolean updateNameAndSurname(User user) throws DaoException;

}

